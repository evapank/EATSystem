package lv.venta.EATSystem.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.enums.OrderStatus;
import lv.venta.EATSystem.models.EmployeeOrderStatus;
import lv.venta.EATSystem.models.Meeting;
import lv.venta.EATSystem.models.Order;
import lv.venta.EATSystem.models.Project;
import lv.venta.EATSystem.services.IOrderService;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
class OrderControllerTest {

	@Mock
	private static IOrderService orderService;
	
	@InjectMocks
	private static OrderController orderController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testGetAll() {
		Project  project = new Project();
		EmployeeOrderStatus eos = new EmployeeOrderStatus();
		
		Order order1 = new Order(20003, project, LocalDate.of(2026, Month.AUGUST, 28),
				LocalDateTime.of(2026, Month.DECEMBER, 1, 12, 00),LocalDateTime.of(2026, Month.DECEMBER, 7, 18, 30),
				OrderStatus.BusinessTrip, eos);
		Order order2 = new Order(20004, project, LocalDate.of(2026, Month.AUGUST, 30),
				LocalDateTime.of(2026, Month.NOVEMBER, 20, 8, 00),LocalDateTime.of(2026, Month.NOVEMBER, 23, 19, 00),
				OrderStatus.Vacation, eos);
		
		ArrayList<Order> allOrders = new ArrayList<>(Arrays.asList(order1, order2));
		
		try {
			when(orderService.selectAllOrders()).thenReturn(allOrders);
			
			mockMvc.perform(get("/order/all"))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
			.andExpect((ResultMatcher) jsonPath("$[0].orderStatus", OrderStatus.BusinessTrip))
			.andExpect((ResultMatcher) jsonPath("$[1].orderNumber",20004));
		}  catch (Exception e){
			
		}
	}

}
