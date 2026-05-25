package lv.venta.EATSystem.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.Meeting;
import lv.venta.EATSystem.models.Project;
import lv.venta.EATSystem.services.IProjectService;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
class ProjectControllerTest {

	@Mock
	private static IProjectService projectService;
	
	@InjectMocks
	private static ProjectController projectController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void test() {
		Employee employee = new Employee();
		Project project1 = new Project(30023, "App testing", LocalDate.of(2026, Month.APRIL, 12), LocalDate.of(2026, Month.APRIL, 26), employee);
		Project project2 = new Project(30025, "Budget planning", LocalDate.of(2026, Month.JUNE, 1), LocalDate.of(2026, Month.JUNE, 5), employee);
		
		ArrayList<Project> allProjects = new ArrayList<>(Arrays.asList(project1, project2));
		
		try {
			when(projectService.selectAllProjects()).thenReturn(allProjects);
			
			mockMvc.perform(get("/project/all"))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
			.andExpect((ResultMatcher) jsonPath("$[0].title", "App testing"))
			.andExpect((ResultMatcher) jsonPath("$[1].title", "Budget planning"));
		} catch (Exception e){
			
		}
	}

}
