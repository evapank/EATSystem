package lv.venta.EATSystem.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeStatus;
import lv.venta.EATSystem.models.Meeting;
import lv.venta.EATSystem.repos.IEmployeeRepo;
import lv.venta.EATSystem.repos.IEmployeeStatusRepo;
import lv.venta.EATSystem.repos.IMeetingRepo;
import lv.venta.EATSystem.services.IMeetingService;

@Service
public class MeetingServiceImpl implements IMeetingService{

	@Autowired
	IEmployeeStatusRepo empStRepo;
	
	@Autowired
	IEmployeeRepo employeeRepo;
	
	@Autowired
	IMeetingRepo meetingRepo;
	
	@Override
	public ArrayList<EmployeeStatus> getAllEmployeeStatusByDateTime(LocalDateTime datetime) {
		ArrayList<EmployeeStatus> allStatuses = (ArrayList<EmployeeStatus>) empStRepo.findAll();
		ArrayList<Employee> allEmployees = (ArrayList<Employee>) employeeRepo.findAll();
		ArrayList<EmployeeStatus> result = new ArrayList<>();
		for (Employee employee: allEmployees) {
			if (!empStRepo.findByEmployeeAndDateTimeStart(employee, datetime)) {
				EmployeeStatus empSt = new EmployeeStatus(employee, GeneralStatus.InPerson, datetime, datetime.plusDays(1));
				empStRepo.save(empSt);
			}
		}
		for(EmployeeStatus status: allStatuses) {
			if(status.getGeneralStatus()!=GeneralStatus.DayOff) {
				if(status.getDateTimeStart().isBefore(datetime)&&status.getDateTimeEnd().isAfter(datetime)) {
					result.add(status);
				}
			}
		}
		return result;
	}

	@Override
	public ArrayList<Meeting> selectAllMeetings() {
		ArrayList<Meeting> result = (ArrayList<Meeting>) meetingRepo.findAll();
		return result;
	}

	@Override
	public Meeting selectMeetingById(int id) {
		Meeting result = meetingRepo.findByIdMeeting(id);
		return result;
	}

	@Override
	public ArrayList<Meeting> deleteMeetingById(int id) {
		meetingRepo.deleteById(id);
		ArrayList<Meeting> result = selectAllMeetings();
		return result;
	}

	@Override
	public Meeting insertNewMeeting(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd,
			GeneralStatus generalStatus) throws Exception {
		if(generalStatus!=GeneralStatus.DayOff) {
			Meeting result = new Meeting(dateTimeStart, dateTimeEnd, generalStatus);
			meetingRepo.save(result);
			return result;
		} else {
			throw new Exception("Meeting cannot be a day off");
		}
	}

	@Override
	public Meeting updateMeetingById(int id, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd,
			GeneralStatus generalStatus) {
		Meeting result = new Meeting();
		if (meetingRepo.existsById(id)) {
			result = meetingRepo.findByIdMeeting(id);
			result.setDateTimeStart(dateTimeStart);
			result.setDateTimeEnd(dateTimeEnd);
			result.setGeneralStatus(generalStatus);
			meetingRepo.save(result);
		}
		return result;
	}

	@Override
	public void addEmployee(int meetingId, Employee employee) {
		if(meetingRepo.existsById(meetingId)) {
			Meeting result = meetingRepo.findByIdMeeting(meetingId);
			result.addEmployee(employee);
			meetingRepo.save(result);
		}
		
	}

}
