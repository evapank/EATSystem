import React, { useState, useEffect } from 'react';
import { OrderService } from '../static/api';
import { Link, useLocation } from 'react-router-dom';

const AllOrders = () => {
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const location = useLocation();
    
    useEffect(() => {
        const fetchOrders = async () => {
            try {
                const response = await OrderService.getAll();
                setOrders(response.data);
                console.log(orders);
                console.log(response.data);
                setLoading(false);
            } catch (error){
                setError('cannot find orders');
                setLoading(false);
                console.log(error);
            }
        };
        fetchOrders();
    }, []);
    
    const handleDelete = async (id) => {
        const confirm = window.confirm("Record will be deleted");
        if(confirm){
            await OrderService.delete(id);
            window.location.reload();
        };
    };
    
    if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Orders</h2>
      <Link to="/order/create" className="btn btn-success mb-3">
        Add New Order
      </Link>
       <div className="row">
        {orders.length === 0 ? (
          <p>No Orders found</p>
        ) : (
          orders.map(order => (
            <div>
              <table className="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">OrderNumber</th>
                        <th scope="col">Project</th>
                        <th scope="col">Order date</th>
                        <th scope="col">Date time start</th>
                        <th scope="col">Date time end</th>
                        <th scope="col">Order status</th>
                       <th scope="col">Employee</th>
                        <th scope="col">General status</th> 
                    </tr>
                </thead>
                <tbody>
                    <tr key={order.idOrder}>
                        <th scope='row'>{order.idOrder}</th>
                        <td>{order.orderNumber}</td>
                        <td>{order.project}</td>
                        <td>{order.orderDate}</td>
                        <td>{order.dateTimeStart}</td>
                        <td>{order.dateTimeEnd}</td>
                        <td>{order.employeeOrderStatus.employee.name} {order.employeeOrderStatus.employee.surname}</td>
                        <td>{order.employeeOrderStatus.generalStatus}</td>
                        <td>
                            <Link to={`/order/all/${order.idOrder}`} className="btn btn-primary">
                            View
                            </Link>
                        </td>
                        <td>
                            <Link to={`/order/update/${order.idOrder}`} className="btn btn-secondary">
                            Update
                            </Link>
                        </td>
                        <td>
                            <button onClick={e =>handleDelete(order.idOrder)} className="btn btn-danger">
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

export default AllOrders;