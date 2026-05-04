package lv.venta.EATSystem.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DepartmentTest {

	Department depRight = new Department("Sales");
	Department depWrong = new Department("1");
	
	@Test
	void test() {
		
		assertEquals("Sales", depRight.getTitle());
		
		assertEquals("", depWrong.getTitle());
	}

}
