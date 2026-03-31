package lv.venta.EATSystem.models;

import java.time.LocalDateTime;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class Meeting {
	
	@Id
	@Column(name = "IdMeeting")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idMeeting;
	
	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "IdMeeting"),inverseJoinColumns = @JoinColumn(name = "IdEmployee"),
	name = "employees_meetings")
	@ToString.Exclude
	@JsonIgnore
	private Collection<Employee> employees;
	
	@Column(name = "DateTimeStart")
	private LocalDateTime dateTimeStart;
	
	@Column(name = "DateTimeEnd")
	private LocalDateTime dateTimeEnd;
	
	@Column(name = "generalStatus")
	private GeneralStatus generalStatus;
	
	public Meeting (LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, GeneralStatus generalStatus) {
		this.dateTimeStart = dateTimeStart;
		this.dateTimeEnd = dateTimeEnd;
		this.generalStatus = generalStatus;
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}

}
