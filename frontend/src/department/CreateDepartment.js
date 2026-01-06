import React, { useEffect, useState } from 'react';
import { DepartmentService } from '../static/api';
import { Link} from 'react-router-dom';
import TextError from '../static/TextError';

const CreateDepartment = () => {
	const [department , setDepartment]= useState({
		title : '',
	});
	const [manager, setManager] = useState({
			name : '',
			surname: ''
		});
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const [employees, setEmployees] = useState([]);
	
	const handleSubmit = (event) => {
		event.preventDefault();
	};
	useEffect(() => {
	const fetchDepartment = async () => {
		try {
				console.log("dfdfn");
				const employeesResponse = await DepartmentService.getEmployees();
				console.log("jsdosijdo");
				console.log(employeesResponse);
				const response = await DepartmentService.create(department);
				setDepartment(response);
				setEmployees([employeesResponse]);
				setLoading(false);
			} catch (error){
				setError('cannot create');
				setLoading(false);
				console.log(error);
			}
		};
		fetchDepartment();
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
       						onChange= {e => setDepartment({...department, name: e.target.value})}/>
       			</div>
				<div>
       			<label>Manager:</label>
			
					
							{employees.map((e) => (
							<select value="*{manager}" name='manager' onChange= {event => setManager({...manager, name: event.target.value, surname: event.target.value})}>
								<option key={e.idEmployee} value={e} text={e.name}{...e.surname}></option>
							</select>
						))}
					
				
       		</div>
       		 <Link to="/department/all" className="btn btn-success mb-3">
        Submit
      </Link>
       	</form>
      </div>
      )
	};
	
	export default CreateDepartment;