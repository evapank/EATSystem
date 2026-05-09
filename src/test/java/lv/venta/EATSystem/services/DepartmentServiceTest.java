package lv.venta.EATSystem.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.repos.IDepartmentRepo;

@SpringBootTest(properties = "spring.profiles.active=test")
class DepartmentServiceTest {

	@Autowired
	IDepartmentService departmentService;
	
	@Autowired
	IDepartmentRepo departmentRepo;
	@Test
	void testSelectAll() {
		Department department = new Department();
		departmentRepo.save(department);
		
		assertFalse(departmentService.selectAllDepartments().isEmpty());
	}

}
