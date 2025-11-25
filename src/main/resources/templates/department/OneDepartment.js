import React, { useState } from 'react';
import { departmentService } from '../static/api';
import { Link } from 'react-router-dom';

const OneDepartment = () => {
	const [department, setDepartment] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	
	useEffect(() => {
		const fetchDepartment = async () => {
			try {
				const response = await departmentService.getById(department.idDepartment, department);
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
	
		if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Department</h2>
      <Link to="/department/update/${department.idDepartment}" className="btn btn-success mb-3">
        Edit Department
      </Link>
      <Link to="/department/remove/${department.idDepartment}" className="btn btn-danger mb-3">
        Delete Department
      </Link>
       <Link to="/department/remove/${department.idDepartment}" className="btn btn-primary mb-3">
        Back
      </Link>
       <div className="row">
        {products.length === 0 ? (
          <p>Department not found</p>
        ) : (
            <div className="col-md-4 mb-3" key={department.id}>
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
        )}
      </div>
    </div>
  );
};

export default OneDepartment;