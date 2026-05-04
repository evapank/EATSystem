package lv.venta.EATSystem.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import lv.venta.EATSystem.enums.OrderStatus;

class OrderTest {

	Project project = new Project();
	EmployeeOrderStatus eos = new EmployeeOrderStatus();
	
	Order orderRight = new Order(10001, project, LocalDate.of(2026, Month.APRIL, 16),
								LocalDateTime.of(2026, Month.APRIL, 24, 14, 30, 00),
								LocalDateTime.of(2026, Month.APRIL, 25, 15, 30, 00), OrderStatus.SickLeave, eos);
	@Test
	void test() {
		assertEquals(10001, orderRight.getOrderNumber());
		assertEquals("SickLeave", OrderStatus.SickLeave.toString());
	}

}
