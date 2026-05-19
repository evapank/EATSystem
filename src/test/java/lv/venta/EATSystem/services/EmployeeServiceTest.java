package lv.venta.EATSystem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.repos.IEmployeeRepo;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
class EmployeeServiceTest {

	@InjectMocks
	IEmployeeService employeeService;
	
	@Mock
	IEmployeeRepo employeeRepo;
	
	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testCreate() {
		Department department = new Department();
		Employee employee = new Employee("Laura", "Berzina", "Social media manager", department, "lberzina1990@gmail.com", true);
		
		when(employeeRepo.save(any())).thenReturn(employee);
		
		Employee employeeFromService = employeeService.insertNewEmployee(employee.getName(), employee.getSurname(), employee.getPosition(),
				employee.getDepartment(), employee.getEmail(), employee.isManager());
		
		assertEquals("Berzina", employeeFromService.getSurname());
	}

}
