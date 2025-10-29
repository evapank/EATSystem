package lv.venta.EATSystem.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "day_table")
@Entity
public class Day {
	
	@Id
	@Column(name = "IdDay")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idDay;
	
	@OneToOne
	@JoinColumn(name = "IdEmployeeStatus")
	private EmployeeStatus employeeStatus;
	
	@Column(name = "Date")
	private LocalDate date;
	
	public Day (EmployeeStatus employeeStatus, LocalDate date) {
		
		this.employeeStatus = employeeStatus;
		this.date = date;
		
	}

}
