import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import AllDepartments from './department/AllDepartments';
import OneDepartment from './department/OneDepartment';
import CreateDepartment from './department/CreateDepartment';
import UpdateDepartment from './department/UpdateDepartment';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path='/department/all' exact={true} element={<AllDepartments/>}/>
        <Route path={`/department/all/${id}`} exact={true} element={<OneDepartment/>}/>
        <Route path={`/department/create`} exact={true} element={<CreateDepartment/>}/>
        <Route path={`/department/update/${id}`} exact={true} element={<UpdateDepartment/>}/>
      </Routes>
    </Router>
  )
}

export default App;
