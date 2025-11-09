package lv.venta.EATSystem.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.Project;
import lv.venta.EATSystem.repos.IProjectRepo;
import lv.venta.EATSystem.services.IProjectService;

public class ProjectServiceImpl implements IProjectService{
	
	private IProjectRepo projectRepo;

	@Override
	public ArrayList<Project> selectAllProjects() {
		ArrayList<Project> result = (ArrayList<Project>) projectRepo.findAll();
		return result;
	}

	@Override
	public Project selectProjectById(int id) throws Exception {
		Project result = projectRepo.findByIdProject(id);
		return result;
	}

	@Override
	public ArrayList<Project> deleteProjectById(int id) {
		projectRepo.deleteByIdProject(id);
		ArrayList<Project> result = selectAllProjects();
		return result;
	}

	@Override
	public Project insertNewProject(int projectNumber, String title, LocalDate dateStart, LocalDate dateEnd,
			Employee projectManager) {
		Project result = new Project(projectNumber, title, dateStart, dateEnd, projectManager);
		return result;
	}

	@Override
	public Project updateProjectById(int id, int projectNumber, String title, LocalDate dateStart, LocalDate dateEnd,
			Employee projectManager) throws Exception {
		Project result = projectRepo.findByIdProject(id);
		result = new Project(projectNumber, title, dateStart, dateEnd, projectManager);
		return result;
	}

}
