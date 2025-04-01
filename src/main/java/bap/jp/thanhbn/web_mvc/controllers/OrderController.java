package bap.jp.thanhbn.web_mvc.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bap.jp.thanhbn.web_mvc.model.Order;
import bap.jp.thanhbn.web_mvc.model.OrderItem;
import bap.jp.thanhbn.web_mvc.model.User;
import bap.jp.thanhbn.web_mvc.service.Order.OrderServiceImpl;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping
    public String listOrders(@RequestParam(required = false) String price, Model model, HttpSession session) {
    	User u = (User) session.getAttribute("loggedInUser");
    	if(u == null) {
			return "redirect:/login";
		}
    	
    	List<Order> orders = null;
    	
    	//Bài 3  lấy ra các order có giá > 1000
    	
    	if(price == null) {
    		orders = orderService.getAllOrdersByUser(u.getUserID());
    	}else {
    		orders = orderService.getAllOrdersByUser(u.getUserID()).stream()
    				.filter(o -> o.getTotalAmount().compareTo(new BigDecimal(price)) > 0)
    				.collect(Collectors.toList());
    	}
    	
    	int totalAmount = orderService.getTotalAmountInMonth().get();
    	
        model.addAttribute("totalAmount", "total amount: " +totalAmount);
	    model.addAttribute("user", u);
        model.addAttribute("orders", orders);
        return "order";
    }
    
   


    @PostMapping
    public String saveOrder(@ModelAttribute Order order) {
    	       
        if (order.getOrderItems() != null) {
            for (OrderItem item : order.getOrderItems()) {
                item.setOrder(order);
            }
        }
              
        orderService.saveOrder(order);

        
        return "redirect:/order";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}
