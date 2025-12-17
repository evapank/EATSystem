package lv.venta.EATSystem.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.services.IDepartmentService;
import lv.venta.EATSystem.services.IEmployeeService;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins ="http://localhost:3000")
public class DepartmentController {
	
	@Autowired
	IDepartmentService departmentService;
	
	@Autowired
	IEmployeeService employeeService;
	
	@GetMapping("/all")
	public Collection<Department> getAllDepartments() {
		return departmentService.selectAllDepartments();
	}
	
	@GetMapping("/all/{id}")
	public Department getDepartmentById(@PathVariable(name = "id") int id) throws Exception { //TODO validation
		try {
			System.out.println(departmentService.selectDepartmentById(id));
		return departmentService.selectDepartmentById(id);
		} catch (Exception e) {
			throw new Exception("can't find");
		}
	}

	@GetMapping("/remove/{id}")
	public void deleteDepartmentById(Model model, @PathVariable(name = "id") int id) {
		departmentService.deleteDepartmentById(id);
	}
	
	@PostMapping("/create")
	public Department addDepartment (@Valid Department department, BindingResult result) throws Exception {
		if(!result.hasErrors()) {
			return departmentService.insertNewDepartment(department.getTitle(), department.getManager());
		} else {
			throw new Exception("can't create");
		}
	}
	
	@GetMapping("/create/getemployees")
	public Collection<Employee> showEmployeeList(){
		System.out.println(employeeService.selectAllEmployees());
		return employeeService.selectAllEmployees();
	}
	
	@PutMapping("/update/{id}")
	public Department updateDepartmentById(@PathVariable(name="id") int id, @Valid Department department, BindingResult result) throws Exception {
	if(!result.hasErrors()) {
			return departmentService.updateDepartmentById(id, department.getTitle(), department.getManager());
		} else {
			throw new Exception("can't update");
		}
	}
	
	@GetMapping("/update/{id}")
	public Collection<Employee> showDepartmentEmployeeList(@PathVariable(name = "id") int id){
		return departmentService.selectAllEmployeesInDepartment(id);
	}

}

