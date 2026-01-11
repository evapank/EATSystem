package lv.venta.EATSystem.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.repos.IDepartmentRepo;
import lv.venta.EATSystem.repos.IEmployeeRepo;
import lv.venta.EATSystem.services.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService{
	
	@Autowired
	private IDepartmentRepo departmentRepo;
	
	@Autowired
	private IEmployeeRepo employeeRepo;

	@Override
	public ArrayList<Department> selectAllDepartments() {
		ArrayList<Department> result = (ArrayList<Department>) departmentRepo.findAll();
		return result;
	}

	@Override
	public Department selectDepartmentById(int id) throws Exception {
		Department result = departmentRepo.findByIdDepartment(id);
		return result;
	}

	@Override
	public ArrayList<Department> deleteDepartmentById(int id) {
		if(departmentRepo.existsById(id)) {
			Employee manager = getDepartmentManagerByDepartmentId(id);
			manager.setManager(false);
			ArrayList<Employee> employees = employeeRepo.findByDepartmentIdDepartment(id);
			for(Employee e: employees) {
				e.setDepartment(null);
				employeeRepo.save(e);
			}
			departmentRepo.deleteByIdDepartment(id);
		}
		ArrayList<Department> result = selectAllDepartments();
		return result;
	}

	@Override
	public Department insertNewDepartment(String title, int managerId) {
		Department result = departmentRepo.save(new Department(title));
		Employee manager = employeeRepo.findByIdEmployee(managerId);
		manager.setManager(true);
		employeeRepo.save(manager);
		return result;
	}

	@Override
	public Department updateDepartmentById(int departmentId, String title, int managerId) throws Exception {
		Department result = departmentRepo.findByIdDepartment(departmentId);
		Employee manager = employeeRepo.findByIdEmployee(managerId);
		result = new Department (title);
		manager.setManager(true);
		employeeRepo.save(manager);
		departmentRepo.save(result);
		return result;
	}

	@Override
	public ArrayList<Employee> selectAllEmployeesInDepartment(int id) {
		ArrayList<Employee> result = new ArrayList<Employee>();
		
		if(departmentRepo.existsById(id)) {
			result = employeeRepo.findByDepartmentIdDepartment(id);
		}
		return result;
	}
	

	@Override
	public Employee getDepartmentManagerByDepartmentId(int id) {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		if(departmentRepo.existsById(id)) {
			employees = employeeRepo.findByDepartmentIdDepartment(id);
		}
		
		Employee result = new Employee();
		
		for (Employee e: employees) {
			if (e.isManager()) {
				result = e;
				return result;
			}
		}
		return result;
	}


}
