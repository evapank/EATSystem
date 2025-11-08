package lv.venta.EATSystem.models;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.EATSystem.enums.GeneralStatus;

@Getter
@Setter
@NoArgsConstructor
@ToString
@MappedSuperclass
public abstract class Status {
	
	@ManyToOne
	@JoinColumn(name = "IdEmployee")
	protected Employee employee;
	
	@Column(name = "generalStatus")
	protected GeneralStatus generalStatus;
	
	public Status (Employee employee, GeneralStatus generalStatus) {
		
		this.employee = employee;
		this.generalStatus = generalStatus;
		
	}

}
