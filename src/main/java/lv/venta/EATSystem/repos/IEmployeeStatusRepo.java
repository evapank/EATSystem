package lv.venta.EATSystem.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.EATSystem.models.EmployeeStatus;

public interface IEmployeeStatusRepo extends CrudRepository<EmployeeStatus, Integer>{

	EmployeeStatus findByIdEmployeeStatus(int id);

	void deleteByIdEmployeeStatus(int id);

	ArrayList<EmployeeStatus> findByEmployeeIdEmployee(int id);

}
