package lv.venta.EATSystem.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.models.EmployeeOrderStatus;
import lv.venta.EATSystem.repos.IEmployeeOrderStatusRepo;

@SpringBootTest(properties = "spring.profiles.active=test")
class EmployeeOrderStatusServiceTest {

	@Autowired
	IEmployeeOrderStatusService eosService;
	
	@Autowired
	IEmployeeOrderStatusRepo eosRepo;
	
	@Test
	void testSelectAll() {
		EmployeeOrderStatus eos = new EmployeeOrderStatus();
		eosRepo.save(eos);
		
		assertFalse(eosService.selectAllEmployeeOrderStatuses().isEmpty());
	}

}
