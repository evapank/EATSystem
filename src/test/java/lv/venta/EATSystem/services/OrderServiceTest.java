package lv.venta.EATSystem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.enums.OrderStatus;
import lv.venta.EATSystem.models.EmployeeOrderStatus;
import lv.venta.EATSystem.models.Order;
import lv.venta.EATSystem.models.Project;
import lv.venta.EATSystem.repos.IOrderRepo;
import lv.venta.EATSystem.services.impl.OrderServiceImpl;

@SpringBootTest(properties = "spring.profiles.active=test")
class OrderServiceTest {

	@InjectMocks
	private static OrderServiceImpl orderService;
	
	@Mock
	private static IOrderRepo orderRepo;
	
	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testCreate() {
		Project project = new Project();
		EmployeeOrderStatus eos = new EmployeeOrderStatus();
		Order order = new Order(12345, project, LocalDate.of(2025, Month.DECEMBER, 2),
				LocalDateTime.of(2026, Month.APRIL, 3, 8, 00), LocalDateTime.of(2026, Month.APRIL, 6, 10, 00), OrderStatus.BusinessTrip,
				eos);
		
		when(orderRepo.save(any())).thenReturn(order);
		
		Order orderFromService = orderService.insertNewOrder(order.getOrderNumber(), order.getProject(), order.getOrderDate(), order.getDateTimeStart(),
				order.getDateTimeEnd(), order.getOrderStatus(), order.getEmployeeOrderStatus());
		
		assertEquals(12345, orderFromService.getOrderNumber());
	}

}
