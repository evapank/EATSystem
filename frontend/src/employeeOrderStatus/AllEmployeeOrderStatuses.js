import React, { useState, useEffect } from 'react';
import { EosService } from '../static/api';
import { Link, useLocation } from 'react-router-dom';

const AllEmployeeOrderStatuses = () => {
	const [eoses, setEos] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const location = useLocation();
	
	useEffect(() => {
		const fetchEoses = async () => {
			try {
				const response = await EosService.getAll();
				setEos(response.data);
				setLoading(false);
			} catch (error){
				setError('cannot find employee order statuses');
				setLoading(false);
				console.log(error);
			}
		};
		fetchEoses();
	}, []);
	
	const handleDelete = async (id) => {
		const confirm = window.confirm("Record will be deleted");
		if(confirm){
			await EosService.delete(id);
			window.location.reload();
		};
	};
	
	if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Employee order statuses</h2>
      <Link to="/eos/create" className="btn btn-success mb-3">
        Add New Employee order status
      </Link>
       <div className="row">
        {eoses.length === 0 ? (
          <p>No Employee order statuses found</p>
        ) : (
          eoses.map(eos => (
            <div>
              <table className="table table-hover">
              	<thead>
					<tr>
              			<th scope="col">ID</th>
              			<th scope="col">Employee</th>
                        <th scope="col">General status</th>
					</tr>
              	</thead>
              	<tbody>
					<tr key={eos.idEmployeeOrderStatus}>
						<th scope='row'>{eos.idEmployeeOrderStatus}</th>
                  		<td>{eos.employee?.name} {eos.employee?.surname}</td>
                        <td>{eos.generalStatus}</td>
                  		<td>
                  			<Link to={`/eos/all/${eos.idEmployeeOrderStatus}`} className="btn btn-primary">
                   	 		View
                 	 		</Link>
                  		</td>
                  		<td>
                  			<Link to={`/eos/update/${eos.idEmployeeOrderStatus}`} className="btn btn-secondary">
                   	 		Update
                 	 		</Link>
                  		</td>
                  		<td>
                  			<button onClick={e =>handleDelete(eos.idEmployeeOrderStatus)} className="btn btn-danger">
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

export default AllEmployeeOrderStatuses;