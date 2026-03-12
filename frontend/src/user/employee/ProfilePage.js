import { useEffect, useState } from "react";
import { UserService } from "../../static/api";
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";

const ProfilePage = () => {
    const [user, setUser] = useState({
            username : '',
            employee : {}
        });
        const [projects, setProjects] = useState([]);
        const [orders, setOrders] = useState([]);
        const [loading, setLoading] = useState(true);
        const [error, setError] = useState(null);
        const {id} = useParams();

        useEffect(() => {
            const fetchEmployee = async () => {
                try {
                    const responseUser = await UserService.employeeView(id);
                    const responseProject = await UserService.employeeProjects(id);
                    const responseOrder = await UserService.employeeOrders(id);
                    setUser(responseUser.data);
                    setProjects(responseProject.data);
                    setOrders(responseOrder.data);
                    setLoading(false);
                } catch (error){
                    setError('cannot find employee');
				    setLoading(false);
				    console.log(error);
                }
            };
              fetchEmployee();
        }, []);

        if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
        <div className="container mt-4">
            <h2>{user.username}</h2>
            <div class="container">
                <h3>Department:</h3>
                <label>{user.employee?.department?.title}</label>
            </div>
            <div class="container-sm">
                <h3>Projects:</h3>
                {projects.map(e => (
					<label key={e.idProject} value={e.idProject}>
                        ID: {e.idProject}
                        Title: {e.title}
                    </label>
				))};
            </div>
            <div class="container-sm">
                <h3>Orders:</h3>
                {orders.map(e => (
					<label key={e.idOrder} value={e.idOrder}>
                        Status: {e.orderStatus}
                        Start: {e.dateTimeStart}
                        End: {e.dateTimeEnd}
                    </label>
				))};
            </div>
            <Link to={`/auth/user/employee/${id}/newStatus`} className="btn btn-primary">
                     Add new status
            </Link>
        </div>
  );
    }

export default ProfilePage;