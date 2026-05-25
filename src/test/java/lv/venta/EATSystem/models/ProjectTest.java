package lv.venta.EATSystem.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.profiles.active=test")
class ProjectTest {

	Employee employee = new Employee();
	
	Project projectRight = new Project(20001, "App development", LocalDate.of(2026, Month.APRIL, 10), LocalDate.of(2027, Month.APRIL, 10), employee);
	
	@Test
	void test() {
		assertEquals(20001, projectRight.getProjectNumber());
		assertEquals("App development", projectRight.getTitle());
	}

}
