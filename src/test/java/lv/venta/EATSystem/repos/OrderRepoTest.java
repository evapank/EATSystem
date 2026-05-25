package lv.venta.EATSystem.repos;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.enums.OrderStatus;
import lv.venta.EATSystem.models.EmployeeOrderStatus;
import lv.venta.EATSystem.models.Order;
import lv.venta.EATSystem.models.Project;

@SpringBootTest(properties = "spring.profiles.active=test")
class OrderRepoTest {

	@Autowired
	private IOrderRepo orderRepo;
	
	@Autowired
	private IEmployeeOrderStatusRepo eosRepo;
	
	@Autowired
	private IProjectRepo projectRepo;
	
	@Test
	void testRepoSave() {
		Project project = new Project();
		EmployeeOrderStatus eos = new EmployeeOrderStatus();
		projectRepo.save(project);
		eosRepo.save(eos);
		Order order = new Order(20002, project, LocalDate.of(2026, Month.AUGUST, 1),
				LocalDateTime.of(2026, Month.SEPTEMBER, 25, 8, 00, 00), LocalDateTime.of(2026, Month.SEPTEMBER, 30, 18, 00, 00),
				OrderStatus.BusinessTrip,eos);
		orderRepo.save(order);
		
		assertNotNull(orderRepo.findByIdOrder(order.getIdOrder()));
	}

	@Test
	void testRepoUpdate() {
		Project project = new Project();
		EmployeeOrderStatus eos = new EmployeeOrderStatus();
		projectRepo.save(project);
		eosRepo.save(eos);
		Order order = new Order(20002, project, LocalDate.of(2026, Month.AUGUST, 1),
				LocalDateTime.of(2026, Month.SEPTEMBER, 25, 8, 00, 00), LocalDateTime.of(2026, Month.SEPTEMBER, 30, 18, 00, 00),
				OrderStatus.BusinessTrip,eos);
		orderRepo.save(order);
		
		order.setDateTimeEnd(LocalDateTime.of(2026, Month.OCTOBER, 1, 18, 00, 00));
		orderRepo.save(order);
		
		assertEquals(LocalDateTime.of(2026, Month.OCTOBER, 1, 18, 00, 00), orderRepo.findByIdOrder(order.getIdOrder()).getDateTimeEnd());
		assertNotEquals(LocalDateTime.of(2026, Month.SEPTEMBER, 30, 18, 00, 00),orderRepo.findByIdOrder(order.getIdOrder()).getDateTimeEnd());
	}
	
}
