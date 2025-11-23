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

export const departmentService = {
	getAll: () => api.get('/department/all'),
	getById: (id) => api.get(`/department/all/${id}`),
	delete: (id) => api.delete(`/department/remove/${id}`),
	create: (department) => api.post('/department/create', department),
	update: (id, department) => api.put(`/department/update/${id}`, department)
	
};

export default api;