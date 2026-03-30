package lv.venta.EATSystem.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.EmployeeStatus;
import lv.venta.EATSystem.repos.IEmployeeRepo;
import lv.venta.EATSystem.repos.IEmployeeStatusRepo;
import lv.venta.EATSystem.services.IMeetingService;

@Service
public class MeetingServiceImpl implements IMeetingService{

	@Autowired
	IEmployeeStatusRepo empStRepo;
	
	@Autowired
	IEmployeeRepo employeeRepo;
	
	@Override
	public ArrayList<EmployeeStatus> getAllEmployeeStatusByDateTime(LocalDateTime datetime) {
		ArrayList<EmployeeStatus> allStatuses = (ArrayList<EmployeeStatus>) empStRepo.findAll();
		ArrayList<EmployeeStatus> result = new ArrayList<>();
		
		for(EmployeeStatus status: allStatuses) {
			if(status.getGeneralStatus()!=GeneralStatus.DayOff) {
				if(status.getDateTimeStart().isBefore(datetime)&&status.getDateTimeEnd().isAfter(datetime)) {
					result.add(status);
				}
			}
		}
		return result;
	}

}
