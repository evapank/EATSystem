package lv.venta.EATSystem.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.repos.IDepartmentRepo;
import lv.venta.EATSystem.services.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService{
	
	@Autowired
	private IDepartmentRepo departmentRepo;

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
		departmentRepo.deleteByIdDepartment(id);
		ArrayList<Department> result = selectAllDepartments();
		return result;
	}

	@Override
	public Department insertNewDepartment(String title, Employee manager) {
		Department result = new Department(title, manager);
		return result;
	}

	@Override
	public Department updateDepartmentById(int id, String title, Employee manager) throws Exception {
		Department result = departmentRepo.findByIdDepartment(id);
		result = new Department (title, manager);
		return result;
	}

}
