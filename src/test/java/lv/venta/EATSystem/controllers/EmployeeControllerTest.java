package lv.venta.EATSystem.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.services.IEmployeeService;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
class EmployeeControllerTest {

	@Mock
	private static IEmployeeService employeeService;
	
	@InjectMocks
	private static EmployeeController employeeController; 
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void test() {
		Department department = new Department();
		Employee employee1 = new Employee("Andy", "Anderson", "Designer", department, "anderson@gmail.com", false);
		Employee employee2 = new Employee("Barbara", "Johnson", "IT department manager", department, "b.johnson@gmail.com", true);
		
		ArrayList<Employee> allEmployees = new ArrayList<>(Arrays.asList(employee1, employee2));
		
		try {
			when(employeeService.selectAllEmployees()).thenReturn(allEmployees);
			
			mockMvc.perform(get("/employee/all"))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
			.andExpect((ResultMatcher) jsonPath("$[0].name", "Andy"))
			.andExpect((ResultMatcher) jsonPath("$[1].surname", "Johnson"));
		} catch (Exception e) {
			
		}
	}

}
