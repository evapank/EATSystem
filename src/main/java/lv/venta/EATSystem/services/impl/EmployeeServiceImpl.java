package lv.venta.EATSystem.services.impl;

import java.util.ArrayList;

import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.repos.IEmployeeRepo;
import lv.venta.EATSystem.services.IEmployeeService;

public class EmployeeServiceImpl implements IEmployeeService{

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
		employeeRepo.deleteByIdEmployee(id);
		ArrayList<Employee> result = selectAllEmployees();
		return result;
	}

	@Override
	public Employee insertNewEmployee(String name, String surname, String position, Department department,
			String email) {
		Employee result = new Employee(name, surname, position, department, email);
		return result;
	}

	@Override
	public Employee updateEmployeeById(int id, String name, String surname, String position, Department department,
			String email) throws Exception {
		Employee result = employeeRepo.findByIdEmployee(id);
		result = new Employee(name, surname, position, department, email);
		return result;
	}

}
