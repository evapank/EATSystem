package lv.venta.EATSystem.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.EATSystem.models.EmployeeOrderStatus;

public interface IEmployeeOrderStatusRepo extends CrudRepository<EmployeeOrderStatus, Integer>{

	EmployeeOrderStatus findByIdEmployeeOrderStatus(int id);

	void deleteByIdEmployeeOrderStatus(int id);

	ArrayList<EmployeeOrderStatus> findByEmployeeIdEmployee(int id);

}
