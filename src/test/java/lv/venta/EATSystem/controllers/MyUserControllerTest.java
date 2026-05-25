package lv.venta.EATSystem.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import lv.venta.EATSystem.enums.SecurityRole;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.MyAuthority;
import lv.venta.EATSystem.models.MyUser;
import lv.venta.EATSystem.services.IMyUserService;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
class MyUserControllerTest {

	@Mock
	private static IMyUserService userService;
	
	@InjectMocks
	private static MyUserController userController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void testSignIn() {
		MyAuthority employeeRole = new MyAuthority(SecurityRole.EMPLOYEE);
		Employee employee = new Employee();
		MyUser user = new MyUser("johnjohnson", passwordEncoder.encode("password123"), employeeRole, employee);
		
		try {
			when(userService.findUserById(user.getIdMyUser())).thenReturn(user);
			
			//mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
			//		.content(new ObjectMapper().writeValueAsString(user))
			//		.contentType(MediaType.APPLICATION_JSON)
			//		.accept(MediaType.APPLICATION_JSON))
			//		.andExpect(status().isOk())
			//		.andExpect((ResultMatcher) jsonPath("$.username", "johnjohnson"));
		} catch (Exception e){
			
		}
	}

}
