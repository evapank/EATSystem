package lv.venta.EATSystem.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import lv.venta.EATSystem.services.IMeetingService;

@RestController
@RequestMapping("/meeting")
@CrossOrigin(origins ="http://localhost:3000")
@Transactional
public class MeetingController {
	
	@Autowired
	IMeetingService meetingService;

	@GetMapping("/employeestatuses")
	public void getEmployeesAndStatusesForTheDayTime(LocalDateTime datetime) {
		meetingService.getAllEmployeeStatusByDateTime(datetime);
	}
}
