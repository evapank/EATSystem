import { useEffect, useState } from "react";
import { UserService } from "../../static/api";
import { useParams } from "react-router-dom";
import { Link } from "react-router-dom";
import { localDateArrayToDate } from "../../static/DateConverters";
import { localDateTimeArrayToDate } from "../../static/DateConverters";

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
                    console.log("user: ",responseUser.data);
                    console.log("project: ",responseProject.data);
                    console.log("order: ",responseOrder.data);
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
        }, [id]);

        if (loading) return <div>Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;
  return (
     <><div className="container-fluid p-2 bg-info w-100">
          <h4>User: {user.username}</h4>
          <p>Department: {user.employee?.department?.title}</p>
      </div>
      <div className="container mt-4">
        <Link to={`/auth/user/employee/newStatus/${id}`} className="btn btn-dark float-right">
                  Add new status
              </Link>
              <div className="container-fluid">
                  <div className="row">
                      <div className="col-lg-6 p-3">
                          <h4><u>Projects:</u></h4>
                          <table className="table-primary table-hover">
                              {projects.map(e => (
                                  <tr key={e.idProject} value={e.idProject}>
                                      <td className="p-1">{e.title}: </td>
                                      <td className="p-1">{localDateArrayToDate(e.dateStart).toDateString()} - </td>
                                      <td className="p-1">{localDateArrayToDate(e.dateEnd).toDateString()}</td>
                                  </tr>
                              ))}
                          </table>
                      </div>
                      <div className="col-lg-6 p-3">
                          <h4><u>Orders:</u></h4>
                          <table className="table-primary table-hover">
                              {orders.map(e => (
                                  <tr key={e.idOrder} value={e.idOrder}>
                                      <td className="p-1">{e.orderStatus}: </td>
                                      <td className="p-1">{localDateTimeArrayToDate(e.dateTimeStart).toDateString()} -</td>
                                      <td className="p-1">{localDateTimeArrayToDate(e.dateTimeEnd).toDateString()}</td>
                                  </tr>
                              ))}
                          </table>
                      </div>
                  </div>
                  </div>
          </div>
              </>
  );
    }

export default ProfilePage;