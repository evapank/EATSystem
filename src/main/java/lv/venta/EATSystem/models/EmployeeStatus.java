package lv.venta.EATSystem.models;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity
@Table
public class EmployeeStatus extends Status{
	
	@Id
	@Column(name = "IdEmployeeStatus")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	protected int idEmployeeStatus;
	
	@Column(name = "DateTimeStart")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dateTimeStart;
	
	@Column(name = "DateTimeEnd")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dateTimeEnd;
	
	public EmployeeStatus (Employee employee, GeneralStatus generalStatus,
			LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
		
		setEmployee(employee);
		setGeneralStatus(generalStatus);
		this.dateTimeStart = dateTimeStart;
		this.dateTimeEnd = dateTimeEnd;
		
	}
	
	
	

}
