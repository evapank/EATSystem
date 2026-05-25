package lv.venta.EATSystem.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.EATSystem.models.Department;

public interface IDepartmentRepo extends CrudRepository<Department, Integer>{

	Department findByIdDepartment(int id);

	void deleteByIdDepartment(int id);

	Department findByEmployeesIdEmployee(int id);

	Department findByTitle(String string);

}
