package lv.venta.EATSystem.repos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lv.venta.EATSystem.enums.SecurityRole;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.MyAuthority;
import lv.venta.EATSystem.models.MyUser;

@SpringBootTest(properties = "spring.profiles.active=test")
class MyUserRepoTest {
	
	@Autowired
	IMyUserRepo userRepo;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	IMyAuthorityRepo authorityRepo;
	
	@Autowired
	IEmployeeRepo employeeRepo;

	@Test
	void testRepoSave() {
		MyAuthority projectManager = new MyAuthority(SecurityRole.PROJECT_MANAGER);
		authorityRepo.save(projectManager);
		Employee employee = new Employee();
		employeeRepo.save(employee);
		userRepo.save(new MyUser("user", encoder.encode("password"), projectManager, employee));
		
		assertNotNull(userRepo.findByUsername("user"));
	}
	
	@Test
	void testRepoUpdate() {
		MyAuthority projectManager = new MyAuthority(SecurityRole.PROJECT_MANAGER);
		authorityRepo.save(projectManager);
		Employee employee = new Employee();
		employeeRepo.save(employee);
		userRepo.save(new MyUser("user1", encoder.encode("password"), projectManager, employee));
		
		MyUser updated = userRepo.findByUsername("user1");
		updated.setUsername("none");
		userRepo.save(updated);
		employeeRepo.save(employee);
		authorityRepo.save(projectManager);
		
		assertNotNull(userRepo.findByUsername("none"));
		assertNull(userRepo.findByUsername("user1"));
	}


}
