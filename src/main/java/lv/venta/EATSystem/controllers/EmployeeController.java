package lv.venta.EATSystem.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.services.IEmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	IEmployeeService employeeService;
	
	@GetMapping("/all")
	public Collection<Employee> getAllEmployees() {
		return employeeService.selectAllEmployees();
	}
	
	@GetMapping("/all/{id}")
	public Employee getEmployeeById(@PathVariable(name = "id") int id) throws Exception {
		try {
			return employeeService.selectEmployeeById(id);
		} catch (Exception e) {
			throw new Exception("can't find");
		}
	}
	
	@GetMapping("/remove/{id}")
	public void deleteEmployeeById(@PathVariable(name = "id") int id) {
		employeeService.deleteEmployeeById(id);
	}
	
	@PostMapping("/create")
	public Employee postAddEmployee(@Valid Employee employee, BindingResult result) throws Exception {
		if(!result.hasErrors()) {
			return employeeService.insertNewEmployee(employee.getName(), employee.getSurname(), employee.getPostion(), employee.getDepartment(), employee.getEmail());
		} else {
			throw new Exception("can't create");
		}
	}
	
	@PutMapping("/update")
	public Employee updateEmployeeById(@PathVariable(name="id") int id, @Valid Employee employee, BindingResult result) throws Exception {
	if(!result.hasErrors()) {
			return employeeService.updateEmployeeById(id, employee.getName(), employee.getSurname(), employee.getPostion(), employee.getDepartment(), employee.getEmail());
		} else {
			throw new Exception("can't update");
		}
	}

}
