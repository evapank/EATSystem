package lv.venta.EATSystem.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.dto.DepartmentDTO;
import lv.venta.EATSystem.services.IDepartmentService;
import lv.venta.EATSystem.services.IEmployeeService;


@RestController
@RequestMapping("/department")
@CrossOrigin(origins ="http://localhost:3000")
@Transactional
public class DepartmentController {
	
	private final IDepartmentService departmentService;
	
	private final IEmployeeService employeeService;
	
	@Autowired
	public DepartmentController(IDepartmentService departmentService, IEmployeeService employeeService) {
		this.departmentService = departmentService;
		this.employeeService = employeeService;
	}
	
	@GetMapping("/all")
	public Collection<Department> getAllDepartments() {
		return departmentService.selectAllDepartments();
	}
	
	@GetMapping("/all/{id}")
	public Department getDepartmentById(@PathVariable(name = "id") int id) throws Exception { //TODO validation
		try {
		return departmentService.selectDepartmentById(id);
		} catch (Exception e) {
			throw new Exception("can't find");
		}
	}

	@DeleteMapping("/remove/{id}")
	public void deleteDepartmentById(Model model, @PathVariable(name = "id") int id) {
		departmentService.deleteDepartmentById(id);
	}
	
	@PostMapping("/create")
	public Department addDepartment (@Valid @RequestBody DepartmentDTO department, BindingResult result) throws Exception {
		if(!result.hasErrors()) {
			return departmentService.insertNewDepartment(department.getTitle(), department.getManagerId());
		} else {
			throw new Exception("can't create");
		}
	}
	
	@GetMapping("/getemployees")
	public Collection<Employee> showEmployeeList(){
		return employeeService.selectAllEmployees();
	}
	
	@GetMapping("/getmanager/{id}")
	public Employee showDepartmentManager(@PathVariable(name="id") int id) {
		return departmentService.getDepartmentManagerByDepartmentId(id);
	}
	
	@PutMapping("/update/{id}")
	public Department updateDepartmentById(@PathVariable(name="id") int id, @Valid @RequestBody DepartmentDTO department, BindingResult result) throws Exception {
	System.out.println(department);
		if(!result.hasErrors()) {
			return departmentService.updateDepartmentById(id, department.getTitle(), department.getManagerId());
		} else {
			throw new Exception("can't update");
		}
	}
	
	@PutMapping("/getemployees/{id}")
	public Collection<Employee> showDepartmentEmployeeList(@PathVariable(name = "id") int id){
		return departmentService.selectAllEmployeesInDepartment(id);
	}

}

