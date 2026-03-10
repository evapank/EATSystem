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
            <div>
                <label>Department: {user.employee?.department?.title}</label>
            </div>
            <div>
                <label>Projects:</label>
                {projects.map(e => (
					<label key={e.idProject} value={e.idProject}>{e.title}</label>
				))};
            </div>
        </div>
  );
    }

export default ProfilePage;