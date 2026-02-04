package lv.venta.EATSystem.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeOrderStatus;
import lv.venta.EATSystem.models.EmployeeStatus;
import lv.venta.EATSystem.models.Project;
import lv.venta.EATSystem.repos.IDepartmentRepo;
import lv.venta.EATSystem.repos.IEmployeeOrderStatusRepo;
import lv.venta.EATSystem.repos.IEmployeeRepo;
import lv.venta.EATSystem.repos.IEmployeeStatusRepo;
import lv.venta.EATSystem.repos.IProjectRepo;
import lv.venta.EATSystem.services.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

	@Autowired
	private IEmployeeRepo employeeRepo;
	
	@Autowired
	private IEmployeeOrderStatusRepo eosRepo;
	
	@Autowired
	private IEmployeeStatusRepo empStRepo;
	
	@Autowired
	private IDepartmentRepo departmentRepo;
	
	@Autowired
	private IProjectRepo projectRepo;
	
	@Override
	public ArrayList<Employee> selectAllEmployees() {
		ArrayList<Employee> result = (ArrayList<Employee>) employeeRepo.findAll();
		return result;
	}

	@Override
	public Employee selectEmployeeById(int id) throws Exception {
		Employee result = employeeRepo.findByIdEmployee(id);
		return result;
	}

	@Override
	public ArrayList<Employee> deleteEmployeeById(int id) {
		if(employeeRepo.existsById(id)) {
			ArrayList<EmployeeOrderStatus> eos = eosRepo.findByEmployeeIdEmployee(id);
			for (EmployeeOrderStatus e : eos) {
				e.setEmployee(null);
				eosRepo.save(e);
			}
			ArrayList<EmployeeStatus> empSt = empStRepo.findByEmployeeIdEmployee(id);
			for (EmployeeStatus e : empSt) {
				e.setEmployee(null);
				empStRepo.save(e);
			}
			Employee temp = employeeRepo.findByIdEmployee(id);
			ArrayList<Project> projects = projectRepo.findByEmployeesIdEmployee(id);
			for (Project p : projects) {
				temp.removeProject(p);
				employeeRepo.save(temp);
				p.removeEmployee(temp);
				projectRepo.save(p);
		
			}
			temp.setDepartment(null);
			employeeRepo.save(temp);
			employeeRepo.deleteById(id);
		}
		ArrayList<Employee> result = selectAllEmployees();
		return result;
	}

	@Override
	public Employee insertNewEmployee(String name, String surname, String position, Department department,
			String email, boolean isManager) {
		Employee result = new Employee(name, surname, position, department, email, isManager);
		employeeRepo.save(result);
		return result;
	}

	@Override
	public Employee updateEmployeeById(int id, String name, String surname, String position, Department department,
			String email, boolean isManager) throws Exception {
		Employee result = employeeRepo.findByIdEmployee(id);
		result.setName(name);
		result.setSurname(surname);
		result.setPosition(position);
		Department depart = departmentRepo.findByIdDepartment(Integer.parseInt(department.getTitle()));
		System.out.println(depart);
		result.setDepartment(depart);
		result.setEmail(email);
		result.setManager(isManager);
		employeeRepo.save(result);
		return result;
	}

}
