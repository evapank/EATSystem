import React, { useState, useEffect } from 'react';
import { departmentService } from '../static/api';
import { Link } from 'react-router-dom';

const CreateDepartment = () => {
	const [department, setDepartment] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	
	useEffect(() => {
		const fetchDepartment = async () => {
			try {
				const response = await departmentService.create();
				setDepartment(response);
				setLoading(false);
			} catch (error){
				setError('cannot create');
				setLoading(false);
				console.log(error);
			}
		};
		fetchDepartment();
	}, []);
	
	if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Add new department</h2>
       	<form action='#  th:action="@{/department/create}" th:object=${department} method="post"'>
       		<table>
       			<tr>
       			<td><label>Title</label></td>
       			<td><input type='text' name='title' className='form-control' placeholder='Enter title'/> </td>
       			<td th:if="${#fields.hasErrors('title')}" th:errors="*{title}" />
       			</tr>
       		</table>
       		 <Link to="/department/create" className="btn btn-success mb-3">
        Submit
      </Link>
       	</form>
      </div>
      )
	};
	
	export default CreateDepartment;