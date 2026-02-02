package lv.venta.EATSystem.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lv.venta.EATSystem.models.Project;
import lv.venta.EATSystem.services.IProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	IProjectService projectService;
	
	@GetMapping("/all")
	public Collection<Project> getAllProjects() {
		return projectService.selectAllProjects();
	}
	
	@GetMapping("/all/{id}")
	public Project getProjectById(@PathVariable(name = "id") int id) throws Exception {
		try {
			return projectService.selectProjectById(id);
		} catch (Exception e) {
			throw new Exception("can't find");
		}
	}
	
	@GetMapping("/remove/{id}")
	public void deleteProjectById(Model model, @PathVariable(name = "id") int id) {
		projectService.deleteProjectById(id);
	}
	
	@PostMapping("/create")
	public Project postAddProject(@Valid @RequestBody Project project, BindingResult result) throws Exception {
		if(!result.hasErrors()) {
			return projectService.insertNewProject(project.getProjectNumber(), project.getTitle(), project.getDateStart(), project.getDateEnd(),
					project.getProjectManager());
		} else {
			throw new Exception("can't create");
		}
	}
	
	@PutMapping("/update/{id}")
	public Project updateProject(@Valid @RequestBody Project project, BindingResult result,  @PathVariable(name = "id") int id) throws Exception {
		if(!result.hasErrors()) {
			return projectService.updateProjectById(id, project.getProjectNumber(), project.getTitle(), project.getDateStart(), project.getDateEnd(),
					project.getProjectManager());
		} else {
			throw new Exception("can't create");
		}
	}
}
