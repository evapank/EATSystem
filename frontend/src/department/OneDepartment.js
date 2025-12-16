import React, { useState, useEffect } from 'react';
import { DepartmentService } from '../static/api';
import { Link, Navigate, useNavigate } from 'react-router-dom';

const OneDepartment = () => {
	const [department, setDepartment] = useState();
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const navigate = useNavigate();
	
	useEffect(() => {
		const fetchDepartment = async () => {
			try {
				const response = await DepartmentService.getById(department.idDepartment);
				setDepartment(response.data);
				setLoading(false);
			} catch (error){
				setError('cannot find department');
				setLoading(false);
				console.log(error);
			}
		};
		fetchDepartment();
	}, []);
	
	const handleDelete = (id) => {
		const confirm = window.confirm("Record will be deleted");
		if(confirm){
			DepartmentService.delete(id).then(
			navigate(DepartmentService.getAll()))
		};
	};
	
		if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Department</h2>
      <Link to={`/department/update/${department.idDepartment}`} className="btn btn-success mb-3">
        Edit Department
      </Link>
      <Link to={`/department/remove/${department.idDepartment}`} onClick={e =>handleDelete(e.idDepartment)} className="btn btn-danger mb-3">
        Delete Department
      </Link>
       <Link to={`/department/all`} className="btn btn-primary mb-3">
        Back
      </Link>
       <div className="row">
            <div className="col-md-4 mb-3" key={department.idDepartment}>
              <table class="table-primary table-hover">
              	<tr>
              		<td scope="col">ID:</td>
              		<td>{department.idDepartment}</td>
              	</tr>
              	<tr>
              		<td scope="col">Title:</td>
              		<td>{department.title}</td>
              	</tr>
              	<tr>
              		<td scope="col">Manager:</td>
              		<td>{department.manager}</td>
                 </tr>
              </table>
            </div>
        )
      </div>
    </div>
  );
};

export default OneDepartment;