import React, { useEffect, useState } from 'react';
import { EmployeeService, ProjectService } from '../static/api';
import { useNavigate, useParams} from 'react-router-dom';

const UpdateProject = () => {
	const {id} = useParams();
	const [project, setProject] = useState({
        projectNumber: 0,
        title: '',
        dateStart: new Date("01-01-1990"),
        dateEnd: new Date("31-01-1990"),
        projectManager: {}
    });
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const [employees, setEmployees] = useState([]);
	const navigate = useNavigate();

	useEffect(() => {
			const fetchProject = async () => {
				try {
					const updatedProject = await ProjectService.getById(id);
					const employeesResponse = await EmployeeService.getAll();
					setProject(updatedProject.data);
					setEmployees(employeesResponse.data);
					setLoading(false);
				} catch (error){
					setError('cannot find project');
					setLoading(false);
					console.log(error);
				}
			};
			fetchProject();
		}, [id]);

	const handleSubmit = async (e) => {
		e.preventDefault();
		try {
			await ProjectService.update(id, project);
			navigate('/project/all/' + id);
		} catch (error) {
			console.log(error);
		}
				
		
	};
	
	if (loading) return <div>Loading...</div>;
  if (error)return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Update project</h2>
       	<form object={project} action="@{/project/update/{id}}" method="put" onSubmit={handleSubmit}>
       		<table>
			<tbody>
                <tr>
       			<td><label>Project number:</label></td>
       			<td><input type='number' name='projectNumber' className='form-control'
       						value={project.projectNumber}
       						onChange={e => setProject({...project, projectNumber: e.target.value})}/> </td>
       			</tr>
       			<tr>
       			<td><label>Title:</label></td>
       			<td><input type='text' name='title' className='form-control'
       						value={project.title}
       						onChange={e => setProject({...project, title: e.target.value})}/> </td>
       			</tr>
                <tr>
       			<td><label>Date start:</label></td>
       			<td><input type='date' name='dateStart' className='form-control'
       						value={project.dateStart}
       						onChange={e => setProject({...project, dateStart: e.target.value})}/> </td>
       			</tr>
                <tr>
                <td><label>Date end:</label></td>
                <td><input type='date' name='dateEnd' className='form-control'
       						value={project.dateEnd}
       						onChange={e => setProject({...project, dateEnd: e.target.value})}/> </td>
       			</tr>
       			<tr>
       			<td><label>Project manager:</label></td>
       			<td>
       					<select options={employees} name='manager' value={project.projectManager} className='form-control' onChange= {e => setProject({...project, projectManager: e.target.value})}>
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
	
	export default UpdateProject;