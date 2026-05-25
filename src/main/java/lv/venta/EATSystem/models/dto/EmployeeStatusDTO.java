package lv.venta.EATSystem.models.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lv.venta.EATSystem.enums.GeneralStatus;

@Data
public class EmployeeStatusDTO {
	private String name;
	private String surname;
	private LocalDateTime dateTimeStart;
	private LocalDateTime dateTimeEnd;
	private GeneralStatus generalStatus;

}
