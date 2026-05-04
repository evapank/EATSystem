package lv.venta.EATSystem.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lv.venta.EATSystem.enums.GeneralStatus;

class EmployeeOrderStatusTest {

	Employee employee = new Employee();
	
	EmployeeOrderStatus eos = new EmployeeOrderStatus(employee, GeneralStatus.InPerson);
	
	@Test
	void test() {
		
		assertEquals("InPerson", GeneralStatus.InPerson.toString());
	}

}
