import React, { useState, useEffect } from 'react';
import { DepartmentService, ProjectService } from '../static/api';
import { Link, useLocation } from 'react-router-dom';

const AllProjects = () => {
	const [projects, setProjects] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const location = useLocation();
	
	useEffect(() => {
		const fetchProjects = async () => {
			try {
				const response = await ProjectService.getAll();
				setProjects(response.data);
				setLoading(false);
			} catch (error){
				setError('cannot find projects');
				setLoading(false);
				console.log(error);
			}
		};
		fetchProjects();
	}, []);
	
	const handleDelete = async (id) => {
		const confirm = window.confirm("Record will be deleted");
		if(confirm){
			await ProjectService.delete(id);
			window.location.reload();
		};
	};
	
	if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Projects</h2>
      <Link to="/project/create" className="btn btn-success mb-3">
        Add New Projects
      </Link>
       <div className="row">
        {projects.length === 0 ? (
          <p>No Projects found</p>
        ) : (
          projects.map(project => (
            <div>
              <table className="table table-hover">
              	<thead>
					<tr>
              			<th scope="col">ID</th>
              			<th scope="col">Number</th>
                        <th scope="col">Title</th>
                        <th scope="col">Date start</th>
                        <th scope="col">Date end</th>
                        <th scope="col">Project manager</th>
					</tr>
              	</thead>
              	<tbody>
					<tr key={project.idProject}>
						<th scope='row'>{project.idProject}</th>
                  		<td>{project.projectNumber}</td>
                        <td>{project.title}</td>
                        <td>{project.dateStart}</td>
                        <td>{project.dateEnd}</td>
                        <td>{project.projectManager?.name} {project.projectManager?.surname}</td>
                  		<td>
                  			<Link to={`/project/all/${project.idProject}`} className="btn btn-primary">
                   	 		View
                 	 		</Link>
                  		</td>
                  		<td>
                  			<Link to={`/project/update/${project.idProject}`} className="btn btn-secondary">
                   	 		Update
                 	 		</Link>
                  		</td>
                  		<td>
                  			<button onClick={e =>handleDelete(project.idProject)} className="btn btn-danger">
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

export default AllProjects;