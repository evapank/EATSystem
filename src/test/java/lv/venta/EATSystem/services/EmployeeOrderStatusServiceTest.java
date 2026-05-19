package lv.venta.EATSystem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeOrderStatus;
import lv.venta.EATSystem.repos.IEmployeeOrderStatusRepo;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
class EmployeeOrderStatusServiceTest {

	@InjectMocks
	IEmployeeOrderStatusService eosService;
	
	@Mock
	IEmployeeOrderStatusRepo eosRepo;
	
	@Test
	void testCreate() {
		Employee employee = new Employee();
		EmployeeOrderStatus eos = new EmployeeOrderStatus(employee, GeneralStatus.DayOff);
		
		when(eosRepo.save(any())).thenReturn(eos);
		
		EmployeeOrderStatus eosFromService = eosService.insertNewEmployeeOrderStatus(employee, eos.getGeneralStatus());
		
		assertEquals(GeneralStatus.DayOff, eosFromService.getGeneralStatus());
	}

}
