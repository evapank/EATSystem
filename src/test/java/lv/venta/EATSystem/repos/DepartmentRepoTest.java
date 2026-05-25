package lv.venta.EATSystem.repos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.models.Department;

@SpringBootTest(properties = "spring.profiles.active=test")
class DepartmentRepoTest {
	
	@Autowired
	private IDepartmentRepo departmentRepo;

	@Test
	void testRepoSave() {
		departmentRepo.save(new Department("Finance"));
		
		assertNotNull(departmentRepo.findByTitle("Finance"));
	}

	@Test
	void testRepoUpdate() {
		departmentRepo.save(new Department("Planning"));
		
		Department updated = departmentRepo.findByTitle("Planning");
		
		updated.setTitle("Sales");
		departmentRepo.save(updated);
		
		assertNotNull(departmentRepo.findByTitle("Sales"));
		assertNull(departmentRepo.findByTitle("Planning"));
	}
}
