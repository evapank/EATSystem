package lv.venta.EATSystem.models.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
import lv.venta.EATSystem.enums.OrderStatus;
import lv.venta.EATSystem.models.EmployeeOrderStatus;
import lv.venta.EATSystem.models.Project;

@Data
public class OrderDTO {
	
	private int orderNumber;
	private Project project;
	private LocalDate orderDate;
	private LocalDateTime dateTimeStart;
	private LocalDateTime dateTimeEnd;
	private OrderStatus orderStatus;
	private EmployeeOrderStatus employeeOrderStatus;

}
