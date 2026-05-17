package lv.venta.EATSystem.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import lv.venta.EATSystem.enums.SecurityRole;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.services.IOtherService;

@RestController
@RequestMapping("/other")
@CrossOrigin(origins ="http://localhost:3000")
@Transactional
public class OtherController {

	@Autowired
	IOtherService otherService;
	
	@GetMapping("/roles")
	public Object[] getAllSecurityRoles() {
		return otherService.selectAllSecurityRoles();
	}
	
	@GetMapping("/orderstatus")
	public Object[] getAllOrderStatuses() {
		return otherService.selectAllOrderStatuses();
	}
	
	@GetMapping("/generalstatus")
	public Object[] getAllGeneralStatuses() {
		return otherService.selectAllGeneralStatuses();
	}
	
	@GetMapping("/meetingstatuses")
	public ArrayList<Object> getMeetingStatuses(){
		return otherService.selectInPersonAndOnlineStatuses();
	}
} 
