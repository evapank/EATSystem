import React, { useEffect, useState } from 'react';
import { DepartmentService } from '../static/api';
import { Link, useNavigate} from 'react-router-dom';
import TextError from '../static/TextError';

const CreateDepartment = () => {
	const [department , setDepartment]= useState({
		title : '',
	});
	const [manager, setManager] = useState('');
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const [employees, setEmployees] = useState([]);
	const navigate = useNavigate();
	
	const handleSubmit = async (event) => {
		event.preventDefault();
		try{
			const response = await DepartmentService.create(department, Number(manager));
			navigate('/department/all');
		} catch (error) {
			console.error('Submit error: ', error);
		}
	};
	useEffect(() => {
	const fetchEmployee = async () => {
		try {
				const employeesResponse = await DepartmentService.getEmployees();
				setEmployees(employeesResponse.data);
			console.log(employees);
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
      <h2>Add new department</h2>
       	<form action="@{/department/create}" object={department} method="post" onSubmit={handleSubmit}>
       			<div>
       			<label>Title:</label>
       			<input type='text' name='title' className='form-control' placeholder='Enter title' value={department.title}
       						onChange= {e => setDepartment({...department, title: e.target.value})}/>
       			</div>
				<div>
       			<label>Manager:</label>
						<select options={employees} name='manager' classname='form-control' onChange= {event => setManager(event.target.value)}>
							<option value=''>-- Select manager --</option>
							{employees.map(e => (
								<option key={e.idEmployee} value={e.idEmployee}>{e.name} {e.surname}</option>
							))};
						</select>
       		</div>
       		 <button type='submit' className="btn btn-success mb-3">Submit</button>
       	</form>
      </div>
      )
	};
	
	export default CreateDepartment;