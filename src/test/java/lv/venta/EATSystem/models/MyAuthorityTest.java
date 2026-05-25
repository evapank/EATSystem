package lv.venta.EATSystem.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.enums.SecurityRole;

@SpringBootTest(properties = "spring.profiles.active=test")
class MyAuthorityTest {

	private MyAuthority authority = new MyAuthority(SecurityRole.EMPLOYEE);
	
	@Test
	void test() {
		assertEquals("EMPLOYEE", authority.getTitle().toString());
	}

}
