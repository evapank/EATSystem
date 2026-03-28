import { useEffect, useState } from "react";
import { OtherService, UserService } from "../../static/api";
import { useNavigate, useParams } from "react-router-dom";

const AddStatusPage = () => {
    const [employeeStatus , setEmployeeStatus]= useState({
        generalStatus : '',
        dateTimeStart : '',
        dateTimeEnd : ''
    });
    const [statusArray, setStatusArray] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const {id} = useParams();
    const navigate = useNavigate();
    
    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            console.log(id);
            await UserService.insertEmployeeStatus(id, employeeStatus);
            navigate(`/auth/user/employee/${id}`);
        } catch (error) {
            console.error('Submit error: ', error);
        }
    };

    useEffect(() => {
            const fetchDepartments = async () => {
                try {
                    const response = await OtherService.getGeneralStatus();
                    setStatusArray(response.data);
                    console.log("success");
                        setLoading(false);
                    } catch (error){
                        setError('Departments fetch error:', error);
                    }
                };
                fetchDepartments();
            }, []);
    
    if (loading) return <div>Loading...</div>;
  if (error)return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Add new status</h2>
        <form action="@{/user/employee/newStatus/{id}}" object={employeeStatus} method="post" onSubmit={handleSubmit}>
            <div>
                <label>General status:</label>
              <select options={statusArray} name='generalStatus' className='form-control' onChange= {e => setEmployeeStatus({...employeeStatus, generalStatus: e.target.value})}>
							<option value=''>-- Select status --</option>
							{employees.map((e, x) => (
								<option key={x} value={x}>{e}</option>
							))};
						</select>
            </div>
            <div>
                <label>Date time start:</label>
                <input type='datetime-local' name='dateTimeStart' className='form-control' placeholder='Enter start date and time' value={employeeStatus.dateTimeStart}
                            onChange={e => setEmployeeStatus({...employeeStatus, dateTimeStart: e.target.value})}/>
            </div>
            <div>
                <label>Date time end:</label>
                <input type='datetime-local' name='dateTimeEnd' className='form-control' placeholder='Enter end date and time' value={employeeStatus.dateTimeEnd}
                            onChange={e => setEmployeeStatus({...employeeStatus, dateTimeEnd: e.target.value})}/>
            </div>
             <button type='submit' className="btn btn-success mb-3" onSubmit={handleSubmit}>Submit</button>
        </form>
      </div>
      )
    };
    
    export default AddStatusPage;