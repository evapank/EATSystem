import React, { useEffect, useState } from 'react';
import { DepartmentService } from '../static/api';
import { Link, useParams} from 'react-router-dom';

const UpdateDepartment = () => {
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
	const employees = DepartmentService.getDepartmentEmployees();
	const {id} = useParams();

	useEffect(() => {
			const fetchDepartment = async () => {
				try {
					console.log(id);
					const responseDep = await DepartmentService.update(id, department);
					const responseMan = await DepartmentService.getManager(id);
					
					setManager(responseMan);
					setLoading(false);
				} catch (error){
					setError('cannot find department');
					setLoading(false);
					console.log(error);
				}
			};
			fetchDepartment();
		}, [id, setDepartment]);
	
		const handleChange = (e) => {
			const { name, value } = e.target;
    		setDepartment({ ...department, [name]: value });
			setManager({ ...manager, [name]: value });
		};

	const handleSubmit = (e) => {
		e.preventDeafult();
		DepartmentService.update(id, department);
		setDepartment(initialDepartment);
				
		
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
       		</table>
       		 <Link to="/department/all" className="btn btn-success mb-3">
        Submit
      </Link>
       	</form>
      </div>
      )
	};
	
	export default UpdateDepartment;