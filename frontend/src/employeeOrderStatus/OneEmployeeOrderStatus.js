import React, { useState, useEffect } from 'react';
import { EosService } from '../static/api';
import { Link, useNavigate, useParams } from 'react-router-dom';

const OneEmployeeOrderStatus = () => {
	const [eos, setEos] = useState({
		employee : {},
        generalStatus : ''
	});
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const navigate = useNavigate();
	const {id} = useParams();
	
	useEffect(() => {
		const fetchEos = async () => {
			try {
				const responseEos = await EosService.getById(id);
				setEos(responseEos.data);
				setLoading(false);
			} catch (error){
				setError('cannot find employee order status');
				setLoading(false);
				console.log(error);
			}
		};
		fetchEos();
	}, []);
	
	const handleDelete = async () => {
			const confirm = window.confirm("Record will be deleted");
			if(confirm){
				await EosService.delete(id);
				navigate('/employeeorderstatus/all');
			};
		};
	
		if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Employee order status</h2>
      <Link to={`/employeeorderstatus/update/${eos.idEmployeeOrderStatus}`} className="btn btn-success mb-3">
        Edit Employee order status
      </Link>
      <Link to={`/employeeorderstatus/remove/${eos.idEmployeeOrderStatus}`} onClick={e =>handleDelete(e.idEmployeeOrderStatus)} className="btn btn-danger mb-3">
        Delete Employee Order status
      </Link>
       <Link to={`/employeeorderstatus/all`} className="btn btn-primary mb-3">
        Back
      </Link>
       <div className="row">
            <div className="col-md-4 mb-3" key={eos.idEmployeeOrderStatus}>
              <table className="table-primary table-hover">
				<tbody>
              	<tr>
              		<td className="col">ID:</td>
              		<td>{eos.idEmployeeOrderStatus}</td>
              	</tr>
              	<tr>
              		<td className="col">Employee:</td>
              		<td>{eos?.name} {eos?.surname}</td>
              	</tr>
              	<tr>
              		<td className="col">Genera status:</td>
              		<td>{eos.generalStatus}</td>
                 </tr>
				 </tbody>
              </table>
            </div>
        
      </div>
    </div>
  );
};

export default OneEmployeeOrderStatus;