package lv.venta.EATSystem.models.dto;

import lombok.Data;
import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Employee;

@Data
public class EmployeeStatusDTO {
	private Employee employee;
	private GeneralStatus generalStatus;

}
