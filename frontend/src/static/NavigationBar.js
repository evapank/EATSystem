import { Link, NavLink} from "react-router-dom";

const NavigationBar = () => {
    return(
         <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <div className="container-fluid">
                <ul className="nav navbar-nav">
                    <li className="nav-item"><NavLink className="nav-link" eventKey ="1" as={Link} to="/">Home</NavLink></li>
                    <li className="nav-item"><NavLink className="nav-link" eventKey ="2" as={Link} to="/department/all">Departments</NavLink></li>
                    <li className="nav-item"><NavLink className="nav-link" eventKey ="3" as={Link} to="/employee/all">Employees</NavLink></li>
                    <li className="nav-item"><NavLink className="nav-link" eventKey ="4" as={Link} to="/project/all">Projects</NavLink></li>
                    <li className="nav-item"><NavLink className="nav-link" eventKey ="5" as={Link} to="/order/all">Orders</NavLink></li>
                    <li className="nav-item"><NavLink className="nav-link" eventKey ="6" as={Link} to="/employeeorderstatus/all">Employee order statuses</NavLink></li>
                    <li className="nav-item"><NavLink className="nav-link" eventKey ="7" as={Link} to="/employeestatus/all">Employee statuses</NavLink></li>
                </ul>
            </div>
                   
        </nav>
    );
}

export default NavigationBar;