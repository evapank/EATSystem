package lv.venta.EATSystem.repos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lv.venta.EATSystem.models.Department;

class DepartmentRepoTest {
	
	@Autowired
	IDepartmentRepo departmentRepo;

	@Test
	void testRepoSave() {
		departmentRepo.save(new Department("Finance"));
		
		assertNotNull(departmentRepo.findByTitle("Finance"));
	}

	@Test
	void testRepoUpdate() {
		departmentRepo.save(new Department("Finance"));
		
		Department updated = departmentRepo.findByTitle("Finance");
		
		updated.setTitle("Sales");
		
		assertNotNull(departmentRepo.findByTitle("Sales"));
		assertNull(departmentRepo.findByTitle("Finance"));
	}
}
