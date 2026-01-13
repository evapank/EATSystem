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
                </ul>
            </div>
                   
        </nav>
    );
}

export default NavigationBar;