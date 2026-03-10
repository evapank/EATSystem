import { useEffect, useState } from "react";
import { UserService } from "../../static/api";

const ProfilePage = () => {
    const [user, setUser] = useState({
            username : '',
            employee : {}
        });
        const [projects, setProjects] = useState([]);
        const [loading, setLoading] = useState(true);
        const {id} = useParams();

        useEffect(() => {
            const fetchEmployee = async () => {
                try {
                    const responseUser = await UserService.employeeView(id);
                    const responseProject = await UserService.employeeProjects(id);
                    setUser(responseUser.data);
                    setProjects(responseProject.data);
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
            <div class="container">
                <label>Department: {user.employee?.department?.title}</label>
            </div>
            <div class="container-sm">
                <label class="fs-3 fw-semibold">Projects:</label>
                {projects.map(e => (
					<label key={e.idProject} value={e.idProject}>
                        ID: {e.idProject}
                        Title: {e.title}
                    </label>
				))};
            </div>
            <div class="container-sm">
                <label class="fs-3 fw-semibold">Orders:</label>
                {orders.map(e => (
					<label key={e.idOrder} value={e.idOrder}>
                        Status: {e.orderStatus}
                        Start: {e.dateTimeStart}
                        End: {e.dateTimeEnd}
                    </label>
				))};
            </div>
        </div>
  );
    }

export default ProfilePage;