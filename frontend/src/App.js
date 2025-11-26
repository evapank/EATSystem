import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import AllDepartments from './department/AllDepartments';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path='/department/all' exact={true} element={<AllDepartments/>}/>
      </Routes>
    </Router>
  )
}

export default App;
