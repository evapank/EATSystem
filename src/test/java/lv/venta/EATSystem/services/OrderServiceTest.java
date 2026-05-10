package lv.venta.EATSystem.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.models.Order;
import lv.venta.EATSystem.repos.IOrderRepo;

@SpringBootTest(properties = "spring.profiles.active=test")
class OrderServiceTest {

	@Autowired
	IOrderService orderService;
	
	@Autowired
	IOrderRepo orderRepo;
	
	@Test
	void testSelectAll() {
		Order order = new Order();
		orderRepo.save(order);
		
		assertFalse(orderService.selectAllOrders().isEmpty());
	}

}
