import { useEffect, useState } from "react";
import { MeetingService, OtherService } from "../static/api";
import { Link, useNavigate, useParams } from "react-router-dom";
import Moment from "react-moment";
import moment from "moment";

const NewMeetingTimeSelect = () => {
    const [meeting, setMeeting] = useState({
            dateTimeStart : moment(new Date()).format("YYYY-MM-DDTHH:mm"),
            dateTimeEnd : moment(new Date()).format("YYYY-MM-DDTHH:mm"),
            generalStatus: ''
        });
    const [employeeStatuses, setEmployeeStatuses] = useState([]);
    const [generalStatuses, setGeneralStatuses] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
           e.preventDefault();
           const response = await MeetingService.getEmployeeStatuses(e.dateTimeStart);
            setEmployeeStatuses(response.data);
            navigate("/meeting/create", {meeting, employeeStatuses});
       };
 
    return (
         <div className="container mt-4">
            <h2>Choose meeting time:</h2>
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
             <button type='submit' className="btn btn-primary mb-3">Next</button>
        </form>
        </div>
    )
};

export default NewMeetingTimeSelect;