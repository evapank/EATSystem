import { useEffect, useState } from "react";
import { MeetingService, OtherService } from "../static/api";
import { Link, useNavigate, useParams } from "react-router-dom";

const NewMeetingTimeSelect = () => {
    const [meeting, setMeeting] = useState({
            dateTimeStart : '',
            dateTimeEnd : '',
        });
    const [statusArray, setStatusArray] = useState([]);
    const [employeeStatuses, setEmployeeStatuses] = useState([]);
    const [generalStatuses, setGeneralStatuses] = useState([]);
    const [employees, setEmployees] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
            const fetchMeeting = async() => {
                try {
                    const response = await OtherService.getGeneralStatus();
                    console.log(response.data);
                    setGeneralStatuses(response.data);
                } catch (error){
					setError('cannot find meeting');
					setLoading(false);
					console.log(error);
				}
            };
            fetchMeeting();
    }, []);

    const onChangeDateTimeStart = async (dateTime) => {
        const response = await MeetingService.getEmployeeStatuses(dateTime);
        setEmployeeStatuses(response.data);
    };

    const onChangeEmployees = (employee) => {
        setEmployees(previousEmployee => ({
            employees: [...previousEmployee.employees, employee]
        }));
    };

    const handleSubmit = async (e) => {
           e.preventDefault();
           try{
               await MeetingService.create(meeting);
               navigate('/dashboard');
           } catch (error) {
               console.error('Submit error: ', error);
           }
       };
 
    return (
         <div className="container mt-4">
            <h2>Create new meeting</h2>
        <form action="@{/meeting/employeestatuses}" object={meeting} method="post" onSubmit={handleSubmit}>
            <div>
                <label>Date time start:</label>
                <input type='datetime-local' name='dateTimeStart' className='form-control' placeholder='Enter start date and time' value={meeting.dateTimeStart}
                            onChange={e =>  setMeeting({...meeting, dateTimeStart: e.target.value})}/>
            </div>
            <div>
                <label>Date time end:</label>
                <input type='datetime-local' name='dateTimeEnd' className='form-control' placeholder='Enter end date and time' value={meeting.dateTimeEnd}
                            onChange={e => setMeeting({...meeting, dateTimeEnd: e.target.value})}/>
            </div>
             <Link to={{
                pathname: "/meeting/create",
                state: employeeStatuses
             }} >Next</Link>
        </form>
        </div>
    )
};

export default NewMeetingTimeSelect;