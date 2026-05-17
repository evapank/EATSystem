package lv.venta.EATSystem.services;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.validation.Valid;
import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeStatus;
import lv.venta.EATSystem.models.Meeting;

public interface IMeetingService {
	
	public abstract ArrayList<EmployeeStatus> getAllEmployeeStatusByDateTime(LocalDateTime datetimeStart, LocalDateTime dateTimeEnd);
	
	public abstract ArrayList<Meeting> selectAllMeetings();
	
	public abstract Meeting selectMeetingById(int id);
	
	public abstract ArrayList<Meeting> deleteMeetingById(int id);
	
	public abstract Meeting insertNewMeeting(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, GeneralStatus generalStatus) throws Exception;
	
	public abstract Meeting updateMeetingById(int id, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, GeneralStatus generalStatus);
	
	public abstract void addEmployee(int meetingId, Employee employee);
}
