package lv.venta.EATSystem.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.EATSystem.models.Employee;

public interface IEmployeeRepo extends CrudRepository<Employee, Integer>{

	Employee findByIdEmployee(int id);

	void deleteByIdEmployee(int id);

	ArrayList<Employee> findByDepartmentIdDepartment(int id);

}
