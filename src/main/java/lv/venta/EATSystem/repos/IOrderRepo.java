package lv.venta.EATSystem.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.Order;

public interface IOrderRepo extends CrudRepository<Order, Integer>{

	Order findByIdOrder(int id);

	void deleteByIdOrder(int id);

	ArrayList<Order> findAllByEmployeeOrderStatusEmployee(Employee employee);

}
