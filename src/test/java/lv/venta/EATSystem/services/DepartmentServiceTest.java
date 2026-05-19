package lv.venta.EATSystem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.repos.IDepartmentRepo;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
class DepartmentServiceTest {

	@InjectMocks
	IDepartmentService departmentService;
	
	@Mock
	IDepartmentRepo departmentRepo;
	
	@Test
	void testCreate() {
		Department department = new Department("Department");
		Employee employee = new Employee();
		
		when(departmentRepo.save(any())).thenReturn(department);
		
		Department departmentFromService = departmentService.insertNewDepartment(department.getTitle(), employee.getIdEmployee());
		
		assertEquals("Department", departmentFromService.getTitle());
	}

}
