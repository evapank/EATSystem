package lv.venta.EATSystem.repos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeOrderStatus;

@SpringBootTest(properties = "spring.profiles.active=test")
class EmployeeOrderStatusRepoTest {
	
	@Autowired
	private IEmployeeOrderStatusRepo eosRepo;
	
	@Autowired
	private IEmployeeRepo employeeRepo;

	@Test
	void testRepoSave() {
		Employee employee = new Employee();
		
		employeeRepo.save(employee);
		
		eosRepo.save(new EmployeeOrderStatus(employee, GeneralStatus.DayOff));
		
		employeeRepo.save(employee);
		
		assertNotNull(eosRepo.findByEmployeeIdEmployee(employee.getIdEmployee()));
	}

}
