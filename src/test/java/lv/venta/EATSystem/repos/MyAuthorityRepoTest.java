package lv.venta.EATSystem.repos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.enums.SecurityRole;
import lv.venta.EATSystem.models.MyAuthority;

@SpringBootTest(properties = "spring.profiles.active=test")
class MyAuthorityRepoTest {

	@Autowired
	IMyAuthorityRepo authorityRepo;
	
	@Test
	void testRepoSave() {
		MyAuthority employee = new MyAuthority(SecurityRole.EMPLOYEE);
		authorityRepo.save(employee);
		
		assertNotNull(authorityRepo.findById(employee.getAuthorityId()));
	}

}
