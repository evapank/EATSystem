import React, { useEffect, useState } from 'react';
import { EmployeeService, EmployeeStatusService } from '../static/api';
import { Link, useNavigate} from 'react-router-dom';
import TextError from '../static/TextError';

const CreateEmployeeStatus = () => {
    const [employeeStatus , setEmployeeStatus]= useState({
        employee : {},
        generalStatus : '',
        dateTimeStart : '',
        dateTimeEnd : ''
    });
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [employees, setEmployees] = useState([]);
    const navigate = useNavigate();
    
    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            await EmployeeStatusService.create(employeeStatus);
            navigate('/employeestatus/all');
        } catch (error) {
            console.error('Submit error: ', error);
        }
    };
    useEffect(() => {
    const fetchEmployee = async () => {
        try {
                const employeesResponse = await EmployeeService.getAll();
                setEmployees(employeesResponse.data);
                setLoading(false);
            } catch (error){
                setError('Employee fetch error:', error);
            }
        };
        fetchEmployee();
    }, []);
    
    if (loading) return <div>Loading...</div>;
  if (error)return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Add new employee status</h2>
        <form action="@{/employeestatus/create}" object={employeeStatus} method="post" onSubmit={handleSubmit}>
                <div>
                <label>Employee:</label>
                        <select options={employees} name='employee' className='form-control' onChange={e => setEmployeeStatus({...employeeStatus, employee: e.target.value})}>
                            <option value=''>-- Select Employee --</option>
                            {employees.map(e => (
                                <option key={e.idEmployee} value={e.idEmployee}>{e.name} {e.surname}</option>
                            ))};
                        </select>
            </div>
            <div>
                <label>General status:</label>
                <input type='text' name='generalStatus' className='form-control' placeholder='Enter general status' value={employeeStatus.generalStatus}
                            onChange={e => setEmployeeStatus({...employeeStatus, generalStatus: e.target.value})}/>
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
             <button type='submit' className="btn btn-success mb-3">Submit</button>
        </form>
      </div>
      )
    };
    
    export default CreateEmployeeStatus;