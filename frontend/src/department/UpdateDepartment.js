import React, { useEffect, useState } from 'react';
import { DepartmentService } from '../static/api';
import { useNavigate, useParams} from 'react-router-dom';

const UpdateDepartment = () => {
	const {id} = useParams();
	const [department, setDepartment] = useState({
		title : ''
	});
	const [manager, setManager] = useState('');
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const [employees, setEmployees] = useState([]);
	const navigate = useNavigate();

	useEffect(() => {
			const fetchDepartment = async () => {
				try {
					console.log(id);
					const updatedDepartment = await DepartmentService.getById(id);
					const managerResponse = await DepartmentService.getManager(id);
					const employeesResponse = await DepartmentService.getEmployees();
					setDepartment(updatedDepartment.data);
					setManager(managerResponse.data);
					setEmployees(employeesResponse.data);
					setLoading(false);
				} catch (error){
					setError('cannot find department');
					setLoading(false);
					console.log(error);
				}
			};
			fetchDepartment();
		}, [id]);

	const handleSubmit = async (e) => {
		e.preventDefault();
		try {
			await DepartmentService.update(id, {...department, managerId: Number(manager)});
			navigate('/department/all');
		} catch (error) {
			console.log(error);
		}
				
		
	};
	
	if (loading) return <div>Loading...</div>;
  if (error)return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Update department</h2>
       	<form object={department} method="put" onSubmit={handleSubmit}>
       		<table>
			<tbody>
       			<tr>
       			<td><label>Title:</label></td>
       			<td><input type='text' name='title' className='form-control'
       						value={department.title}
       						onChange={e => setDepartment({...department, title: e.target.value})}/> </td>
       			</tr>
       			<tr>
       			<td><label>Manager:</label></td>
       			<td>
       					<select options={employees} name='manager' className='form-control' onChange= {e => setManager(Number(e.target.value))}>
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