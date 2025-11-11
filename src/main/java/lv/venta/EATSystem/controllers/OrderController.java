package lv.venta.EATSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lv.venta.EATSystem.models.Order;
import lv.venta.EATSystem.services.IOrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	IOrderService orderService;
	
	@GetMapping("/all")
	public String getAllOrders(Model model) {
		model.addAttribute("order", orderService.selectAllOrders());
		return "order/order-all-page";
	}
	
	@GetMapping("/all/{id}")
	public String getOrderById(Model model, @PathVariable(name = "id") int id) {
		try {
			model.addAttribute("order", orderService.selectOrderById(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "error-page";
		}
		return "order/order-one-page";
	}
	
	@GetMapping("/remove/{id}")
	public String deleteOrderById(Model model, @PathVariable(name = "id") int id) {
		model.addAttribute("order", orderService.deleteOrderById(id));
		return "order/order-all-page";
	}
	
	@GetMapping("/create")
	public String getAddOrder(Order order) {
		return "order/order-add-page";
	}
	
	@PostMapping("/create")
	public String postAddOrder(@Valid Order order, BindingResult result) {
		if(!result.hasErrors()) {
			orderService.insertNewOrder(order.getOrderNumber(), order.getProject(), order.getOrderDate(), order.getDateTimeStart(),
					order.getDateTimeEnd(), order.getOrderStatus(), order.getEmployeeOrderStatus());
		}
		return "order/order-add-page";
	}

}
