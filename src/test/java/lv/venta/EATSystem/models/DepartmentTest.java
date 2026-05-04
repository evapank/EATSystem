package lv.venta.EATSystem.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DepartmentTest {

	Department depRight = new Department("Sales");
	Department depWrong = new Department("1");
	
	@Test
	void testDepartmentCreation() {
		
		assertEquals("Sales", depRight.getTitle());
		
		assertEquals("", depWrong.getTitle());
	}
}
