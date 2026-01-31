import React, { useState, useEffect } from 'react';
import { ProjectService } from '../static/api';
import { Link, useNavigate, useParams } from 'react-router-dom';

const OneProject = () => {
	const [project, setProject] = useState({
		projectNumber: 0,
        title: '',
        dateStart: null,
        dateEnd: null,
        projectManager: {}
	});
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const navigate = useNavigate();
	const {id} = useParams();
	
	useEffect(() => {
		const fetchProject = async () => {
			try {
				const responseProj = await ProjectService.getById(id);
				setProject(responseProj.data);
				setLoading(false);
			} catch (error){
				setError('cannot find project');
				setLoading(false);
				console.log(error);
			}
		};
		fetchProject();
	}, []);
	
	const handleDelete = async () => {
			const confirm = window.confirm("Record will be deleted");
			if(confirm){
				await ProjectService.delete(id);
				navigate('/project/all');
			};
		};
	
		if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Project</h2>
      <Link to={`/project/update/${project.idProject}`} className="btn btn-success mb-3">
        Edit Project
      </Link>
      <Link to={`/project/remove/${project.idProject}`} onClick={e =>handleDelete(e.idProject)} className="btn btn-danger mb-3">
        Delete Project
      </Link>
       <Link to={`/project/all`} className="btn btn-primary mb-3">
        Back
      </Link>
       <div className="row">
            <div className="col-md-4 mb-3" key={project.idProject}>
              <table className="table-primary table-hover">
				<tbody>
              	<tr>
              		<td className="col">ID:</td>
              		<td>{project.idProject}</td>
              	</tr>
              	<tr>
              		<td className="col">Project number:</td>
              		<td>{project.projectNumber}</td>
              	</tr>
              	<tr>
              		<td className="col">Title:</td>
              		<td>{project.title}</td>
                 </tr>
                 <tr>
              		<td className="col">Date start:</td>
              		<td>{project.dateStart}</td>
                 </tr>
                 <tr>
              		<td className="col">Date end:</td>
              		<td>{project.dateEnd}</td>
                 </tr>
                 <tr>
              		<td className="col">Project manager:</td>
              		<td>{project.projectManager.name} {project.projectManager.surname}</td>
                 </tr>
				 </tbody>
              </table>
            </div>
        
      </div>
    </div>
  );
};

export default OneProject;