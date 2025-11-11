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
import lv.venta.EATSystem.models.EmployeeOrderStatus;
import lv.venta.EATSystem.services.IEmployeeOrderStatusService;

@Controller
@RequestMapping("/employeeorderstatus")
public class EmployeeOrderStatusController {
	
	@Autowired
	IEmployeeOrderStatusService eosService;
	
	@GetMapping("/all")
	public String getAllEmployeeOrderStatuses(Model model) {
		model.addAttribute("eos", eosService.selectAllEmployeeOrderStatuses());
		return "status/employeeOrderStatus/eos-all-page";
	}
	
	@GetMapping("/all/{id}")
	public String getEmployeeOrderStatusById(Model model, @PathVariable(name = "id") int id) {
		try {
			model.addAttribute("eos", eosService.selectEmployeeOrderStatusById(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "status/employeeOrderStatus/eos-all-page";
	}
	
	@GetMapping("/remove/{id}")
	public String deleteEmployeeOrderStatusById(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("eos", eosService.deleteEmployeeOrderStatusById(id));
		return "status/employeeOrderStatus/eos-all-page";
	}
	
	@GetMapping("/create")
	public String getAddEmployeeOrderStatus(EmployeeOrderStatus eos) {
		return "status/employeeOrderStatus/eos-all-page";
	}
	
	@PostMapping("/create")
	public String postAddEmployeeOrderStatus(@Valid EmployeeOrderStatus eos, BindingResult result) {
		if(!result.hasErrors()) {
			eosService.insertNewEmployeeOrderStatus(eos.getEmployee(), eos.getGeneralStatus());
		}
		return "status/employeeOrderStatus/eos-all-page";
	}
}
