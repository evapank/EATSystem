package lv.venta.EATSystem.repos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeOrderStatus;

@DataJpaTest
class EmployeeOrderStatusRepoTest {
	
	@Autowired
	IEmployeeOrderStatusRepo eosRepo;

	@Test
	void testRepoSave() {
		Employee employee = new Employee();
		
		eosRepo.save(new EmployeeOrderStatus(employee, GeneralStatus.DayOff));
		
		assertNotNull(eosRepo.findByEmployeeIdEmployee(employee.getIdEmployee()));
	}

}
