import React, { useState, useEffect } from 'react';
import { DepartmentService } from '../static/api';
import { Link, useLocation } from 'react-router-dom';

const AllDepartments = () => {
	const [department, setDepartments] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const location = useLocation();
	
	useEffect(() => {
		const fetchDepartments = async () => {
			try {
				const response = await DepartmentService.getAll();
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
	
	const handleDelete = async (id) => {
		const confirm = window.confirm("Record will be deleted");
		if(confirm){
			await DepartmentService.delete(id);
			window.location.reload();
		};
	};


	
	if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Departments</h2>
      <Link to="/department/create" className="btn btn-success mb-3">
        Add New Department
      </Link>
       <div className="row">
        {department.length === 0 ? (
          <p>No Departments found</p>
        ) : (
          department.map(department => (
            <div>
              <table className="table table-hover">
              	<thead>
					<tr>
              			<th scope="col">ID</th>
              			<th scope="col">Title</th>
					</tr>
              	</thead>
              	<tbody>
					<tr key={department.idDepartment}>
						<th scope='row'>{department.idDepartment}</th>
                  		<td>{department.title}</td>
                  		<td>
                  			<Link to={`/department/all/${department.idDepartment}`} className="btn btn-primary">
                   	 		View
                 	 		</Link>
                  		</td>
                  		<td>
                  			<Link to={`/department/update/${department.idDepartment}`} className="btn btn-secondary">
                   	 		Update
                 	 		</Link>
                  		</td>
                  		<td>
                  			<button onClick={e =>handleDelete(department.idDepartment)} className="btn btn-danger">
                   	 		Delete
                 	 		</button>
                  		</td>
					</tr>
                  </tbody>
              </table>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default AllDepartments;