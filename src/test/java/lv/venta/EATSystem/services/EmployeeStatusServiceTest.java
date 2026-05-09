package lv.venta.EATSystem.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.models.EmployeeStatus;
import lv.venta.EATSystem.repos.IEmployeeStatusRepo;

@SpringBootTest(properties = "spring.profiles.active=test")
class EmployeeStatusServiceTest {

	@Autowired
	IEmployeeStatusService emplStService;
	
	@Autowired
	IEmployeeStatusRepo empStRepo;
	
	@Test
	void testSelectAll() {
		EmployeeStatus empSt = new EmployeeStatus();
		empStRepo.save(empSt);
		
		assertFalse(emplStService.selectAllEmployeeStatuses().isEmpty());
	}

}
