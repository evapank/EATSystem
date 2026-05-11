import React, { useEffect, useState } from 'react';
import { EmployeeService, ProjectService } from '../static/api';
import { Link, useNavigate} from 'react-router-dom';
import TextError from '../static/TextError';

const CreateProject = () => {
    const [project , setProject]= useState({
        projectNumber: 0,
        title: '',
        dateStart: new Date("01-01-1990"),
        dateEnd: new Date("31-01-1990"),
        projectManager: {}
    });
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [employees, setEmployees] = useState([]);
    const navigate = useNavigate();
    
    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            await ProjectService.create(project);
            navigate('/project/all');
        } catch (error) {
            console.error('Submit error: ', error);
        }
    };
    useEffect(() => {
    const fetchEmployee = async () => {
        try {
                const employeesResponse = await EmployeeService.getAll();
                setEmployees(employeesResponse.data);
            console.log(employees);
                setLoading(false);
            } catch (error){
                setError('Employee fetch error:', error);
            }
        };
        fetchEmployee();
    }, []);
    
    if (loading) return <div>Loading...</div>;
  if (error)return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Add new project</h2>
        <form action="@{/project/create}" object={project} method="post" onSubmit={handleSubmit}>
                <div>
                <label>Number:</label>
                <input type='number' name='projectNumber' className='form-control' placeholder='Enter project number' value={project.projectNumber}
                            onChange= {e => setProject({...project, projectNumber: e.target.value})}/>
                </div>
                <div>
       			<label>Title:</label>
       			<input type='text' name='title' className='form-control' placeholder='Enter name' value={project.title}
       						onChange= {e => setProject({...project, title: e.target.value})}/>
       			</div>
                <div>
                <label>Date start:</label>
       			<input type='date' name='dateStart' className='form-control'  value={project.dateStart}
       						onChange= {e => setProject({...project, dateStart: e.target.value})}/>
       			</div>
                <div>
                <label>Date end:</label>
       			<input type='date' name='dateEnd' className='form-control'  value={project.dateEnd}
       						onChange= {e => setProject({...project, dateEnd: e.target.value})}/>
       			</div>
                <div>
                <label>Project Manager:</label>
                        <select options={employees} name='projectManager' className='form-control' onChange= {e => setProject({...project, projectManager: e.target.value})}>
                            <option value=''>-- Select project manager --</option>
                            {employees.map(e => (
                                <option key={e.idEmployee} value={e.idEmployee}>{e.name} {e.surname}</option>
                            ))};
                        </select>
            </div>
             <button type='submit' className="btn btn-success mb-3">Submit</button>
        </form>
      </div>
      )
    };
    
    export default CreateProject;