package lv.venta.EATSystem.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BindingResult;
import org.thymeleaf.spring6.expression.Mvc;

import lv.venta.EATSystem.controllers.DepartmentController;
import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.dto.DepartmentDTO;
import lv.venta.EATSystem.services.IDepartmentService;

@SpringBootTest(properties = "spring.profiles.active=test")
class DepartmentControllerTest {

	@InjectMocks
	DepartmentController departmentController;
	
	@Test
	void testGetById() throws Exception{
		
		DepartmentDTO department = new DepartmentDTO();
		department.setTitle("Title");
		BindingResult result = null;
		
		Department response = departmentController.addDepartment(department, result);
		
		assertEquals(department.getTitle(), response.getTitle());
	}

}
