import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
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

const App = () => {
  return (
    <Router>
        <NavigationBar />
        <Routes>
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
      </Routes>
    </Router>
  )
}

export default App;
