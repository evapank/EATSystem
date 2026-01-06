import React, { useEffect, useState } from 'react';
import { DepartmentService } from '../static/api';
import { Link, useNavigate, useParams} from 'react-router-dom';

const UpdateDepartment = () => {
	const {id} = useParams();
	const initialDepartment = useState({
		title : ''
	});
	const [department, setDepartment] = initialDepartment;
	const [manager, setManager] = useState({
		name : '',
		surname: ''
	});
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const [employees, setEmployees] = useState([]);
	const navigate = useNavigate();

	useEffect(() => {
			const fetchDepartment = async () => {
				try {
					console.log(id);
					const responseDep = await DepartmentService.update(id, department)
					const responseMan = await DepartmentService.getManager(id);
					const responseEmp = await DepartmentService.getDepartmentEmployees(id);
					setDepartment(responseDep.data);
					setManager(responseMan.data);
					setEmployees(responseEmp.data);
					setLoading(false);
				} catch (error){
					setError('cannot find department');
					setLoading(false);
					console.log(error);
				}
			};
			fetchDepartment();
		}, [id, setDepartment, setManager, setEmployees]);
	
		const handleChange = (e) => {
			const { name, value } = e.target;
    		setDepartment({ ...department, [name]: value });
			setManager({ ...manager, [name]: value });
		};

	const handleSubmit = (e) => {
		e.preventDeafult();
		DepartmentService.update(id, department);
		setDepartment(initialDepartment);
		navigate(DepartmentService.getAll);
				
		
	};
	
	if (loading) return <div>Loading...</div>;
  if (error)return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Update department</h2>
       	<form action="@{/department/create}" object={department} method="post" onSubmit={handleSubmit}>
       		<table>
				<tbody>
       			<tr>
       			<td><label>Title:</label></td>
       			<td><input type='text' name='title' className='form-control'
       						value={department.title}
       						onChange={handleChange}/> </td>
       			</tr>
       			<tr>
       			<td><label>Manager:</label></td>
       			<td>
       					<select value={manager.name}{...manager.surname} name='manager' onChange= {handleChange}>
       						{employees.map((e, myKey) => (
							<option key={myKey} value={e} text={e.name}{...e.surname}></option>
						))};
       				</select>
       			</td>
       			</tr>
				</tbody>
       		</table>
       		 <Link to="/department/all" className="btn btn-success mb-3">
        Submit
      </Link>
       	</form>
      </div>
      )
	};
	
	export default UpdateDepartment;