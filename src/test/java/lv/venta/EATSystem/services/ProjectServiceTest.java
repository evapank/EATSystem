package lv.venta.EATSystem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.Project;
import lv.venta.EATSystem.repos.IProjectRepo;
import lv.venta.EATSystem.services.impl.ProjectServiceImpl;

@SpringBootTest(properties = "spring.profiles.active=test")
class ProjectServiceTest {

	@InjectMocks
	ProjectServiceImpl projectService;
	
	@Mock
	IProjectRepo projectRepo;
	
	@Test
	void testCreate() {
		Employee employee = new Employee();
		Project project = new Project(54321, "Full-stack development", LocalDate.of(2026, Month.APRIL, 19),
				LocalDate.of(2027, Month.DECEMBER, 29), employee);
		
		when(projectRepo.save(any())).thenReturn(project);
		
		Project projectFromService = projectService.insertNewProject(project.getProjectNumber(), project.getTitle(),
				project.getDateStart(), project.getDateEnd(), project.getProjectManager());
		
		assertEquals("Full-stack development", projectFromService.getTitle());
	}

}
