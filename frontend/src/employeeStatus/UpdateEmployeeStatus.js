import React, { useEffect, useState } from 'react';
import { EmployeeService, EmployeeStatusService, OtherService } from '../static/api';
import { useNavigate, useParams} from 'react-router-dom';

const UpdateEmployeeStatus = () => {
	const {id} = useParams();
	const [employeeStatus, setEmployeeStatus] = useState({
        employee : {},
        generalStatus : '',
        dateTimeStart : '',
        dateTimeEnd : ''
    });
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const [employees, setEmployees] = useState([]);
	const [statusArray, setStatusArray] = useState([]);
	const navigate = useNavigate();

	useEffect(() => {
			const fetchEmployeeStatus = async () => {
				try {
					const updatedEmpSt = await EmployeeStatusService.getById(id);
					const employeesResponse = await EmployeeService.getAll();
					const generalStatusResponse = await OtherService.getGeneralStatus();
					setStatusArray(generalStatusResponse.data);
					setEmployeeStatus(updatedEmpSt.data);
					setEmployees(employeesResponse.data);
					setLoading(false);
				} catch (error){
					setError('cannot find employee status');
					setLoading(false);
					console.log(error);
				}
			};
			fetchEmployeeStatus();
		}, [id]);

	const handleSubmit = async (e) => {
		e.preventDefault();
		try {
			await EmployeeStatusService.update(id, employeeStatus);
			navigate('/employeestatus/all/' + id);
		} catch (error) {
			console.log(error);
		}
				
		
	};
	
	if (loading) return <div>Loading...</div>;
  if (error)return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Update employee status</h2>
       	<form object={employeeStatus} action="@{/employeestatus/update/{id}}" method="put" onSubmit={handleSubmit}>
       		<table>
			<tbody>
                <tr>
       			<td><label>Employee:</label></td>
       			<td>
       					<select options={employees} name='employee' value={employeeStatus.employee} className='form-control' onChange= {e => setEmployeeStatus({...employeeStatus, employee: e.target.value})}>
							<option value=''>-- Select employee --</option>
							{employees.map(e => (
								<option key={e.idEmployee} value={e.idEmployee}>{e.name} {e.surname}</option>
							))};
						</select>
       			</td>
       			</tr>
       			<tr>
       			<td><label>General status:</label></td>
       			<td> <select options={statusArray} name='generalStatus' className='form-control' value={employeeStatus.generalStatus} onChange= {e => setEmployeeStatus({...employeeStatus, generalStatus: e.target.value})}>
							<option value=''>-- Select status --</option>
							{statusArray.map((e, x) => (
								<option key={x} value={x}>{e}</option>
							))};
						</select>
				</td>
       			</tr>
                <tr>
       			<td><label>Date time start:</label></td>
       			<td><input type='datetime-local' name='dateTimeStart' className='form-control'
       						value={employeeStatus.dateTimeStart}
       						onChange={e => setEmployeeStatus({...employeeStatus, dateTimeStart: e.target.value})}/> </td>
       			</tr>
                <tr>
       			<td><label>Date time end:</label></td>
       			<td><input type='datetime-local' name='dateTimeEnd' className='form-control'
       						value={employeeStatus.dateTimeEnd}
       						onChange={e => setEmployeeStatus({...employeeStatus, dateTimeEnd: e.target.value})}/> </td>
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
	
	export default UpdateEmployeeStatus;