package lv.venta.EATSystem.services;

import java.util.ArrayList;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeOrderStatus;

public interface IEmployeeOrderStatusService {
	
public abstract ArrayList<EmployeeOrderStatus> selectAllEmployeeOrderStatuses();
	
	public abstract EmployeeOrderStatus selectEmployeeOrderStatusById(int id) throws Exception;
	
	public abstract ArrayList<EmployeeOrderStatus> deleteEmployeeOrderStatusById(int id);
	
	public abstract EmployeeOrderStatus insertNewEmployeeOrderStatus(Employee employee, GeneralStatus generalStatus);
	
	public abstract EmployeeOrderStatus updateEmployeeOrderStatusById(int id, Employee employee, GeneralStatus generalStatus) throws Exception;



}
