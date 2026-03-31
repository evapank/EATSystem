import { useEffect, useState } from "react";
import { MeetingService, OtherService } from "../static/api";
import { useParams } from "react-router-dom";

const NewMeeting = () => {
    const [meeting, setMeeting] = useState({
            dateTimeStart : '',
            dateTimeEnd : '',
            generalStatus : ''
        });
    const [statusArray, setStatusArray] = useState([]);
    const [employeeStatuses, setEmployeeStatuses] = useState([]);
    const [generalStatuses, setGeneralStatuses] = useState([]);
    const [employees, setEmployees] = useState([]);

    useEffect(() => {
            const fetchMeeting = async() => {
                try {
                    const response = await OtherService.getGeneralStatus();
                    setStatusArray(response.data);
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
        <form action="@{/meeting/create}" object={meeting} method="post" onSubmit={handleSubmit}>
            <div>
                <label>Date time start:</label>
                <input type='datetime-local' name='dateTimeStart' className='form-control' placeholder='Enter start date and time' value={meeting.dateTimeStart}
                            onChange={e => {onChangeDateTimeStart; setMeeting({...meeting, dateTimeStart: e.target.value})}}/>
            </div>
            <div>
                <label>Date time end:</label>
                <input type='datetime-local' name='dateTimeEnd' className='form-control' placeholder='Enter end date and time' value={meeting.dateTimeEnd}
                            onChange={e => setMeeting({...meeting, dateTimeEnd: e.target.value})}/>
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
            <div>
                <label>Employees:</label>
                <select multiple={true} options={employeeStatuses} name='employees' className='form-control' onChange={onChangeEmployees}>
                    <option value=''>-- Select Employee --</option>
							{employeeStatuses.map(e => (
								<option key={employeeStatuses.employee.idEmployee} value={employeeStatuses.employee.idEmployee}>{e.employee.name} {e.employee.surname} / {e.generalStatus}</option>
							))};
                </select>
            </div>
             <button type='submit' className="btn btn-success mb-3">Submit</button>
        </form>
    )
};

export default NewMeeting;