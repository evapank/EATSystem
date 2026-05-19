package lv.venta.EATSystem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.repos.IDepartmentRepo;
import lv.venta.EATSystem.repos.IEmployeeRepo;
import lv.venta.EATSystem.services.impl.DepartmentServiceImpl;

@SpringBootTest(properties = "spring.profiles.active=test")
class DepartmentServiceTest {
	
	@Mock
	private static IDepartmentRepo departmentRepo;
	
	@InjectMocks
	private static DepartmentServiceImpl departmentService;
	
	@Mock
	private IEmployeeRepo employeeRepo;
	
	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testCreate() {
		Department department = new Department("Department");
		Employee employee = new Employee();
		
		when(departmentRepo.save(any())).thenReturn(department);
		when(employeeRepo.findByIdEmployee(employee.getIdEmployee())).thenReturn(employee);
		when(employeeRepo.save(any())).thenReturn(employee);
		
		employeeRepo.save(employee);
		Department departmentFromService = departmentService.insertNewDepartment(department.getTitle(), employee.getIdEmployee());
		
		assertEquals("Department", departmentFromService.getTitle());
	}

}
