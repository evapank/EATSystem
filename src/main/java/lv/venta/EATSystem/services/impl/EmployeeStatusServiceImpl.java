package lv.venta.EATSystem.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeStatus;
import lv.venta.EATSystem.repos.IEmployeeStatusRepo;
import lv.venta.EATSystem.services.IEmployeeStatusService;

@Service
public class EmployeeStatusServiceImpl implements IEmployeeStatusService{
	
	@Autowired
	private IEmployeeStatusRepo empStRepo;

	@Override
	public ArrayList<EmployeeStatus> selectAllEmployeeStatuses() {
		ArrayList<EmployeeStatus> result = (ArrayList<EmployeeStatus>) empStRepo.findAll();
		return result;
	}

	@Override
	public EmployeeStatus selectEmployeeStatusById(int id) throws Exception {
		EmployeeStatus result = empStRepo.findByIdEmployeeStatus(id);
		return result;
	}

	@Override
	public ArrayList<EmployeeStatus> deleteEmployeeStatusById(int id) {
		empStRepo.deleteByIdEmployeeStatus(id);
		ArrayList<EmployeeStatus> result = selectAllEmployeeStatuses();
		return result;
	}

	@Override
	public EmployeeStatus insertNewEmployeeStatus(Employee employee, GeneralStatus generalStatus,
			LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
		EmployeeStatus result = new EmployeeStatus(employee, generalStatus, dateTimeStart, dateTimeEnd);
		return result;
	}

	@Override
	public EmployeeStatus updateEmployeeStatusById(int id, Employee employee, GeneralStatus generalStatus,
			LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) throws Exception {
		EmployeeStatus result = empStRepo.findByIdEmployeeStatus(id);
		result = new EmployeeStatus(employee, generalStatus, dateTimeStart, dateTimeEnd);
		return result;
	}

}
