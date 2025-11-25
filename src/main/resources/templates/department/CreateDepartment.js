import React, { useState } from 'react';
import { departmentService } from '../static/api';
import { Link } from 'react-router-dom';

const CreateDepartment = () => {
	const [values, setValues] = useState({
		title : '',
		manager: null
	});
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	
	const handleSubmit = (e) => {
		e.preventDeafult();
		try {
				const response = await departmentService.create(department);
				setDepartment(response);
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
      <h2>Add new department</h2>
       	<form action='#  th:action="@{/department/create}" th:object=${department} method="post"' onSubmit={handleSubmit}>
       		<table>
       			<tr>
       			<td><label>Title:</label></td>
       			<td><input type='text' name='title' className='form-control' placeholder='Enter title'
       						onChange= {e => setValues({...values, name: e.target.value})}/> </td>
       			<td th:if="${#fields.hasErrors('title')}" th:errors="*{title}" />
       			</tr>
       			<tr>
       			<td><label>Manager:</label></td>
       			<td>
       				<select th:field='*{manager}' name='manager' onChange= {e => setValues({...values, manager: e.target.value})}>
       					<option th:each="e: ${employees}"th:value="${e.idEmployee}" th:text="${e.name} ${e.surname}"/>
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
	
	export default CreateDepartment;