import { useEffect, useState } from "react";
import { MeetingService, OtherService } from "../static/api";
import { Link, useNavigate, useParams } from "react-router-dom";

const NewMeetingTimeSelect = () => {
    const [meeting, setMeeting] = useState({
            dateTimeStart : '',
            dateTimeEnd : '',
            generalStatus: ''
        });
    const [employeeStatuses, setEmployeeStatuses] = useState([]);
    const [generalStatuses, setGeneralStatuses] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const onChangeDateTimeStart = async (dateTime) => {
        const response = await MeetingService.getEmployeeStatuses(dateTime);
        setEmployeeStatuses(response.data);
    };

    const handleSubmit = async (e) => {
           e.preventDefault();
               navigate("/meeting/create", {meeting, employeeStatuses});
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