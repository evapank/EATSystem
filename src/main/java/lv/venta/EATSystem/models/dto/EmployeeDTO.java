package lv.venta.EATSystem.models.dto;

import lombok.Data;
import lv.venta.EATSystem.models.Department;

@Data
public class EmployeeDTO {
	private String name;
	private String surname;
	private String position;
	private Department department;
	private String email;
	private boolean isManager;
}
