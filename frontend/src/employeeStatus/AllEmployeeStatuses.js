import React, { useState, useEffect } from 'react';
import { DepartmentService, EmployeeStatusService } from '../static/api';
import { Link, useLocation } from 'react-router-dom';

const AllEmployeeStatuses = () => {
	const [employeeStatuses, setEmployeeStatuses] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	
	useEffect(() => {
		const fetchEmployeeStatuses = async () => {
			try {
				const response = await EmployeeStatusService.getAll();
				setEmployeeStatuses(response.data);
				setLoading(false);
			} catch (error){
				setError('cannot find employee statuses');
				setLoading(false);
				console.log(error);
			}
		};
		fetchEmployeeStatuses();
	}, []);
	
	const handleDelete = async (id) => {
		const confirm = window.confirm("Record will be deleted");
		if(confirm){
			await EmployeeStatusService.delete(id);
			window.location.reload();
		};
	};
	
	if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Employee Statuses</h2>
      <Link to="/employeestatus/create" className="btn btn-success mb-3">
        Add New Employee Status
      </Link>
       <div className="row">
        {employeeStatuses.length === 0 ? (
          <p>No Employee Statuses found</p>
        ) : (
          employeeStatuses.map(employeeStatus => (
            <div>
              <table className="table table-hover">
              	<thead>
					<tr>
              			<th scope="col">ID</th>
              			<th scope="col">Employee</th>
                        <th scope="col">General status</th>
                        <th scope="col">Date time start</th>
                        <th scope="col">Date time end</th>
					</tr>
              	</thead>
              	<tbody>
					<tr key={employeeStatus.idEmployeeStatus}>
						<th scope='row'>{employeeStatus.idEmployeeStatus}</th>
                  		<td>{employeeStatus.employee?.name} {employeeStatus.employee?.surname}</td>
                        <td>{employeeStatus.generalStatus}</td>
                        <td>{employeeStatus.dateTimeStart}</td>
                        <td>{employeeStatus.dateTimeEnd}</td>
                  		<td>
                  			<Link to={`/employeestatus/all/${employeeStatus.idEmployeeStatus}`} className="btn btn-primary">
                   	 		View
                 	 		</Link>
                  		</td>
                  		<td>
                  			<Link to={`/employeestatus/update/${employeeStatus.idEmployeeStatus}`} className="btn btn-secondary">
                   	 		Update
                 	 		</Link>
                  		</td>
                  		<td>
                  			<button onClick={e =>handleDelete(e.idEmployeeStatus)} className="btn btn-danger">
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

export default AllEmployeeStatuses;