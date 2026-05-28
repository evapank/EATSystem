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
	
	private IDepartmentRepo departmentRepo;
	
	private IEmployeeRepo employeeRepo;

	@Autowired
	public DepartmentServiceImpl(IDepartmentRepo departmentRepo, IEmployeeRepo employeeRepo) {
		this.departmentRepo = departmentRepo;
		this.employeeRepo = employeeRepo;
	}
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
		Employee manager = employeeRepo.findByDepartmentIdDepartmentAndIsManager(departmentId, true);
		manager.setManager(false);
		employeeRepo.save(manager);
		System.out.println("manager id: " + managerId);
		System.out.println(departmentRepo.findByIdDepartment(departmentId));
		System.out.println(employeeRepo.findByIdEmployee(managerId));
		if(employeeRepo.existsById(managerId)) {
			manager = employeeRepo.findByIdEmployee(managerId);
		}
		manager.setDepartment(result);
		manager.setManager(true);
		result.setTitle(title);
		result.addEmployee(manager);
		departmentRepo.save(result);
		employeeRepo.save(manager);
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
