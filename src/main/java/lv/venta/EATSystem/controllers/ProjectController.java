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
import lv.venta.EATSystem.models.Project;
import lv.venta.EATSystem.services.IProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	IProjectService projectService;
	
	@GetMapping("/all")
	public String getAllProjects(Model model) {
		model.addAttribute("project", projectService.selectAllProjects());
		return "project/project-all-page";
	}
	
	@GetMapping("/all/{id}")
	public String getProjectById(Model model, @PathVariable(name = "id") int id) {
		try {
			model.addAttribute("project", projectService.selectProjectById(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "error-page";
		}
		return "project/project-one-page";
	}
	
	@GetMapping("/remove/{id}")
	public String deleteProjectById(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("project", projectService.deleteProjectById(id));
		return "project/project-all-page";
	}
	
	@GetMapping("/create")
	public String getAddProject(Project project) {
		return "project/project-add-page";
	}
	
	@PostMapping("/create")
	public String postAddProject(@Valid Project project, BindingResult result) {
		if(!result.hasErrors()) {
			projectService.insertNewProject(project.getProjectNumber(), project.getTitle(), project.getDateStart(), project.getDateEnd(),
					project.getProjectManager());
		}
		return "project/project-add-page";
	}


}
