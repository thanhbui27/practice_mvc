package bap.jp.thanhbn.web_mvc.controllers.admin;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bap.jp.thanhbn.web_mvc.service.Order.OrderServiceImpl;
import bap.jp.thanhbn.web_mvc.service.product.ProductServiceImpl;
import bap.jp.thanhbn.web_mvc.service.user.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class ManagerDashboardController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@GetMapping("/dashboard")
	public String index(Model model) {
		
		int totalProduct = productServiceImpl.getAllProduct().size();
		int totalUsers = userServiceImpl.getAllUsers().size();
		int totalOrder = orderServiceImpl.getAllOrders().size();
		BigDecimal totalMoney = orderServiceImpl.getAllOrders()
				.stream().map(o -> o.getTotalAmount()).reduce(BigDecimal.ZERO,BigDecimal::add);
		
		model.addAttribute("totalProducts", totalProduct);
		model.addAttribute("totalOrders", totalOrder);
		model.addAttribute("totalUsers", totalUsers);
		model.addAttribute("totalMoney", totalMoney);
		
		return "/admin/dashboard";
	}
}
