import { useEffect, useState } from "react";
import { UserService } from "../../static/api";

const ProfilePage = () => {
    const [user, setUser] = useState({
            username : '',
            employee : {}
        });
        const [loading, setLoading] = useState(true);
        const {id} = useParams();

        useEffect(() => {
            const fetchEmployee = async () => {
                try {
                    const response = await UserService.employeeView(id);
                    setUser(response.data);
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
        </div>
  );
    }

export default ProfilePage;