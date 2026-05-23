package lv.venta.EATSystem.repos;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeStatus;

public interface IEmployeeStatusRepo extends CrudRepository<EmployeeStatus, Integer>{

	EmployeeStatus findByIdEmployeeStatus(int id);

	void deleteByIdEmployeeStatus(int id);

	ArrayList<EmployeeStatus> findByEmployeeIdEmployee(int id);

	EmployeeStatus findByEmployeeAndDateTimeStart(Employee employee, LocalDateTime datetime);

	boolean existsByEmployeeAndDateTimeStart(Employee employee, LocalDateTime dateTimeStart);

}
