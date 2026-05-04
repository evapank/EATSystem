package lv.venta.EATSystem.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lv.venta.EATSystem.enums.SecurityRole;

class MyUserTest {
	
	MyAuthority authority = new MyAuthority(SecurityRole.EMPLOYEE);
	Employee employee = new Employee();
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	MyUser userRight = new MyUser("employee", encoder.encode("employee"), authority, employee);

	@Test
	void test() {
		assertEquals("employee", userRight.getUsername());
	}

}
