import React, { useState, useEffect } from 'react';
import { DepartmentService, EmployeeService } from '../static/api';
import { Link, useNavigate, useParams } from 'react-router-dom';

const OneEmployee = () => {
	const [employee, setEmployee] = useState({
		name : '',
        surname : '',
        position : '',
		department : {},
        isManager : false
	});
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const navigate = useNavigate();
	const {id} = useParams();
	
	useEffect(() => {
		const fetchEmployee = async () => {
			try {
				const responseEmployee = await EmployeeService.getById(id);
				console.log(responseEmployee.data);
				setEmployee(responseEmployee.data);
				setLoading(false);
				console.log("department: " + employee.department);
			} catch (error){
				setError('cannot find employee');
				setLoading(false);
				console.log(error);
			}
		};
	        fetchEmployee();
	}, []);
	
	const handleDelete = async () => {
			const confirm = window.confirm("Record will be deleted");
			if(confirm){
				await EmployeeService.delete(id);
				navigate('/employee/all');
			};
		};
	
		if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Employee</h2>
      <Link to={`/employee/update/${employee.idEmployee}`} className="btn btn-success mb-3">
        Edit Employee
      </Link>
      <Link to={`/employee/remove/${employee.idEmployee}`} onClick={e =>handleDelete(e.idEmployee)} className="btn btn-danger mb-3">
        Delete Employee
      </Link>
       <Link to={`/employee/all`} className="btn btn-primary mb-3">
        Back
      </Link>
       <div className="row">
            <div className="col-md-4 mb-3" key={employee.idEmployee}>
              <table className="table-primary table-hover">
				<tbody>
              	<tr>
              		<td className="col">ID:</td>
              		<td>{employee.idEmployee}</td>
              	</tr>
              	<tr>
              		<td className="col">Name:</td>
              		<td>{employee.name}</td>
              	</tr>
                <tr>
              		<td className="col">Surname:</td>
              		<td>{employee.surname}</td>
              	</tr>
                <tr>
              		<td className="col">Position:</td>
              		<td>{employee.position}</td>
              	</tr>
                <tr>
              		<td className="col">Department:</td>
              		<td>{employee.department.title}</td>
              	</tr>
                <tr>
              		<td className="col">Is manager?:</td>
              		<td>{employee.isManager}</td>
              	</tr>
				 </tbody>
              </table>
            </div>
        
      </div>
    </div>
  );
};

export default OneEmployee;