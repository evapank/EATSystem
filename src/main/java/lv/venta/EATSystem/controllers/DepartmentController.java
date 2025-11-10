package lv.venta.EATSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.services.IDepartmentService;

@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	IDepartmentService departmentService;
	
	@GetMapping("/all")
	public String getAllDepartments(Model model) {
		model.addAttribute("department", departmentService.selectAllDepartments());
		return "department-all-page";
	}
	
	@GetMapping("/all/{id}")
	public String getDepartmentById(Model model, @PathVariable(name = "id") int id) {
		try {
			model.addAttribute("department", departmentService.selectDepartmentById(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "department-one-page";
	}

	@GetMapping("/remove/{id}")
	public String deleteDepartmentById(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("department", departmentService.deleteDepartmentById(id));
		return "department-all-page";
	}
	
	@GetMapping("/create")
	public String getAddDepartment(Department department) {
		return "department-add-page";
	}
	
	@PostMapping("/create")
	public String postAddDepartment (@Valid Department department, BindingResult result) {
		if(!result.hasErrors()) {
			departmentService.insertNewDepartment(department.getTitle(), department.getManager());
		}
		return "department-add-page";
	}

}
