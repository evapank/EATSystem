import React, { useState, useEffect } from 'react';
import { OrderService } from '../static/api';
import { Link, useNavigate, useParams } from 'react-router-dom';

const OneOrder = () => {
	const [order, setOrder] = useState({
		orderNumber: 0,
        project: {},
        orderDate: null,
        dateTimeStart: null,
        dateTimeEnd: null,
        employeeOrderStatus: {}
	});
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	const navigate = useNavigate();
	const {id} = useParams();
	
	useEffect(() => {
		const fetchOrder = async () => {
			try {
				const responseOrder = await OrderService.getById(id);
				setOrder(responseOrder.data);
				setLoading(false);
			} catch (error){
				setError('cannot find order');
				setLoading(false);
				console.log(error);
			}
		};
		fetchOrder();
	}, []);
	
	const handleDelete = async () => {
			const confirm = window.confirm("Record will be deleted");
			if(confirm){
				await OrderService.delete(id);
				navigate('/order/all');
			};
		};
	
		if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Order</h2>
      <Link to={`/order/update/${order.idOrder}`} className="btn btn-success mb-3">
        Edit Order
      </Link>
      <Link to={`/order/remove/${order.idOrder}`} onClick={e =>handleDelete(e.idOrder)} className="btn btn-danger mb-3">
        Delete Order
      </Link>
       <Link to={`/order/all`} className="btn btn-primary mb-3">
        Back
      </Link>
       <div className="row">
            <div className="col-md-4 mb-3" key={order.idOrder}>
              <table className="table-primary table-hover">
				<tbody>
              	<tr>
              		<td className="col">ID:</td>
              		<td>{order.idOrder}</td>
              	</tr>
                <tr>
              		<td className="col">Order number:</td>
              		<td>{order.orderNumber}</td>
              	</tr>
              	<tr>
              		<td className="col">Project:</td>
              		<td>{order.project?.title}</td>
              	</tr>
              	<tr>
              		<td className="col">Order date:</td>
              		<td>{order.orderDate}</td>
                 </tr>
                 <tr>
              		<td className="col">Date time start:</td>
              		<td>{order.dateTimeStart}</td>
                 </tr>
                 <tr>
              		<td className="col">Date time end:</td>
              		<td>{order.dateTimeEnd}</td>
                 </tr>
                 <tr>
              		<td className="col">Employee order status:</td>
              		<td>{order.employeeOrderStatus?.idEmployeeOrderStatus}</td>
                 </tr>
				 </tbody>
              </table>
            </div>
        
      </div>
    </div>
  );
};

export default OneOrder;