package lv.venta.EATSystem.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeeTest {

Department department = new Department();
	
	Employee empRight = new Employee("Eva", "Pankevica", "Engineer", department, "evaPankevica@gmail.com", false);
	Employee empWrong = new Employee("2", "!{123", "234rdddddfg", department, "www.venta.lv", true);
	
	@Test
	void test() {
		//right
		assertEquals("Eva", empRight.getName());
		assertEquals("Pankevica", empRight.getSurname());
		assertEquals("Engineer", empRight.getPosition());
		assertEquals("evaPankevica@gmail.com", empRight.getEmail());
			
		//wrong
		assertEquals("", empWrong.getName());
		assertEquals("", empWrong.getSurname());
		assertEquals("", empWrong.getPosition());
		assertEquals("", empWrong.getEmail());
	}

}
