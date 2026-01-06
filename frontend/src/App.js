import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import AllDepartments from './department/AllDepartments';
import OneDepartment from './department/OneDepartment';
import CreateDepartment from './department/CreateDepartment';
import UpdateDepartment from './department/UpdateDepartment';
import AllEmployees from './employee/AllEmployees';
import CreateEmployee from './employee/CreateEmployee';
import OneEmployee from './employee/OneEmployee';
import UpdateEmployee from './employee/UpdateEmployee';
import 'bootstrap/dist/css/bootstrap.min.css';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path={'/department/all'} exact={true} element={<AllDepartments/>}/>
        <Route path={`/department/all/:id`} exact={true} element={<OneDepartment/>}/>
        <Route path={`/department/getmanager/:id`} exact={true} element={<OneDepartment/>}/>
        <Route path={`/department/create`} exact={true} element={<CreateDepartment/>}/>
        <Route path={`/department/update/:id`} exact={true} element={<UpdateDepartment/>}/>
        <Route path={`/department/getmanager/:id`} exact={true} element={<UpdateDepartment/>}/>
        <Route path={`/department/getemployees`} exact={true} element={<UpdateDepartment/>}/>
        <Route path={`/department/remove/:id`} exact={true} element={<UpdateDepartment/>}/>
        <Route path={`/department/remove/:id`} exact={true} element={<AllDepartments/>}/>
        <Route path={`/department/remove/:id`} exact={true} element={<OneDepartment/>}/>

        <Route path={'/employee/all'} exact={true} element={<AllEmployees/>}/>
        <Route path={`/employee/all/:id`} exact={true} element={<OneEmployee/>}/>
        <Route path={`/employee/create`} exact={true} element={<CreateEmployee/>}/>
        <Route path={`/employee/update/:id`} exact={true} element={<UpdateEmployee/>}/>
      </Routes>
    </Router>
  )
}

export default App;
