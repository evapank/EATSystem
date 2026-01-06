import React, { useState, useEffect } from 'react';
import { DepartmentService } from '../static/api';
import { Link, useNavigate, useParams } from 'react-router-dom';

const OneDepartment = () => {
	const [department, setDepartment] = useState({
		title : ''
	});
	const [manager, setManager] = useState({
		name : '',
		surname: ''
	});
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const navigate = useNavigate();
	const {id} = useParams();
	
	useEffect(() => {
		const fetchDepartment = async () => {
			try {
				console.log(id);
				const responseMan = await DepartmentService.getManager(id);
				const responseDep = await DepartmentService.getById(id);
				console.log(responseMan.data);
				setDepartment(responseDep.data);
				setManager(responseMan.data);
				setLoading(false);
			} catch (error){
				setError('cannot find department');
				setLoading(false);
				console.log(error);
			}
		};
		fetchDepartment();
	}, []);
	
	const handleDelete = () => {
		const confirm = window.confirm("Record will be deleted");
		if(confirm){
			console.log(DepartmentService.delete(id));
			DepartmentService.delete(id);
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
              <table className="table-primary table-hover">
				<tbody>
              	<tr>
              		<td className="col">ID:</td>
              		<td>{department.idDepartment}</td>
              	</tr>
              	<tr>
              		<td className="col">Title:</td>
              		<td>{department.title}</td>
              	</tr>
              	<tr>
              		<td className="col">Manager:</td>
              		<td>{manager.name} {manager.surname}</td>
                 </tr>
				 </tbody>
              </table>
            </div>
        
      </div>
    </div>
  );
};

export default OneDepartment;