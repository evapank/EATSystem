import React, { useState, useEffect } from 'react';
import { departmentService } from '../static/api';
import { Link } from 'react-router-dom';

const DepartmentList = () => {
	const [department, setDepartments] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	
	useEffect(() => {
		const fetchDepartments = async () => {
			try {
				const response = await departmentService.getAll();
				setDepartments(response.data);
				setLoading(false);
			} catch (error){
				setError('cannot find departments');
				setLoading(false);
				console.log(error);
			}
		};
		fetchDepartments();
	}, []);
	
	if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Departments</h2>
      <Link to="/department/create" className="btn btn-primary mb-3">
        Add New Department
      </Link>
       <div className="row">
        {products.length === 0 ? (
          <p>No Departments found</p>
        ) : (
          department.map(department => (
            <div className="col-md-4 mb-3" key={department.id}>
              <table class="table-primary table-hover">
              	<thead>
              		<th scope="col">ID</th>
              		<th scope="col">Title</th>
              		<th scope="col">Manager</th>
              	</thead>
              	<tbody>
              	  <td>{department.idDepartment}</td>
                  <td>{department.title}</td>
                  <td>{department.manager}</td>
                  <td>
                  	<Link to={`/departments/${department.id}`} className="btn btn-info mr-2">
                   	 View
                 	 </Link>
                  </td>
                  </tbody>
              </table>
            </div>
          ))
        )}
      </div>
    </div>
  );
};
export default DepartmentList;