import React, { useEffect, useState } from 'react';
import { DepartmentService, EmployeeService } from '../static/api';
import { Link, useNavigate} from 'react-router-dom';
import TextError from '../static/TextError';

const CreateEmployee = () => {
	const [employee , setEmployee]= useState({
		name : '',
        surname : '',
        position : '',
		department : '',
        isManager : false
	});
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const navigate = useNavigate();
	const [departments, setDepartments] = useState([]);
	
	const handleSubmit = async (e) => {
		e.preventDefault();
		try{
			await EmployeeService.create(employee);
			navigate('/employee/all');
		} catch (error) {
			console.error('Submit error: ', error);
		}
	};

	useEffect(() => {
		const fetchDepartments = async () => {
			try {
					const departmentsResponse = await DepartmentService.getAll();
					setDepartments(departmentsResponse.data);
				console.log(departments);
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
      <h2>Add new department</h2>
       	<form action="@{/employee/create}" object={employee} method="post" onSubmit={handleSubmit}>
       			<div>
       			<label>Name:</label>
       			<input type='text' name='name' className='form-control' placeholder='Enter name' value={employee.name}
       						onChange= {e => setEmployee({...employee, name: e.target.value})}/>
       			</div>
                <div>
       			<label>Surname:</label>
       			<input type='text' name='surname' className='form-control' placeholder='Enter surname' value={employee.surname}
       						onChange= {e => setEmployee({...employee, surname: e.target.value})}/>
       			</div>
                <div>
       			<label>Position:</label>
       			<input type='text' name='position' className='form-control' placeholder='Enter position' value={employee.position}
       						onChange= {e => setEmployee({...employee, position: e.target.value})}/>
       			</div>
				<div>
       			<label>Department:</label>
						<select options={departments} name='departments' className='form-control' onChange= {e => setEmployee({...employee, department: e.target.value})}>
							<option value=''>-- Select department --</option>
							{departments.map(e => (
								<option key={e.idDepartment} value={e.idDepartment}>{e.title}</option>
							))};
						</select>
       		</div>
            <div>
       			<label>Is manager?:</label>
       			<input type='checkbox' value={employee.isManager}
       						onClick= {() => setEmployee({...employee, isManager: true})}/>
       		</div>
       		 <button type='submit' className="btn btn-success mb-3">Submit</button>
       	</form>
      </div>
      )
	};
	
	export default CreateEmployee;