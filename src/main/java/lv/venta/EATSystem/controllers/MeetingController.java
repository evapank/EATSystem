package lv.venta.EATSystem.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeStatus;
import lv.venta.EATSystem.models.Meeting;
import lv.venta.EATSystem.models.dto.MeetingDTO;
import lv.venta.EATSystem.services.IMeetingService;

@RestController
@RequestMapping("/meeting")
@CrossOrigin(origins ="http://localhost:3000")
@Transactional
public class MeetingController {
	
	@Autowired
	IMeetingService meetingService;
	
	@GetMapping("/all")
	public Collection<Meeting> getAllMeetings(){
		return meetingService.selectAllMeetings();
	}

	@GetMapping("/all/{id}")
	public Meeting getMeetingById(@PathVariable(name = "id") int id) throws Exception{
		try {
			return meetingService.selectMeetingById(id);
		} catch (Exception e){
			throw new Exception("can't find");
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public void deletemMeetingById(Model model, @PathVariable(name = "id") int id) {
		meetingService.deleteMeetingById(id);
	}
	
	@PutMapping("/update/{id}")
	public Meeting updateMeetingById(@PathVariable(name="id") int id, @Valid @RequestBody Meeting meeting, BindingResult result) throws Exception {
		if(!result.hasErrors()) {
			return meetingService.updateMeetingById(id, meeting.getDateTimeStart(), meeting.getDateTimeEnd(), meeting.getGeneralStatus());
		} else {
			throw new Exception("can't update");
		}
	}
	
	@PutMapping("/employeestatuses")
	public Collection<EmployeeStatus> setEmployeesAndStatusesForTheDayTime(@RequestBody(required = false) MeetingDTO meetingdto) {
		System.out.println(meetingdto);
		return meetingService.getAllEmployeeStatusByDateTime(meetingdto.getDateTimeStart(), meetingdto.getDateTimeEnd());
	}
	
	@PostMapping("/create")
	public Meeting postAddMeeting(@Valid @RequestBody Meeting meeting, BindingResult result) throws Exception {
		if(!result.hasErrors()) {
			return meetingService.insertNewMeeting(meeting.getDateTimeStart(),
												   meeting.getDateTimeEnd(), meeting.getGeneralStatus());
		} else {
			throw new Exception ("can't create");
		}
	}
	
	@PostMapping("/addEmployee")
	public void AddEmployeeToMeeting(int id, @Valid @RequestBody Employee employee, BindingResult result) {
		meetingService.addEmployee(id, employee);
	}
}
