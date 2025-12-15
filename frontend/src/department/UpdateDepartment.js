import React, { use, useState } from 'react';
import { DepartmentService } from '../static/api';
import { Link } from 'react-router-dom';

const UpdateDepartment = () => {
	const [department, setDepartment] = useState({
		title : '',
		manager: null
	});
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const [employees, setEmployees] = useState([]);
	
	const handleSubmit = async (e) => {
		e.preventDeafult();
		try {
				const response = await DepartmentService.update(department.idDepartment, department, employees);
				setDepartment(response.data);
				setEmployees(response.data);
				setLoading(false);
			} catch (error){
				setError('cannot create');
				setLoading(false);
				console.log(error);
			}
	};
	
	if (loading) return <div>Loading...</div>;
  if (error)return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Update department</h2>
       	<form action="@{/department/create}" object={department} method="post" onSubmit={handleSubmit}>
       		<table>
       			<tr>
       			<td><label>Title:</label></td>
       			<td><input type='text' name='title' className='form-control' placeholder='Enter title'
       						value={department.title}
       						onChange= {e => setDepartment({...department, name: e.target.value})}/> </td>
       			</tr>
       			<tr>
       			<td><label>Manager:</label></td>
       			<td>
       				<select value='*{manager}' name='manager' onChange= {e => setDepartment({...department, manager: e.target.value})}>
       					{employees.map((e) => (
							<option value={e} text={e.name}{...e.surname}></option>
						))};
       				</select>
       			</td>
       			</tr>
       		</table>
       		 <Link to="/department/all" className="btn btn-success mb-3">
        Submit
      </Link>
       	</form>
      </div>
      )
	};
	
	export default UpdateDepartment;