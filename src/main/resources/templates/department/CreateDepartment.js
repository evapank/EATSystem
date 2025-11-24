import React, { useState, useEffect } from 'react';
import { departmentService } from '../static/api';
import { Link } from 'react-router-dom';

const CreateDepartment = () => {
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	
	useEffect(() => {
		const fetchDepartment = async () => {
			try {
				const response = await departmentService.create();
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
       	<form>
       		<div>
       			<label>Title</label>
       			<input type='text' name='title' className='form-control' placeholder='Enter title'/>
       		</div>
       	</form>
      </div>
      )
	};
	
	export default CreateDepartment;