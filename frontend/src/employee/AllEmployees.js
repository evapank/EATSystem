import React, { useState, useEffect } from 'react';
import { EmployeeService } from '../static/api';
import { Link, useLocation } from 'react-router-dom';

const AllEmployees = () => {
	const [employee, setEmployees] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const location = useLocation();
	
	useEffect(() => {
		const fetchEmployees = async () => {
			try {
				const response = await EmployeeService.getAll();
				setEmployees(response.data);
				setLoading(false);
			} catch (error){
				setError('cannot find employees');
				setLoading(false);
				console.log(error);
			}
		};
		fetchEmployees();
	}, []);
	
	const handleDelete = async (id) => {
		const confirm = window.confirm("Record will be deleted");
		if(confirm){
			await EmployeeService.delete(id);
			window.location.reload();
		};
	};


	
	if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Employees</h2>
      <Link to="/employee/create" className="btn btn-success mb-3">
        Add New Employee
      </Link>
       <div className="row">
        {employee.length === 0 ? (
          <p>No Employees found</p>
        ) : (
          employee.map(employee => (
            <div>
              <table className="table-primary table-hover">
              	<thead>
					<tr className='container-fluid'>
              			<th scope="col">ID</th>
              			<th scope="col">Name</th>
              			<th scope="col">Surname</th>
                    <th scope="col">Position</th>
                    <th scope="col">Department</th>
                    <th scope="col">Is manager?</th>
					</tr>
              	</thead>
              	<tbody>
					<tr key={employee.idEmployee}>
						<td scope='row'>{employee.idEmployee}</td>
                  		<td>{employee.name}</td>
                  		<td>{employee.surname}</td>
                      <td>{employee.position}</td>
                      <td>{employee.department.title}</td>
                      <td>{employee.isManager}</td>
                  		<td>
                  			<Link to={`/employee/all/${employee.idEmployee}`} className="btn btn-primary">
                   	 		View
                 	 		</Link>
                  		</td>
                  		<td>
                  			<Link to={`/employee/update/${employee.idEmployee}`} className="btn btn-secondary">
                   	 		Update
                 	 		</Link>
                  		</td>
                  		<td>
                  			<button onClick={e =>handleDelete(e.idEmployee)} className="btn btn-danger">
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

export default AllEmployees;