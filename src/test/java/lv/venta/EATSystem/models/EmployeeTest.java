package lv.venta.EATSystem.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active=test")
class EmployeeTest {

Department department = new Department();
	
	Employee empRight = new Employee("Eva", "Pankevica", "Engineer", department, "evaPankevica@gmail.com", false);
	
	@Test
	void test() {
		assertEquals("Eva", empRight.getName());
		assertEquals("Pankevica", empRight.getSurname());
		assertEquals("Engineer", empRight.getPosition());
		assertEquals("evaPankevica@gmail.com", empRight.getEmail());
	}

}
