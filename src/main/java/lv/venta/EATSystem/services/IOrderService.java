package lv.venta.EATSystem.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import lv.venta.EATSystem.enums.OrderStatus;
import lv.venta.EATSystem.models.EmployeeOrderStatus;
import lv.venta.EATSystem.models.Order;
import lv.venta.EATSystem.models.Project;

public interface IOrderService {
	
	public abstract ArrayList<Order> selectAllOrders();
	
	public abstract Order selectOrderById(int id) throws Exception;
	
	public abstract ArrayList<Order> deleteOrderById(int id);
	
	public abstract Order insertNewOrder(int orderNumber, Project project, LocalDate orderDate, LocalDateTime dateTimeStart,
			LocalDateTime dateTimeEnd, OrderStatus orderStatus, EmployeeOrderStatus employeeOrderStatus);
	
	public abstract Order updateOrderById(int id, int orderNumber, Project project, LocalDate orderDate, LocalDateTime dateTimeStart,
			LocalDateTime dateTimeEnd, OrderStatus orderStatus, EmployeeOrderStatus employeeOrderStatus) throws Exception;


}
