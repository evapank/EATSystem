package lv.venta.EATSystem.services;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lv.venta.EATSystem.models.EmployeeStatus;

public interface IMeetingService {

	public abstract ArrayList<EmployeeStatus> getAllEmployeeStatusByDateTime(LocalDateTime datetime);
}
