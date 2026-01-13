package lv.venta.EATSystem.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.repos.IEmployeeRepo;
import lv.venta.EATSystem.services.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

	@Autowired
	private IEmployeeRepo employeeRepo;
	
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
		employeeRepo.deleteByIdEmployee(id);
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
		result.setPostion(position);
		result.setDepartment(department);
		result.setEmail(email);
		result.setManager(isManager);
		employeeRepo.save(result);
		return result;
	}

}
