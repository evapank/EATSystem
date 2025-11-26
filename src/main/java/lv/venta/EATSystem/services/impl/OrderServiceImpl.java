package lv.venta.EATSystem.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.EATSystem.enums.OrderStatus;
import lv.venta.EATSystem.models.EmployeeOrderStatus;
import lv.venta.EATSystem.models.Order;
import lv.venta.EATSystem.models.Project;
import lv.venta.EATSystem.repos.IOrderRepo;
import lv.venta.EATSystem.services.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	private IOrderRepo orderRepo;

	@Override
	public ArrayList<Order> selectAllOrders() {
		ArrayList<Order> result = (ArrayList<Order>) orderRepo.findAll();
		return result;
	}

	@Override
	public Order selectOrderById(int id) throws Exception {
		Order result = orderRepo.findByIdOrder(id);
		return result;
	}

	@Override
	public ArrayList<Order> deleteOrderById(int id) {
		orderRepo.deleteByIdOrder(id);
		ArrayList<Order> result = selectAllOrders();
		return result;
	}

	@Override
	public Order insertNewOrder(int orderNumber, Project project, LocalDate orderDate, LocalDateTime dateTimeStart,
			LocalDateTime dateTimeEnd, OrderStatus orderStatus, EmployeeOrderStatus employeeOrderStatus) {
		Order result = new Order(orderNumber, project, orderDate, dateTimeStart, dateTimeEnd, orderStatus, employeeOrderStatus);
		return result;
	}

	@Override
	public Order updateOrderById(int id, int orderNumber, Project project, LocalDate orderDate,
			LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, OrderStatus orderStatus,
			EmployeeOrderStatus employeeOrderStatus) throws Exception {
		Order result = orderRepo.findByIdOrder(id);
		result = new Order(orderNumber, project, orderDate, dateTimeStart, dateTimeEnd, orderStatus, employeeOrderStatus);
		return result;
	}

}
