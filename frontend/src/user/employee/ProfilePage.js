import { useEffect, useState } from "react";
import { UserService } from "../../static/api";

const ProfilePage = () => {
    const [employee, setEmployee] = useState({
            name : '',
            surname : '',
            position : '',
            department : {},
            isManager : false
        });
        const [loading, setLoading] = useState(true);
        const {id} = useParams();

        useEffect(() => {
            const fetchEmployee = async () => {
                try {
                    const responseEmployee = await UserService.employeeView(id);
                    setLoading(false);
                } catch (error){
                    setError('cannot find employee');
				    setLoading(false);
				    console.log(error);
                }
            };
              fetchEmployee();
        }, []);
    }

export default ProfilePage;