import { useLocation, useNavigate } from "react-router-dom";
import { MeetingService, OtherService } from "../static/api";
import { useEffect, useState } from "react";
import Select from "react-select/base";

const NewMeetingAdd = () => {
    const [meeting, setMeeting] = useState({
            dateTimeStart : '',
            dateTimeEnd : '',
            generalStatus: ''
        });
    const [employeeStatuses, setEmployeeStatuses] = useState({
        employee: '',
        generalStatus: '',
        dateTimeStart: '',
        dateTimeEnd: ''
    });
    const [generalStatuses, setGeneralStatuses] = useState([]);
    const [selectedEmployees, setEmployees] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    const location = useLocation();
    const myObject = {['dateTimeStart']:"200", ['dateTimeEnd']:"100"};
    
    useEffect(() => {
            const fetchMeeting = async() => {
                try {
                    setMeeting({...meeting, dateTimeStart: location.state.dateTimeStart});
                    setMeeting({...meeting, dateTimeEnd: location.state.dateTimeEnd});
                    console.log(location.state.dateTimeStart + "+ " + location.state.dateTimeEnd)
                    const response = await OtherService.getGeneralStatus();
                    myObject.dateTimeStart=location.state.dateTimeStart
                    myObject.dateTimeEnd=location.state.dateTimeEnd
                    const emStResponse = await MeetingService.getEmployeeStatuses(myObject);
                    setGeneralStatuses(response.data);
                    console.log("employeestatusresponse:",emStResponse.data)

                    //const tempEmpSt = emStResponse.data.map((empSt) => {
                    //    return {name: empSt, surname: empSt}
                   // });
                    setEmployeeStatuses(emStResponse.data);

                    console.log(employeeStatuses);
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

        const empStOptions = () => {
            const optionsArray = [{value: "value", label: "label"}];
            employeeStatuses.map(employeeStatus =>
                optionsArray.value = employeeStatus.idEmployeeStatus,
                optionsArray.label = employeeStatus.employee.name + " " + employeeStatus.employee.surname);
            return optionsArray;
        };

    return (
         <div className="container mt-4">
            <h2>Create new meeting for the time:</h2>

            <form action="@{/meeting/create}" object={meeting} method="post" onSubmit={handleSubmit}>
                <div>
                    <label>Employees:</label>
                    <Select isMulti options={empStOptions}>
                    </Select>
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