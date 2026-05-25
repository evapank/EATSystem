package lv.venta.EATSystem.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active=test")
class DepartmentTest {

	Department depRight = new Department("Sales");
	
	@Test
	void test() {
		
		assertEquals("Sales", depRight.getTitle());

	}

}
