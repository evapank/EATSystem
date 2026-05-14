import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { MeetingService } from "../static/api";

const NewMeetingTimeSelect = () => {
    const [dateTimeStart, setDateTimeStart] = useState('');
    const [dateTimeEnd, setDateTimeEnd] = useState('');
    const [error, setError] = useState(null);
    const navigate = useNavigate();
     const myDateTime = {['dateTimeStart']:"200", ['dateTimeEnd']:"100"};

    const handleSubmit = async (e) => {
           e.preventDefault();
           console.log(dateTimeStart + "/" + dateTimeEnd);
           try{
                myDateTime.dateTimeStart = dateTimeStart;
                myDateTime.dateTimeEnd = dateTimeEnd;
                await MeetingService.setEmployeeStatuses(myDateTime);
                navigate("/meeting/create", {state:{dateTimeStart: dateTimeStart, dateTimeEnd: dateTimeEnd}});
           } catch (error){
                setError('cannot find meeting');
                console.log(error);
           }
       };
 
    return (
         <div className="container mt-4">
            <h2>Choose meeting time:</h2>
        <form action="@{/meeting/employeestatuses}" object={{dateTimeStart, dateTimeEnd}}
        method="post" onSubmit={handleSubmit}>
            <div>
                <label>Date time start:</label>
                <input type='datetime-local' name='dateTimeStart' className='form-control'
                        placeholder='Enter start date and time' value={dateTimeStart}
                        onChange={e =>  setDateTimeStart(e.target.value)}/>
            </div>
            <div>
                <label>Date time end:</label>
                <input type='datetime-local' name='dateTimeEnd' className='form-control'
                        placeholder='Enter end date and time' value={dateTimeEnd}
                        onChange={e => setDateTimeEnd(e.target.value)}/>
            </div>
             <button type='submit' className="btn btn-primary mb-3">Next</button>
        </form>
        </div>
    )
};

export default NewMeetingTimeSelect;