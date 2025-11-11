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
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.services.IEmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	IEmployeeService employeeService;
	
	@GetMapping("/all")
	public String getAllEmployees(Model model) {
		model.addAttribute("employee", employeeService.selectAllEmployees());
		return "employee/employee-all-page";
	}
	
	@GetMapping("/all/{id}")
	public String getEmployeeById(Model model, @PathVariable(name = "id") int id) {
		try {
			model.addAttribute("employee", employeeService.selectEmployeeById(id));
		} catch (Exception e) {
			return "error-page";
		}
		return "employee/employee-one-page";
	}
	
	@GetMapping("/remove/{id}")
	public String deleteEmployeeById(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("employee", employeeService.deleteEmployeeById(id));
		return "employee/employee-all-page";
	}
	
	@GetMapping("/create")
	public String getAddEmployee(Employee employee) {
		return "employee/employee-add-page";
	}
	
	@PostMapping("/create")
	public String postAddEmployee(@Valid Employee employee, BindingResult result) {
		if(!result.hasErrors()) {
			employeeService.insertNewEmployee(employee.getName(), employee.getSurname(), employee.getPostion(), employee.getDepartment(), employee.getEmail());
		}
		return "employee/employee-add-page";
	}

}
