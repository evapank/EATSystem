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

export const UserService = {
	logIn: (username, password) => api.post('/auth/login', {username, password}),
	signUp: (username, email, password, role) => api.post('/auth/signup', {username, email, password, role}),
	employeeView: (id) => api.get(`/auth/user/employee/${id}`),
	employeeProjects: (id) => api.get(`/auth/user/employee/projects/${id}`),
	employeeOrders: (id) => api.get(`/auth/user/employee/orders/${id}`),
	insertEmployeeStatus: (id, employeeStatus) => api.post(`/auth/user/employee/newStatus/${id}`, employeeStatus)
};

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
	getAll: () => api.get('/employeeorderstatus/all'),
	getById: (id) => api.get(`/employeeorderstatus/all/${id}`),
	delete: (id) => api.delete(`/employeeorderstatus/remove/${id}`),
	create: (eos) => api.post('/employeeorderstatus/create', eos),
	update: (id, eos) => api.put(`/employeeorderstatus/update/${id}`, eos)
	
};

export const EmployeeStatusService = {
	getAll: () => api.get('/employeestatus/all'),
	getById: (id) => api.get(`/employeestatus/all/${id}`),
	delete: (id) => api.delete(`/employeestatus/remove/${id}`),
	create: (empst) => api.post('/employeestatus/create', empst),
	update: (id, empst) => api.put(`/employeestatus/update/${id}`, empst)
	
};

export const OtherService = {
	getRoles: () => api.get('/other/roles'),
	getOrderStatuses: () => api.get('/other/orderstatus'),
	getGeneralStatus: () => api.get('/other/generalstatus'),
};

export const MeetingService = {
	getEmployeeStatuses: (dateTime) => api.get('meeting/employeestatuses', dateTime),
	create: (meeting) => api.post('meeting/create', meeting),
	addEmployeeToMeeting: (meetingId, employee) => api.post('meeting/addEmployee', meetingId, employee)
};

export default api;