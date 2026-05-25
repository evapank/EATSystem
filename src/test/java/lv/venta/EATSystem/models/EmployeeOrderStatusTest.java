package lv.venta.EATSystem.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.enums.GeneralStatus;

@SpringBootTest(properties = "spring.profiles.active=test")
class EmployeeOrderStatusTest {

	private Employee employee = new Employee();
	
	private EmployeeOrderStatus eos = new EmployeeOrderStatus(employee, GeneralStatus.InPerson);
	
	@Test
	void test() {
		
		assertEquals("InPerson", GeneralStatus.InPerson.toString());
	}

}
