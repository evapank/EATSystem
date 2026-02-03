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
import lv.venta.EATSystem.models.EmployeeStatus;
import lv.venta.EATSystem.services.IEmployeeStatusService;

@RestController
@RequestMapping("/employeestatus")
public class EmployeeStatusController {
	
	@Autowired
	IEmployeeStatusService emplStatusService;
	
	@GetMapping("/all")
	public Collection<EmployeeStatus> getAllEmployeeStatuses() {
		return emplStatusService.selectAllEmployeeStatuses();
	}
	
	@GetMapping("/all/{id}")
	public EmployeeStatus getEmployeeStatusById(@PathVariable(name = "id") int id) throws Exception {
		try {
			return emplStatusService.selectEmployeeStatusById(id);
		} catch (Exception e) {
			throw new Exception("can't find");
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public void deleteEmployeeStatusById(@PathVariable(name = "id") int id) {
		emplStatusService.deleteEmployeeStatusById(id);
	}
	
	@PostMapping("/create")
	public EmployeeStatus postAddEmployeeOrderStatus(@Valid @RequestBody EmployeeStatus employeeStatus, BindingResult result) throws Exception {
		if(!result.hasErrors()) {
			return emplStatusService.insertNewEmployeeStatus(employeeStatus.getEmployee(), employeeStatus.getGeneralStatus(),
					employeeStatus.getDateTimeStart(), employeeStatus.getDateTimeEnd());
		} else {
			throw new Exception("can't create");
		}
	}
	
	@PutMapping("/update/{id}")
	public EmployeeStatus updateEmployeeStatusById(@PathVariable(name="id") int id, @Valid @RequestBody EmployeeStatus employeeStatus, BindingResult result) throws Exception {
	if(!result.hasErrors()) {
			return emplStatusService.updateEmployeeStatusById(id, employeeStatus.getEmployee(), employeeStatus.getGeneralStatus(),
					employeeStatus.getDateTimeStart(), employeeStatus.getDateTimeEnd());
		} else {
			throw new Exception("can't update");
		}
	}

}
