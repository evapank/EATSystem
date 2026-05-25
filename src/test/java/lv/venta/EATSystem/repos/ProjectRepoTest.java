package lv.venta.EATSystem.repos;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.Project;

@SpringBootTest(properties = "spring.profiles.active=test")
class ProjectRepoTest {

	@Autowired
	private IProjectRepo projectRepo;
	
	@Autowired
	private IEmployeeRepo employeeRepo;
	
	@Test
	void testRepoSave() {
		Employee employee = new Employee();
		employeeRepo.save(employee);
		projectRepo.save(new Project(10002, "Website testing", LocalDate.of(2026, Month.APRIL, 11), LocalDate.of(2026, Month.DECEMBER, 11), employee));
		
		assertNotNull(projectRepo.findByEmployeesIdEmployee(employee.getIdEmployee()));
	}
	
	@Test
	void testRepoUpdate() {
		Employee employee = new Employee();
		employeeRepo.save(employee);
		Project project = new Project(10002, "Website testing", LocalDate.of(2026, Month.APRIL, 11), LocalDate.of(2026, Month.DECEMBER, 11), employee);
		projectRepo.save(project);
		
		project.setTitle("Budget planning");
		projectRepo.save(project);
		
		assertEquals("Budget planning", projectRepo.findByIdProject(project.getIdProject()).getTitle());
		assertNotEquals("Website testing", projectRepo.findByIdProject(project.getIdProject()).getTitle());
	}

}
