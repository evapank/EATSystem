package lv.venta.EATSystem.models;


import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.EATSystem.enums.GeneralStatus;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table
@Entity
public class EmployeeOrderStatus {
	
	@Id
	@Column(name = "IdEmployeeOrderStatus")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idEmployeeOrderStatus;
	
	@ManyToOne
	@JoinColumn(name = "IdEmployee")
	private Employee employee;
	
	@Column(name = "generalStatus")
	private GeneralStatus generalStatus;
	
	@OneToMany(mappedBy = "employeeOrderStatus")
	private Collection<Order> orders = new ArrayList<Order>();
	
	public EmployeeOrderStatus (Employee employee, GeneralStatus generalStatus) {
		
		this.employee = employee;
		this.generalStatus = generalStatus;
		
	}
	
	public void addOrder(Order order) {
		
		orders.add(order);
		
	}
	
	

}
