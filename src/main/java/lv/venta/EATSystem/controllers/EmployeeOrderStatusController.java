package lv.venta.EATSystem.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lv.venta.EATSystem.models.EmployeeOrderStatus;
import lv.venta.EATSystem.models.dto.EmployeeOrderStatusDTO;
import lv.venta.EATSystem.services.IEmployeeOrderStatusService;

@RestController
@RequestMapping("/employeeorderstatus")
public class EmployeeOrderStatusController {
	
	@Autowired
	IEmployeeOrderStatusService eosService;
	
	@GetMapping("/all")
	public Collection<EmployeeOrderStatus> getAllEmployeeOrderStatuses() {
		eosService.selectAllEmployeeOrderStatuses();
		return eosService.selectAllEmployeeOrderStatuses();
	}
	
	@GetMapping("/all/{id}")
	public EmployeeOrderStatus getEmployeeOrderStatusById(@PathVariable(name = "id") int id) throws Exception {
		try {
			return eosService.selectEmployeeOrderStatusById(id);
		} catch (Exception e) {
			throw new Exception("can't find");
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public void deleteEmployeeOrderStatusById(@PathVariable(name = "id") int id) {
		eosService.deleteEmployeeOrderStatusById(id);
	}
	
	@PostMapping("/create")
	public EmployeeOrderStatus postAddEmployeeOrderStatus(@Valid @RequestBody EmployeeOrderStatusDTO eos, BindingResult result) throws Exception {
		if(!result.hasErrors()) {
			return eosService.insertNewEmployeeOrderStatus(eos.getEmployee(), eos.getGeneralStatus());
		} else {
			throw new Exception("can't create");
		}
	}
	
	@PutMapping("/update/{id}")
	public EmployeeOrderStatus updateEmployeeOrderStatusById(@PathVariable(name="id") int id, @Valid @RequestBody EmployeeOrderStatusDTO eos, BindingResult result) throws Exception {
	if(!result.hasErrors()) {
			return eosService.updateEmployeeOrderStatusById(id, eos.getEmployee(), eos.getGeneralStatus());
		} else {
			throw new Exception("can't update");
		}
	}
}
