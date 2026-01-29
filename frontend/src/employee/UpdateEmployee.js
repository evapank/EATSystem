import React, { useEffect, useState } from 'react';
import { DepartmentService, EmployeeService } from '../static/api';
import { useNavigate, useParams} from 'react-router-dom';

const UpdateEmployee = () => {
	const {id} = useParams();
	const [employee, setEmployee] = useState({
        name : '',
        surname : '',
        position : '',
		department : null,
        isManager : false 
    });
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const [departments, setDepartments] = useState([]);
	const navigate = useNavigate();

	useEffect(() => {
			const fetchEmployee = async () => {
				try {
					const updatedEmployee = await EmployeeService.getById(id);
					const departmentsResponse = await DepartmentService.getAll();
					setEmployee(updatedEmployee.data);
					setDepartments(departmentsResponse.data);
					setLoading(false);
				} catch (error){
					setError('cannot find employee');
					setLoading(false);
					console.log(error);
				}
			};
			fetchEmployee();
		}, [id]);

	const handleSubmit = async (e) => {
		e.preventDefault();
		try {
			await EmployeeService.update(id, employee);
			navigate('/employee/all/' + id);
		} catch (error) {
			console.log(error);
		}
				
		
	};
	
	if (loading) return <div>Loading...</div>;
  if (error)return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Update employee</h2>
       	<form object={employee} action="@{/employee/update/{id}}" method="put" onSubmit={handleSubmit}>
       		<table>
			<tbody>
       			<tr>
       			<td><label>Title:</label></td>
       			<td><input type='text' name='name' className='form-control'
       						value={employee.name}
       						onChange={e => setEmployee({...employee, name: e.target.value})}/> </td>
       			</tr>
       			<tr>
       			<td><label>Manager:</label></td>
       			<td>
       					<select options={employees} name='manager' value={manager} className='form-control' onChange= {e => setManager(Number(e.target.value))}>
							<option value=''>-- Select manager --</option>
							{employees.map(e => (
								<option key={e.idEmployee} value={e.idEmployee}>{e.name} {e.surname}</option>
							))};
						</select>
       			</td>
       			</tr>
			</tbody>
       		</table>
       		 <button type="submit" className="btn btn-success mb-3">
        Submit
      </button>
       	</form>
      </div>
      )
	};
	
	export default UpdateDepartment;