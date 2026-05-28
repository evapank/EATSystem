package lv.venta.EATSystem.models.dto;

import java.time.LocalDate;

import lombok.Data;
import lv.venta.EATSystem.models.Employee;

@Data
public class ProjectDTO {
	private int projectNumber;
	private String title;
	private LocalDate dateStart;
	private LocalDate dateEnd;
	private Employee projectManager;

}
