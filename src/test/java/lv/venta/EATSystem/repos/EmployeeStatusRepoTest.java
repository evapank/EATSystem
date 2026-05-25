package lv.venta.EATSystem.repos;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeStatus;

@SpringBootTest(properties = "spring.profiles.active=test")
class EmployeeStatusRepoTest {

	@Autowired
	private IEmployeeStatusRepo empStRepo;
	
	@Autowired
	private IEmployeeRepo employeeRepo;
	
	@Test
	void testRepoSave() {
		Employee employee = new Employee(); 
		
		employeeRepo.save(employee);
		
		empStRepo.save(new EmployeeStatus(employee, GeneralStatus.DayOff,
						LocalDateTime.of(2026, Month.APRIL, 25, 8, 00, 00), LocalDateTime.of(2026, Month.APRIL, 26, 18, 30, 00)));
		
		assertNotNull(empStRepo.findByEmployeeIdEmployee(employee.getIdEmployee()));
		
	}
	
	@Test
	void testRepoUpdate() {
		Employee employee = new Employee(); 
		
		employeeRepo.save(employee);
		empStRepo.save(new EmployeeStatus(employee, GeneralStatus.DayOff,
						LocalDateTime.of(2026, Month.APRIL, 25, 8, 00, 00), LocalDateTime.of(2026, Month.APRIL, 26, 18, 30, 00)));
		
		EmployeeStatus updated = empStRepo.findByEmployeeAndDateTimeStart(employee, LocalDateTime.of(2026, Month.APRIL, 25, 8, 00, 00));
		updated.setGeneralStatus(GeneralStatus.InPerson);
		empStRepo.save(updated);
		employeeRepo.save(employee);
		
		assertEquals(GeneralStatus.InPerson, empStRepo.findByEmployeeAndDateTimeStart(employee, LocalDateTime.of(2026, Month.APRIL, 25, 8, 00, 00)).getGeneralStatus());
		
	}

}
