import { useLocation, useNavigate } from "react-router-dom";
import { MeetingService, OtherService } from "../static/api";
import { useEffect, useState } from "react";
import Select from "react-select";

const NewMeetingAdd = () => {
    const [meeting, setMeeting] = useState({
            dateTimeStart : '',
            dateTimeEnd : '',
            generalStatus: ''
        });
    const [employeeStatuses, setEmployeeStatuses] = useState([]);
    const [meetingStatuses, setMeetingStatuses] = useState([]);
    const [selectedEmployees, setEmployees] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    const location = useLocation();
    const myDateTime = {['dateTimeStart']:"200", ['dateTimeEnd']:"100"};
    
    useEffect(() => {
            const fetchMeeting = async() => {
                try {
                    myDateTime.dateTimeStart=location.state.dateTimeStart;
                    myDateTime.dateTimeEnd=location.state.dateTimeEnd;
                    console.log("location.dateTimeStart:", location.state.dateTimeStart);
                    console.log("location.dateTimeEnd:", location.state.dateTimeEnd);
                    console.log("myDateTime.dateTimeStart:", myDateTime.dateTimeStart);
                    console.log("myDateTime.dateTimeEnd:", myDateTime.dateTimeEnd);
                    setMeeting({...meeting, dateTimeStart: myDateTime.dateTimeStart});
                    setMeeting({...meeting, dateTimeEnd: location.state.dateTimeEnd});
                    console.log("myDateTime:", myDateTime);
                    const emStResponse = await MeetingService.setEmployeeStatuses(myDateTime);
                    const response = await OtherService.getMeetingStatuses();
                    setMeetingStatuses(response.data);

                    const employeeOptions = emStResponse.data.map((item) => ({
                        value: item.employee.idEmployee,
                        label: `${item.employee.name} ${item.employee.surname} / ${item.generalStatus}`
                    }));
                    console.log(employeeOptions);
                    setEmployeeStatuses(employeeOptions);
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
               // selectedEmployees.map(e =>{
                //    await MeetingService.addEmployeeToMeeting(e);
               // });
                navigate('/meeting/all');
            } catch (error) {
                console.error('Submit error: ', error);
            }
       };
 
        const onChangeEmployees = (employee) => {
            const newEmployee =employee.map( (e) => {
                return {surname: employee.surname};
                });
            setEmployees(newEmployee);
        };

    return (
         <div className="container mt-4">
            <h2>Create new meeting for the time:</h2>

            <form action="@{/meeting/create}" object={meeting} method="post" onSubmit={handleSubmit}>
                <div>
                    <label>Employees:</label>
                    <Select closeMenuOnSelect={false} isMulti options={employeeStatuses}>
                    </Select>
                </div>
                <div>
                <label>Meeting status:</label>
                    <select options={meetingStatuses} name='meetingStatus' className='form-control' onChange= {e => setMeeting({...meeting, generalStatus: e.target.value})}>
                                <option value=''>-- Select status --</option>
                                {meetingStatuses.map((e, x) => (
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