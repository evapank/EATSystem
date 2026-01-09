package lv.venta.EATSystem.services;

import java.util.ArrayList;

import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.Employee;

public interface IDepartmentService {
	
	public abstract ArrayList<Department> selectAllDepartments();
	
	public abstract Department selectDepartmentById(int id) throws Exception;
	
	public abstract ArrayList<Department> deleteDepartmentById(int id);
	
	public abstract Department insertNewDepartment(String title, int managerId);
	
	public abstract Department updateDepartmentById(int id, String title) throws Exception;

	public abstract ArrayList<Employee> selectAllEmployeesInDepartment(int id);
	
	public abstract Employee getDepartmentManagerByDepartmentId(int id);
}
