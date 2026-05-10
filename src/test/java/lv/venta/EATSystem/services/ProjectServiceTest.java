package lv.venta.EATSystem.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.models.Project;
import lv.venta.EATSystem.repos.IProjectRepo;

@SpringBootTest(properties = "spring.profiles.active=test")
class ProjectServiceTest {

	@Autowired
	IProjectService projectService;
	
	@Autowired
	IProjectRepo projectRepo;
	
	@Test
	void testSelectAll() {
		Project project = new Project();
		projectRepo.save(project);
		
		assertFalse(projectService.selectAllProjects().isEmpty());
	}

}
