package lv.venta.EATSystem.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.models.MyUser;
import lv.venta.EATSystem.repos.IMyUserRepo;

@SpringBootTest(properties = "spring.profiles.active=test")
class MyUserServiceTest {

	@Autowired
	IMyUserService userService;
	
	@Autowired
	IMyUserRepo userRepo;
	
	@Test
	void test() {
		MyUser user = new MyUser();
		userRepo.save(user);
		
		assertFalse(userService.getAllUsers().isEmpty());
	}

}
