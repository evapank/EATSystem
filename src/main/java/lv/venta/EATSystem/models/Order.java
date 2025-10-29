package lv.venta.EATSystem.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.EATSystem.enums.OrderStatus;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "order_table")
@Entity
public class Order {
	
	@Id
	@Column(name = "IdOrder")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idOrder;
	
	@Column(name = "OrderNumber")
	@Min(value = 0)
	private int orderNumber;
	
	@ManyToOne
	@JoinColumn(name = "IdPorject")
	private Project project;
	
	@Column(name = "OrderDate")
	private LocalDate orderDate;
	
	@Column(name = "DateTimeStart")
	private LocalDateTime dateTimeStart;
	
	@Column(name = "DateTimeEnd")
	private LocalDateTime dateTimeEnd;
	
	@Column(name = "OrderStatus")
	private OrderStatus orderStatus;
	
	@ManyToOne
	@JoinColumn(name = "IdEmployeeOrderStatus")
	private EmployeeOrderStatus employeeOrderStatus;
	
	public Order (int orderNumber, Project project, LocalDate orderDate, LocalDateTime dateTimeStart,
			LocalDateTime dateTimeEnd, OrderStatus orderStatus, EmployeeOrderStatus employeeOrderStatus) {
		
		this.orderNumber = orderNumber;
		this.project = project;
		this.orderDate = orderDate;
		this.dateTimeStart = dateTimeStart;
		this.dateTimeEnd = dateTimeEnd;
		this.orderStatus = orderStatus;
		this.employeeOrderStatus = employeeOrderStatus;
	}

}
