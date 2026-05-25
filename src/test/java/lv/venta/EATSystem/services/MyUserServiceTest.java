package lv.venta.EATSystem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lv.venta.EATSystem.enums.SecurityRole;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.MyAuthority;
import lv.venta.EATSystem.models.MyUser;
import lv.venta.EATSystem.repos.IMyUserRepo;
import lv.venta.EATSystem.services.impl.MyUserServiceImpl;

@SpringBootTest(properties = "spring.profiles.active=test")
class MyUserServiceTest {

	@InjectMocks
	private static MyUserServiceImpl userService;
	
	@Mock
	private static IMyUserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.openMocks(this);
	}
	@Test
	void testFindById() {
		MyAuthority roleEmployee = new MyAuthority(SecurityRole.EMPLOYEE);
		Employee employee = new Employee();
		MyUser user = new MyUser("newEmployee", passwordEncoder.encode("newPass123"), roleEmployee, employee);
		
		when(userRepo.existsByIdMyUser(user.getIdMyUser())).thenReturn(true);
		when(userRepo.findByIdMyUser(user.getIdMyUser())).thenReturn(user);

		
		MyUser userFromService = userService.findUserById(user.getIdMyUser());
		
		assertEquals("newEmployee", userFromService.getUsername());
	}

}
