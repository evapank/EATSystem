import { useLocation, useNavigate } from "react-router-dom";
import { MeetingService, OtherService } from "../static/api";
import { useEffect, useState } from "react";
import Multiselect from "multiselect-react-dropdown";

const NewMeetingAdd = () => {
    const [meeting, setMeeting] = useState({
            dateTimeStart : '',
            dateTimeEnd : '',
            generalStatus: ''
        });
    const [statusArray, setStatusArray] = useState([]);
    const [employeeStatuses, setEmployeeStatuses] = useState([]);
    const [generalStatuses, setGeneralStatuses] = useState([]);
    const [employees, setEmployees] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    const location = useLocation();
    const myTime = {['dateTimeStart']:"200", ['dateTimeEnd']:"100"}
    
    useEffect(() => {
            const fetchMeeting = async() => {
                try {
                    setMeeting({...meeting, dateTimeStart: location.state.dateTimeStart});
                    setMeeting({...meeting, dateTimeEnd: location.state.dateTimeEnd});
                    console.log(location.state.dateTimeStart + "+ " + location.state.dateTimeEnd)
                    const response = await OtherService.getGeneralStatus();
                    myTime.dateTimeStart=location.state.dateTimeStart
                    myTime.dateTimeEnd=location.state.dateTimeEnd
                    const emStResponse = await MeetingService.getEmployeeStatuses(myTime);
                    console.log("statuses: "+response.data);
                    console.log("employee statuses: "+emStResponse.data);
                    console.log("employee statuses1: "+emStResponse.data.employee);
                    setGeneralStatuses(response.data);
                    setEmployeeStatuses(emStResponse.data);
                    setLoading(false);
                } catch (error){
                    setError('cannot find meeting');
                    setLoading(false);
                    console.log(error);
                }
            };
            fetchMeeting();
        }, []);

        const handleSubmit = async (e) => {
           e.preventDefault();
            try{
                await MeetingService.create(meeting);
                navigate('/meeting/all');
            } catch (error) {
                console.error('Submit error: ', error);
            }
       };
 
        const onChangeEmployees = (employee) => {
            const newEmployee =employee.map( (e) => {
                return {surname: employee.surname};
                });
            setEmployees(employee);
        };

    return (
         <div className="container mt-4">
            <h2>Create new meeting for the time:</h2>

            <form action="@{/meeting/create}" object={meeting} method="post" onSubmit={handleSubmit}>
                <div>
                    <label>Employees:</label>
                    <Multiselect options={employeeStatuses} onSelect={onChangeEmployees} onRemove={onChangeEmployees} displayValue={employeeStatuses.surname}{...employeeStatuses.generalStatus} className='form-control'/>
                </div>
                <div>
                <label>General status:</label>
                    <select options={generalStatuses} name='generalStatus' className='form-control' onChange= {e => setMeeting({...meeting, generalStatus: e.target.value})}>
                                <option value=''>-- Select status --</option>
                                {generalStatuses.map((e, x) => (
                                    <option key={x} value={x}>{e}</option>
                                ))};
                            </select>
                </div>
                <button type='submit' className="btn btn-success mb-3">Submit</button>
            </form>
        </div>
    )
};

export default NewMeetingAdd;