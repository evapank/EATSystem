package lv.venta.EATSystem.repos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.Employee;

@SpringBootTest(properties = "spring.profiles.active=test")
class EmployeeRepoTest {

	@Autowired
	private IEmployeeRepo employeeRepo;
	
	@Autowired
	private IDepartmentRepo departmentRepo;
	
	@Test
	void testRepoSave() {
		Department department = new Department();
		departmentRepo.save(department);
		employeeRepo.save(new Employee("Eva", "Pankevica", "Tester", department, "gmail@gmail.com", false));
		departmentRepo.save(department);
		
		assertNotNull(employeeRepo.findByDepartmentIdDepartmentAndIsManager(department.getIdDepartment(), false));
	}
	
	@Test
	void testRepoUpdate() {
		Department department = new Department();
		departmentRepo.save(department);
		employeeRepo.save(new Employee("Eva", "Pankevica", "Tester", department, "gmail@gmail.com", true));
		departmentRepo.save(department);
		
		Employee updated = employeeRepo.findByDepartmentIdDepartmentAndIsManager(department.getIdDepartment(), true);
		updated.setManager(false);
		employeeRepo.save(updated);
		
		assertNotNull(employeeRepo.findByDepartmentIdDepartmentAndIsManager(department.getIdDepartment(), false));
		assertNull(employeeRepo.findByDepartmentIdDepartmentAndIsManager(department.getIdDepartment(), true));
	}

}
