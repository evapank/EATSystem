import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import "react-datepicker/dist/react-datepicker.css";
import "react-big-calendar/lib/css/react-big-calendar.css"
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import AllDepartments from './department/AllDepartments';
import OneDepartment from './department/OneDepartment';
import CreateDepartment from './department/CreateDepartment';
import UpdateDepartment from './department/UpdateDepartment';
import AllEmployees from './employee/AllEmployees';
import CreateEmployee from './employee/CreateEmployee';
import OneEmployee from './employee/OneEmployee';
import UpdateEmployee from './employee/UpdateEmployee';
import NavigationBar from './static/NavigationBar';
import AllProjects from './project/AllProjects';
import OneProject from './project/OneProject';
import CreateProject from './project/CreateProject';
import UpdateProject from './project/UpdateProject';
import AllOrders from './order/AllOrders';
import OneOrder from './order/OneOrder';
import CreateOrder from './order/CreateOrder';
import AllEmployeeOrderStatuses from './employeeOrderStatus/AllEmployeeOrderStatuses';
import UpdateOrder from './order/UpdateOrder';
import OneEmployeeOrderStatus from './employeeOrderStatus/OneEmployeeOrderStatus';
import UpdateEmployeeOrderStatus from './employeeOrderStatus/UpdateEmployeeOrderStatus';
import CreateEmployeeOrderStatus from './employeeOrderStatus/CreateEmployeeOrderStatus';
import AllEmployeeStatuses from './employeeStatus/AllEmployeeStatuses';
import OneEmployeeStatus from './employeeStatus/OneEmployeeStatus';
import CreateEmployeeStatus from './employeeStatus/CreateEmployeeStatus';
import UpdateEmployeeStatus from './employeeStatus/UpdateEmployeeStatus';
import LoginPage from './static/LoginPage';
import SignupPage from './static/SignupPage';
import WelcomeDashboard from './static/WelcomeDashboard';
import ProfilePage from './user/employee/ProfilePage';
import AddStatusPage from './user/employee/AddStatusPage';
import NewMeetingAdd from './meeting/NewMeetingAdd';
import NewMeetingTimeSelect from './meeting/NewMeetingTimeSelect';
import AllMeetingsCalendar from './meeting/AllMeetingsCalendar';

const App = () => {
  return (
    <Router>
        <NavigationBar />
        <Routes>
        <Route path={"/"} element={<LoginPage/>} />
        <Route path={"/auth/signup"} element={ <SignupPage/>} />
        <Route path={"/dashboard"} element={<WelcomeDashboard/>}/>
        <Route path={`/auth/user/employee/:id`} element={<ProfilePage/>}/>
        <Route path={`/auth/user/employee/newStatus/:id`} element={<AddStatusPage/>}/>

        <Route path={"/meeting/all"} element={<AllMeetingsCalendar/>} />
        <Route path={"/meeting/employeestatuses"} element={<NewMeetingTimeSelect/>} />
        <Route path={"/meeting/create"} element={<NewMeetingAdd/>} />

        <Route path={'/department/all'} exact={true} element={<AllDepartments/>}/>
        <Route path={`/department/all/:id`} exact={true} element={<OneDepartment/>}/>
        <Route path={`/department/create`} exact={true} element={<CreateDepartment/>}/>
        <Route path={`/department/update/:id`} exact={true} element={<UpdateDepartment/>}/>  
      
        <Route path={'/employee/all'} exact={true} element={<AllEmployees/>}/>
        <Route path={`/employee/all/:id`} exact={true} element={<OneEmployee/>}/>
        <Route path={`/employee/create`} exact={true} element={<CreateEmployee/>}/>
        <Route path={`/employee/update/:id`} exact={true} element={<UpdateEmployee/>}/>

        <Route path={'/project/all'} exact={true} element={<AllProjects/>}/>
        <Route path={`/project/all/:id`} exact={true} element={<OneProject/>}/>
        <Route path={`/project/create`} exact={true} element={<CreateProject/>}/>
        <Route path={`/project/update/:id`} exact={true} element={<UpdateProject/>}/>

        <Route path={'/order/all'} exact={true} element={<AllOrders/>}/>
        <Route path={`/order/all/:id`} exact={true} element={<OneOrder/>}/>
        <Route path={`/order/create`} exact={true} element={<CreateOrder/>}/>
        <Route path={`/order/update/:id`} exact={true} element={<UpdateOrder/>}/>

        <Route path={'/employeeorderstatus/all'} exact={true} element={<AllEmployeeOrderStatuses/>}/>
        <Route path={`/employeeorderstatus/all/:id`} exact={true} element={<OneEmployeeOrderStatus/>}/>
        <Route path={`/employeeorderstatus/create`} exact={true} element={<CreateEmployeeOrderStatus/>}/>
        <Route path={`/employeeorderstatus/update/:id`} exact={true} element={<UpdateEmployeeOrderStatus/>}/>

        <Route path={'/employeestatus/all'} exact={true} element={<AllEmployeeStatuses/>}/>
        <Route path={`/employeestatus/all/:id`} exact={true} element={<OneEmployeeStatus/>}/>
        <Route path={`/employeestatus/create`} exact={true} element={<CreateEmployeeStatus/>}/>
        <Route path={`/employeestatus/update/:id`} exact={true} element={<UpdateEmployeeStatus/>}/>
      </Routes>
    </Router>
  )
}

export default App;
