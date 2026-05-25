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

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeOrderStatus;
import lv.venta.EATSystem.services.IEmployeeOrderStatusService;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
class EmployeeOrderStatusControllerTest {
	
	@Mock
	private static IEmployeeOrderStatusService eosService;
	
	@InjectMocks
	private static EmployeeOrderStatusController eosController;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void test() {
		Department department = new Department();
		Employee employee = new Employee("Judy", "Thompson", "Accountant", department, "thomson1980@gmail.com", false);
		
		EmployeeOrderStatus eos1 = new EmployeeOrderStatus(employee, GeneralStatus.InPerson);
		EmployeeOrderStatus eos2 = new EmployeeOrderStatus(employee, GeneralStatus.Online);
		
		ArrayList<EmployeeOrderStatus> allEos = new ArrayList<>(Arrays.asList(eos1, eos2));
		
		try {
		when(eosService.selectAllEmployeeOrderStatuses()).thenReturn(allEos);
		
		mockMvc.perform(get("/employeeorderstatus/all"))
		.andExpect(status().isOk())
		.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
		.andExpect((ResultMatcher) jsonPath("$[0].employee.surname", "Thompson"))
		.andExpect((ResultMatcher) jsonPath("$[1].generalStatus", GeneralStatus.Online));
		} catch (Exception e){
			
		}
	}

}
