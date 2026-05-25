package lv.venta.EATSystem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeStatus;
import lv.venta.EATSystem.repos.IEmployeeStatusRepo;
import lv.venta.EATSystem.services.impl.EmployeeStatusServiceImpl;

@SpringBootTest(properties = "spring.profiles.active=test")
class EmployeeStatusServiceTest {

	@InjectMocks
	private static EmployeeStatusServiceImpl emplStService;
	
	@Mock
	private static IEmployeeStatusRepo empStRepo;
	
	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testCreate() {
		Employee employee = new Employee();
		EmployeeStatus empSt = new EmployeeStatus(employee, GeneralStatus.InPerson,
				LocalDateTime.of(2026, Month.JULY, 7, 12, 30), LocalDateTime.of(2026, Month.JULY, 7, 16, 30));
		
		when(empStRepo.save(any())).thenReturn(empSt);
		
		assertEquals(LocalDateTime.of(2026, Month.JULY, 7, 12, 30), empSt.getDateTimeStart());
	}

}
