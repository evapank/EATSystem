package lv.venta.EATSystem.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeOrderStatus;
import lv.venta.EATSystem.repos.IEmployeeOrderStatusRepo;
import lv.venta.EATSystem.services.IEmployeeOrderStatusService;

@Service
public class EmployeeOrderStatusServiceImpl implements IEmployeeOrderStatusService{

	private IEmployeeOrderStatusRepo eosRepo;
	
	@Autowired
	public EmployeeOrderStatusServiceImpl (IEmployeeOrderStatusRepo eosRepo) {
		this.eosRepo = eosRepo;
	}
	
	@Override
	public ArrayList<EmployeeOrderStatus> selectAllEmployeeOrderStatuses() {
		ArrayList<EmployeeOrderStatus> result = (ArrayList<EmployeeOrderStatus>) eosRepo.findAll();
		return result;
	}

	@Override
	public EmployeeOrderStatus selectEmployeeOrderStatusById(int id) throws Exception {
		EmployeeOrderStatus result = eosRepo.findByIdEmployeeOrderStatus(id);
		return result;
	}

	@Override
	public ArrayList<EmployeeOrderStatus> deleteEmployeeOrderStatusById(int id) {
		if(eosRepo.existsById(id)) {
			eosRepo.deleteByIdEmployeeOrderStatus(id);
		}
		ArrayList<EmployeeOrderStatus> result = selectAllEmployeeOrderStatuses();
		return result;
	}

	@Override
	public EmployeeOrderStatus insertNewEmployeeOrderStatus(Employee employee, GeneralStatus generalStatus) {
		EmployeeOrderStatus result = new EmployeeOrderStatus(employee, generalStatus);
		eosRepo.save(result);
		return result;
	}

	@Override
	public EmployeeOrderStatus updateEmployeeOrderStatusById(int id, Employee employee, GeneralStatus generalStatus)
			throws Exception {
		EmployeeOrderStatus result = eosRepo.findByIdEmployeeOrderStatus(id);
		result.setEmployee(employee);
		result.setGeneralStatus(generalStatus);
		eosRepo.save(result);
		return result;
	}

}
