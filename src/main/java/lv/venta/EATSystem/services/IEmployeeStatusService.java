package lv.venta.EATSystem.services;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeStatus;

public interface IEmployeeStatusService {
	
	public abstract ArrayList<EmployeeStatus> selectAllEmployeeStatuses();
	
	public abstract EmployeeStatus selectEmployeeStatusById(int id) throws Exception;
	
	public abstract ArrayList<EmployeeStatus> deleteEmployeeStatusById(int id);
	
	public abstract EmployeeStatus insertNewEmployeeStatus(Employee employee, GeneralStatus generalStatus, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);
	
	public abstract EmployeeStatus updateEmployeeStatusById(int id, Employee employee, GeneralStatus generalStatus, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) throws Exception;


}
