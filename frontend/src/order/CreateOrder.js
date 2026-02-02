import React, { useEffect, useState } from 'react';
import { EmployeeService, EosService, OrderService, ProjectService } from '../static/api';
import { Link, useNavigate} from 'react-router-dom';
import TextError from '../static/TextError';

const CreateOrder = () => {
    const [order , setOrder]= useState({
        orderNumber: 0,
        project: {},
        orderDate: null,
        dateTimeStart: null,
        dateTimeEnd: null,
        employeeOrderStatus: {}
    });
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [eoses, setEoses] = useState([]);
    const [projects, setProjects] = useState([]);
    const navigate = useNavigate();
    
    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            await OrderService.create(order);
            navigate('/order/all');
        } catch (error) {
            console.error('Submit error: ', error);
        }
    };
    useEffect(() => {
    const fetchObjects = async () => {
        try {
                const eosesResponse = await EosService.getAll();
                const projectsResponse = await ProjectService.getAll();
                setEoses(eosesResponse.data);
                setProjects(projectsResponse.data);
                setLoading(false);
            } catch (error){
                setError('Employee order service/project fetch error:', error);
            }
        };
        fetchObjects();
    }, []);
    
    if (loading) return <div>Loading...</div>;
  if (error)return <div className="alert alert-danger">{error}</div>;
  return (
    <div className="container mt-4">
      <h2>Add new order</h2>
        <form action="@{/order/create}" object={order} method="post" onSubmit={handleSubmit}>
        <div>
                <label>Order number:</label>
                <input type='number' name='orderNumber' className='form-control' placeholder='Enter project number' value={order.orderNumber}
                            onChange= {e => setOrder({...order, orderNumber: e.target.value})}/>
                </div>
                <div>
                <label>Project:</label>
                        <select options={projects} name='project' className='form-control' onChange= {e => setOrder({...order, project: e.target.value})}>
                            <option value=''>-- Select project --</option>
                            {projects.map(e => (
                                <option key={e.idProject} value={e.idProject}>{e.title}</option>
                            ))};
                        </select>
                </div>
                <div>
                <label>Order date:</label>
                <input type="date" name='orderDate' className='form-control' placeholder='Enter order date' value={order.orderDate}
                            onChange= {e => setOrder({...order, orderDate: e.target.value})}/>
                </div>
                <div>
                <label>Date time start:</label>
                <input type="datetime-local" name='dateTimeStart' className='form-control' placeholder='Enter start date and time' value={order.dateTimeStart}
                            onChange= {e => setOrder({...order, dateTimeStart: e.target.value})}/>
                </div>
                <div>
                <label>Date time end:</label>
                <input type="datetime-local" name='dateTimeEnd' className='form-control' placeholder='Enter end date' value={order.dateTimeEnd}
                            onChange= {e => setOrder({...order, dateTimeEnd: e.target.value})}/>
                </div>
                <div>
                <label>Employee order status:</label>
                        <select options={eoses} name='employeeOrderStatus' className='form-control' onChange= {e => setOrder({...order, employeeOrderStatus: e.target.value})}>
                            <option value=''>-- Select employee order status --</option>
                            {eoses.map(e => (
                                <option key={e.idEmployeeOrderStatus} value={e.idEmployeeOrderStatus}>{e.employee.name} {e.employee.surname} / {e.generalStatus}</option>
                            ))};
                        </select>
            </div>
             <button type='submit' className="btn btn-success mb-3">Submit</button>
        </form>
      </div>
      )
    };
    
    export default CreateOrder;