package lv.venta.EATSystem.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import lv.venta.EATSystem.enums.GeneralStatus;

class EmployeeStatusTest {

	Employee employee = new Employee();
	
	EmployeeStatus empStRight = new EmployeeStatus(employee, GeneralStatus.Online, LocalDateTime.of(
            2026, Month.APRIL, 24, 14, 30, 00), LocalDateTime.of(
                    2026, Month.APRIL, 24, 15, 30, 00));
	
	@Test
	void test() {
		assertEquals("Online", GeneralStatus.Online.toString());
	}

}
