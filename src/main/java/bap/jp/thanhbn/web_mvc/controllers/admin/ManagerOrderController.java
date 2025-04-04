package bap.jp.thanhbn.web_mvc.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bap.jp.thanhbn.web_mvc.service.Order.OrderServiceImpl;

@Controller
@RequestMapping("/admin/order")
public class ManagerOrderController {

	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@GetMapping
	public String index(Model model) {
		
		model.addAttribute("orders", orderServiceImpl.getAllOrders());
		return "/admin/ordermanager";
	}
	
}
