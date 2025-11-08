package lv.venta.EATSystem.services;

import java.time.LocalDate;
import java.util.ArrayList;

import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.Project;

public interface IProjectService {
	
	
	public abstract ArrayList<Project> selectAllProjects();
	
	public abstract Project selectProjectById(int id) throws Exception;
	
	public abstract ArrayList<Project> deleteProjectById(int id);
	
	public abstract Project insertNewProject(int projectNumber, String title, LocalDate dateStart, LocalDate dateEnd, Employee projectManager);
	
	public abstract Project updateProjectById(int id, int projectNumber, String title, LocalDate dateStart, LocalDate dateEnd, Employee projectManager) throws Exception;


}
