package lv.venta.EATSystem.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class EmployeeStatus {
	
	@Id
	@Column(name = "IdEmployeeStatus")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idEmployeeStatus;
	
	@ManyToOne
	@JoinColumn(name = "IdEmployee")
	private Employee employee;
	
	@Column(name = "generalStatus")
	private GeneralStatus generalStatus;
	
	@Column(name = "DateTimeStart")
	private LocalDateTime dateTimeStart;
	
	@Column(name = "DateTimeEnd")
	private LocalDateTime dateTimeEnd;
	
	public EmployeeStatus (Employee employee, GeneralStatus generalStatus,
			LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
		
		this.employee = employee;
		this.generalStatus = generalStatus;
		this.dateTimeStart = dateTimeStart;
		this.dateTimeEnd = dateTimeEnd;
		
	}
	
	
	

}
