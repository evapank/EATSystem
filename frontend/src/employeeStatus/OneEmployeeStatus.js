import React, { useState, useEffect } from 'react';
import { EmployeeStatusService } from '../static/api';
import { Link, useNavigate, useParams } from 'react-router-dom';

const OneEmployeeStatus = () => {
    const [employeeStatus, setEmployeeStatus] = useState({
        employee : {},
        generalStatus : '',
        dateTimeStart : '',
        dateTimeEnd : ''
    });
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    const {id} = useParams();
    
    useEffect(() => {
        const fetchEmployeeStatus = async () => {
            try {
                const responseEmpSt = await EmployeeStatusService.getById(id);
                setEmployeeStatus(responseEmpSt.data);
                setLoading(false);
            } catch (error){
                setError('cannot find employee status');
                setLoading(false);
                console.log(error);
            }
        };
        fetchEmployeeStatus();
    }, []);
    
    const handleDelete = async () => {
            const confirm = window.confirm("Record will be deleted");
            if(confirm){
                await EmployeeStatusService.delete(id);
                navigate('/employeestatus/all');
            };
        };
    
        if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Employee status</h2>
      <Link to={`/employeestatus/update/${employeeStatus.idEmployeeStatus}`} className="btn btn-success mb-3">
        Edit Employee status
      </Link>
      <Link to={`/employeestatus/remove/${employeeStatus.idEmployeeStatus}`} onClick={e =>handleDelete(e.idEmployeeStatus)} className="btn btn-danger mb-3">
        Delete Employee status
      </Link>
       <Link to={`/employeestatus/all`} className="btn btn-primary mb-3">
        Back
      </Link>
       <div className="row">
            <div className="col-md-4 mb-3" key={employeeStatus.idEmployeeStatus}>
              <table className="table-primary table-hover">
                <tbody>
                <tr>
                    <td className="col">ID:</td>
                    <td>{employeeStatus.idEmployeeStatus}</td>
                </tr>
                <tr>
                    <td className="col">Employee:</td>
                    <td>{employeeStatus.employee?.name} {employeeStatus.employee?.surname}</td>
                </tr>
                <tr>
                    <td className="col">General status:</td>
                    <td>{employeeStatus.generalStatus}</td>
                 </tr>
                 <tr>
                    <td className="col">Date time start:</td>
                    <td>{employeeStatus.dateTimeStart}</td>
                 </tr>
                  <tr>
                    <td className="col">Date time end:</td>
                    <td>{employeeStatus.dateTimeEnd}</td>
                 </tr>
                 </tbody>
              </table>
            </div>
        
      </div>
    </div>
  );
};

export default OneEmployeeStatus;