import React, { useState, useEffect } from 'react';
import { departmentService } from '../static/api';
import { Link } from 'react-router-dom';

const DepartmentList = () => {
	const [department, setDepartments] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(null);
	
	useEffect(() => {
		const fetchDepartments = async () => {
			try {
				const response = await departmentService.getAll();
				setDepartments(response.data);
				setLoading(false);
			} catch (error){
				setError('cannot find departments');
				setLoading(false);
				console.log(error);
			}
		};
		fetchDepartments();
	}, []);
}