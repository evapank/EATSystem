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
	create: (department, managerId) => api.post('/department/create', {...department, managerId:managerId}),
  getEmployees: () => api.get('/department/getemployees'),
  getManager: (departmentId) => api.get(`/department/getmanager/${departmentId}`),
  update: (id, department) => api.put(`/department/update/${id}`, department),
  getDepartmentEmployees: (id) => api.get(`/department/update/${id}`)
};

export const EmployeeService = {
	getAll: () => api.get('/employee/all'),
	getById: (id) => api.get(`/employee/all/${id}`),
	delete: (id) => api.delete(`/employee/remove/${id}`),
	create: (employee) => api.post('/employee/create', employee),
	update: (id, employee) => api.put(`/employee/update/${id}`, employee)
	
};

export const ProjectService = {
	getAll: () => api.get('/project/all'),
	getById: (id) => api.get(`/project/all/${id}`),
	delete: (id) => api.delete(`/project/remove/${id}`),
	create: (project) => api.post('/project/create', project),
	update: (id, project) => api.put(`/project/update/${id}`, project)
	
};

export const OrderService = {
	getAll: () => api.get('/order/all'),
	getById: (id) => api.get(`/order/all/${id}`),
	delete: (id) => api.delete(`/order/remove/${id}`),
	create: (order) => api.post('/order/create', order),
	update: (id, order) => api.put(`/order/update/${id}`, order)
	
};

export const EosService = {
	getAll: () => api.get('/eos/all'),
	getById: (id) => api.get(`/eos/all/${id}`),
	delete: (id) => api.delete(`/eos/remove/${id}`),
	create: (eos) => api.post('/eos/create', eos),
	update: (id, eos) => api.put(`/eos/update/${id}`, eos)
	
};

export default api;