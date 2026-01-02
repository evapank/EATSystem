import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';
const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json'
    }
   });
   
   api.interceptors.request.use(
  config => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  error => Promise.reject(error)
);

export const DepartmentService = {
	getAll: () => api.get('/department/all'),
	getById: (id) => api.get(`/department/all/${id}`),
	delete: (id) => api.delete(`/department/remove/${id}`),
	create: (department) => api.post('/department/create', department),
  getEmployees: (employees) => api.get('/department/getemployees', employees),
  getManager: (departmentId) => api.get(`/department/getmanager/${departmentId}`, departmentId),
	update: (id, department) => api.put(`/department/update/${id}`, department),
  getDepartmentEmployees: (id, employees) => api.get(`/department/update/${id}`, employees)
};

export const EmployeeService = {
	getAll: () => api.get('/employee/all'),
	getById: (id) => api.get(`/employee/all/${id}`),
	delete: (id) => api.delete(`/employee/remove/${id}`),
	create: (employee) => api.post('/employee/create', employee),
	update: (id, employee) => api.put(`/employee/update/${id}`, employee)
	
};

export default api;