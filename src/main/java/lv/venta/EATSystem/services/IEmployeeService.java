package lv.venta.EATSystem.services;

import java.util.ArrayList;

import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.Employee;

public interface IEmployeeService {
	
	public abstract ArrayList<Employee> selectAllEmployees();
	
	public abstract Employee selectEmployeeById(int id) throws Exception;
	
	public abstract ArrayList<Employee> deleteEmployeeById(int id);
	
	public abstract Employee insertNewEmployee(String name, String surname, String position, Department department, String email);
	
	public abstract Employee updateEmployeeById(int id, String name, String surname, String position, Department department, String email) throws Exception;

}
