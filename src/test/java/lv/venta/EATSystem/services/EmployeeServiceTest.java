package lv.venta.EATSystem.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.repos.IEmployeeRepo;

@SpringBootTest(properties = "spring.profiles.active=test")
class EmployeeServiceTest {

	@Autowired
	IEmployeeService employeeService;
	
	@Autowired
	IEmployeeRepo employeeRepo;
	
	@Test
	void test() {
		Employee employee = new Employee();
		employeeRepo.save(employee);
		
		assertFalse(employeeService.selectAllEmployees().isEmpty());
	}

}
