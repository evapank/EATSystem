package lv.venta.EATSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lv.venta.EATSystem.models.EmployeeStatus;
import lv.venta.EATSystem.services.IEmployeeStatusService;

@Controller
@RequestMapping("/employeestatus")
public class EmployeeStatusController {
	
	@Autowired
	IEmployeeStatusService emplStatusService;
	
	@GetMapping("/all")
	public String getAllEmployeeStatuses(Model model) {
		model.addAttribute("employeeStatus", emplStatusService.selectAllEmployeeStatuses());
		return "status/employeeStatus/employee-status-all-page";
	}
	
	@GetMapping("/all/{id}")
	public String getEmployeeStatusById(Model model, @PathVariable(name = "id") int id) {
		try {
			model.addAttribute("employeeStatus", emplStatusService.selectEmployeeStatusById(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "error-page";
		}
		return "status/employeeStatus/employee-status-all-page";
	}
	
	@GetMapping("/remove/{id}")
	public String deleteEmployeeStatusById(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("employeeStatus", emplStatusService.deleteEmployeeStatusById(id));
		return "status/employeeStatus/employee-status-all-page";
	}
	
	@GetMapping("/create")
	public String getAddEmployeeStatus(EmployeeStatus employeeStatus) {
		return "status/employeeStatus/employee-status-all-page";
	}
	
	@PostMapping("/create")
	public String postAddEmployeeOrderStatus(@Valid EmployeeStatus employeeStatus, BindingResult result) {
		if(!result.hasErrors()) {
			emplStatusService.insertNewEmployeeStatus(employeeStatus.getEmployee(), employeeStatus.getGeneralStatus(),
					employeeStatus.getDateTimeStart(), employeeStatus.getDateTimeEnd());
		}
		return "status/employeeStatus/employee-status-all-page";
	}

}
