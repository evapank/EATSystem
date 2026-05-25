import React, { useEffect, useState } from 'react';
import { EmployeeService, EosService } from '../static/api';
import { useNavigate, useParams} from 'react-router-dom';

const UpdateEmployeeOrderStatus = () => {
	const {id} = useParams();
	const [eos, setEos] = useState({
        employee : {},
        generalStatus : ''
    });
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const [employees, setEmployees] = useState([]);
	const navigate = useNavigate();

	useEffect(() => {
			const fetchEos = async () => {
				try {
					const updatedEos = await EosService.getById(id);
					const employeesResponse = await EmployeeService.getAll();
					setEos(updatedEos.data);
					setEmployees(employeesResponse.data);
					setLoading(false);
				} catch (error){
					setError('cannot find eos');
					setLoading(false);
					console.log(error);
				}
			};
			fetchEos();
		}, [id]);

	const handleSubmit = async (e) => {
		e.preventDefault();
		try {
			await EosService.update(id, eos);
			navigate('/employeeorderstatus/all/' + id);
		} catch (error) {
			console.log(error);
		}
				
		
	};
	
	if (loading) return <div>Loading...</div>;
  if (error)return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Update Employee order status</h2>
       	<form object={eos} action="@{/employeeorderstatus/update/{id}}" method="put" onSubmit={handleSubmit}>
       		<table>
			<tbody>
       			<tr>
       			<td><label>Employee:</label></td>
       			<td>
       					<select options={employees} name='employee' value={eos.employee} className='form-control' onChange={e => setEos({...eos, employee: e.target.value})}>
							<option value=''>-- Select employee --</option>
							{employees.map(e => (
								<option key={e.idEmployee} value={e.idEmployee}>{e.name} {e.surname}</option>
							))};
						</select>
       			</td>
       			</tr>
                <tr>
       			<td><label>General status:</label></td>
       			<td><input type='text' name='generalStatus' className='form-control'
       						value={eos.generalStatus}
       						onChange={e => setEos({...eos, generalStatus: e.target.value})}/> </td>
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
	
	export default UpdateEmployeeOrderStatus;