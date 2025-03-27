package bap.jp.thanhbn.web_mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String listOrders(Model model, HttpSession session) {
    	User u = (User) session.getAttribute("loggedInUser");
    	if(u == null) {
			return "redirect:/login";
		}
        List<Order> orders = orderService.getAllOrdersByUser(u.getUserID());
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
