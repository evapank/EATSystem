import React, { useEffect, useState } from 'react';
import { DepartmentService, EmployeeService, EosService } from '../static/api';
import { Link, useNavigate} from 'react-router-dom';
import TextError from '../static/TextError';

const CreateEmployeeOrderStatus = () => {
	const [eos , setEos]= useState({
		employee : {},
        generalStatus : ''
	});
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
	const [employees, setEmployees] = useState([]);
	const navigate = useNavigate();
	
	const handleSubmit = async (e) => {
		e.preventDefault();
		try{
			await EosService.create(eos);
			navigate('/employeeorderstatus/all');
		} catch (error) {
			console.error('Submit error: ', error);
		}
	};
	useEffect(() => {
	const fetchEmployees = async () => {
		try {
				const employeesResponse = await EmployeeService.getAll();
				setEmployees(employeesResponse.data);
			console.log(employees);
				setLoading(false);
			} catch (error){
				setError('Employee fetch error:', error);
			}
		};
		fetchEmployees();
	}, []);
	
	if (loading) return <div>Loading...</div>;
  if (error)return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Add new Employee order status</h2>
       	<form action="@{/employeeorderstatus/create}" object={eos} method="post" onSubmit={handleSubmit}>
				<div>
       			<label>Employee:</label>
						<select options={employees} name='employee' className='form-control' onChange= {e => setEos({...eos, employee: e.target.value})}>
							<option value=''>-- Select employee --</option>
							{employees.map(e => (
								<option key={e.idEmployee} value={e.idEmployee}>{e.name} {e.surname}</option>
							))};
						</select>
       		</div>
            <div>
       			<label>General status:</label>
       			<input type='text' name='generalStatus' className='form-control' placeholder='Enter general status:' value={eos.generalStatus}
       						onChange= {e => setEos({...eos, generalStatus: e.target.value})}/>
       			</div>
       		 <button type='submit' className="btn btn-success mb-3">Submit</button>
       	</form>
      </div>
      )
	};
	
	export default CreateEmployeeOrderStatus;