import React, { useEffect, useState } from 'react';
import { DepartmentService, EosService, OrderService, ProjectService } from '../static/api';
import { useNavigate, useParams} from 'react-router-dom';

const UpdateOrder = () => {
	const {id} = useParams();
	const [order, setOrder] = useState({
        orderNumber: 0,
        project: {},
        orderDate: null,
        dateTimeStart: null,
        dateTimeEnd: null,
        employeeOrderStatus: {}
    });
	const [projects, setProjects] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const [eoses, setEoses] = useState([]);
	const navigate = useNavigate();

	useEffect(() => {
			const fetchOrder = async () => {
				try {
					const updatedOrder = await OrderService.getById(id);
					const projectsResponse = await ProjectService.getAll();
					const eoesesResponse = await EosService.getAll();
					setOrder(updatedOrder.data);
					setProjects(projectsResponse.data);
					setEoses(eoesesResponse.data);
					setLoading(false);
				} catch (error){
					setError('cannot find order');
					setLoading(false);
					console.log(error);
				}
			};
			fetchOrder();
		}, [id]);

	const handleSubmit = async (e) => {
		e.preventDefault();
		try {
			await OrderService.update(id, order);
			navigate('/order/all/' + id);
		} catch (error) {
			console.log(error);
		}
				
		
	};
	
	if (loading) return <div>Loading...</div>;
  if (error)return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Update order</h2>
       	<form object={order} action="@{/order/update/{id}}" method="put" onSubmit={handleSubmit}>
       		<table>
			<tbody>
                <tr>
       			<td><label>Order number:</label></td>
       			<td><input type='number' name='orderNumber' className='form-control'
       						value={order.orderNumber}
       						onChange={e => setOrder({...order, orderNumber: e.target.value})}/> </td>
       			</tr>
                <tr>
       			<td><label>Project:</label></td>
       			<td>
       					<select options={projects} name='project' value={projects} className='form-control' onChange={e => setOrder({...order, project: e.target.value})}>
							<option value=''>-- Select project --</option>
							{projects.map(e => (
								<option key={e.idProject} value={e.idProject}>{e.title}</option>
							))};
						</select>
       			</td>
       			</tr>
       			<tr>
       			<td><label>Order date:</label></td>
       			<td><input type='date' name='orderDate' className='form-control'
       						value={order.orderDate}
       						onChange={e => setOrder({...order, orderDate: e.target.value})}/> </td>
       			</tr>
                <tr>
       			<td><label>Date time start:</label></td>
       			<td><input type='datetime-local' name='dateTimeStart' className='form-control'
       						value={order.dateTimeStart}
       						onChange={e => setOrder({...order, dateTimeStart: e.target.value})}/> </td>
       			</tr>
                <tr>
       			<td><label>Date time end:</label></td>
       			<td><input type='datetime-local' name='dateTimeEnd' className='form-control'
       						value={order.dateTimeEnd}
       						onChange={e => setOrder({...order, dateTimeEnd: e.target.value})}/> </td>
       			</tr>
       			<tr>
       			<td><label>Employee Order Status:</label></td>
       			<td>
       					<select options={eoses} name='employeeOrderStatus' value={order.employeeOrderStatus} className='form-control' onChange= {e => setOrder({...order, employeeOrderStatus: e.target.value})}>
							<option value=''>-- Select employee order status --</option>
							{eoses.map(e => (
								<option key={e.idEmployeeOrderStatus} value={e.idEmployeeOrderStatus}>{e.employee.name} {e.employee.surname} / {e.generalStatus}</option>
							))};
						</select>
       			</td>
       			</tr>
			</tbody>
       		</table>
       		 <button type="submit" className="btn btn-success mb-3">
        Submit
      </button>
       	</form>
      </div>
      )
	};
	
	export default UpdateOrder;