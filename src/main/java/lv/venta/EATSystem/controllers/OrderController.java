package lv.venta.EATSystem.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lv.venta.EATSystem.models.Order;
import lv.venta.EATSystem.models.dto.OrderDTO;
import lv.venta.EATSystem.services.IOrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	IOrderService orderService;
	
	@GetMapping("/all")
	public Collection<Order> getAllOrders() {
		return orderService.selectAllOrders();
	}
	
	@GetMapping("/all/{id}")
	public Order getOrderById(@PathVariable(name = "id") int id) throws Exception {
		try {
			return orderService.selectOrderById(id);
		} catch (Exception e) {
			throw new Exception("can't find");
		}
	}
	
	@DeleteMapping("/remove/{id}")
	public void deleteOrderById(@PathVariable(name = "id") int id) {
		orderService.deleteOrderById(id);
	}
	
	@PostMapping("/create")
	public Order postAddOrder(@Valid @RequestBody OrderDTO order, BindingResult result) throws Exception {
		if(!result.hasErrors()) {
			return orderService.insertNewOrder(order.getOrderNumber(), order.getProject(), order.getOrderDate(), order.getDateTimeStart(),
					order.getDateTimeEnd(), order.getOrderStatus(), order.getEmployeeOrderStatus());
		} else {
			throw new Exception("can't create");
		}
	}
	
	@PutMapping("/update/{id}")
	public Order updateOrderById(@PathVariable(name = "id") int id, @Valid @RequestBody OrderDTO order, BindingResult result) throws Exception {
		if(!result.hasErrors()) {
			return orderService.updateOrderById(id, order.getOrderNumber(), order.getProject(), order.getOrderDate(), order.getDateTimeStart(),
					order.getDateTimeEnd(), order.getOrderStatus(), order.getEmployeeOrderStatus());
		} else {
			throw new Exception("can't update");
		}
	}

}
