import React, { use, useState } from 'react';
import { DepartmentService } from '../static/api';
import { Link } from 'react-router-dom';

const UpdateDepartment = () => {
	const [department, setDepartment] = useState({
		title : ''
	});
	const [manager, setManager] = useState({
		name : '',
		surname: ''
	});
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const [employees, setEmployees] = useState([]);
	
	const handleSubmit = async (e) => {
		e.preventDeafult();
		try {
				const responseEmp = await DepartmentService.getDepartmentEmployees();
				const responseDep = await DepartmentService.update(department.idDepartment, department);
				const responseMan = await DepartmentService.getManager(department.idDepartment);
				setDepartment(responseDep);
				setEmployees(responseEmp);
				setManager(responseMan);
				setLoading(false);
			} catch (error){
				setError('cannot update');
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
       				<select value={manager.surname} name='manager' onChange= {e => setDepartment({...department, manager: e.target.value})}>
       					{this.state.employees.map((e, myKey) => (
							<option key={myKey} value={e} text={e.name}{...e.surname}></option>
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