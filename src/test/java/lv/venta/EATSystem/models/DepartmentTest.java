package lv.venta.EATSystem.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

class DepartmentTest {

	Department depRight = new Department("Sales");
	Department depWrong = new Department("1");
	
	@Test
	void test() {
		
		assertEquals("Sales", depRight.getTitle());
		
		assertEquals("", depWrong.getTitle());
	}

}
