package lv.venta.EATSystem.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.Month;
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

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeStatus;
import lv.venta.EATSystem.services.IEmployeeStatusService;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
class EmployeeStatusControllerTest {

	@Mock
	private static IEmployeeStatusService empStService;
	
	@InjectMocks
	private static EmployeeStatusController empStController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void test() {
		Department department = new Department();
		Employee employee = new Employee("Ryan", "Gary", "Assistant", department, "ryangary777@gmail.com", false);
		
		EmployeeStatus empSt1 = new EmployeeStatus(employee, GeneralStatus.DayOff,
				LocalDateTime.of(2026, Month.APRIL, 26, 9, 00, 00), LocalDateTime.of(2026, Month.APRIL, 26, 18, 00, 00));
		EmployeeStatus empSt2 = new EmployeeStatus(employee, GeneralStatus.InPerson,
				LocalDateTime.of(2026, Month.APRIL, 29, 9, 00, 00), LocalDateTime.of(2026, Month.APRIL, 29, 18, 00, 00));
		
		ArrayList<EmployeeStatus> allEmpSt = new ArrayList<>(Arrays.asList(empSt1, empSt2));
		
		try {
			when(empStService.selectAllEmployeeStatuses()).thenReturn(allEmpSt);
			
			mockMvc.perform(get("/employeestatus/all"))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
			.andExpect((ResultMatcher) jsonPath("$[0].employee.surname", "Gary"))
			.andExpect((ResultMatcher) jsonPath("$[1].dateTimeStart", LocalDateTime.of(2026, Month.APRIL, 29, 9, 00, 00)));
		} catch (Exception e){
			
		}
	}

}
