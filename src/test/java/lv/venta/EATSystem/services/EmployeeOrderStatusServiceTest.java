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

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeOrderStatus;
import lv.venta.EATSystem.repos.IEmployeeOrderStatusRepo;
import lv.venta.EATSystem.services.impl.EmployeeOrderStatusServiceImpl;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
class EmployeeOrderStatusServiceTest {

	@InjectMocks
	private static EmployeeOrderStatusServiceImpl eosService;
	
	@Mock
	private static IEmployeeOrderStatusRepo eosRepo;
	
	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testCreate() {
		Employee employee = new Employee();
		EmployeeOrderStatus eos = new EmployeeOrderStatus(employee, GeneralStatus.DayOff);
		
		when(eosRepo.save(any())).thenReturn(eos);
		
		EmployeeOrderStatus eosFromService = eosService.insertNewEmployeeOrderStatus(eos.getEmployee(), eos.getGeneralStatus());
		
		assertEquals(GeneralStatus.DayOff, eosFromService.getGeneralStatus());
	}

}
