package bap.jp.thanhbn.web_mvc.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bap.jp.thanhbn.web_mvc.model.Order;
import bap.jp.thanhbn.web_mvc.model.Product;
import bap.jp.thanhbn.web_mvc.model.User;
import bap.jp.thanhbn.web_mvc.service.cart.CartService;
import bap.jp.thanhbn.web_mvc.service.product.ProductService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	 @Autowired
	 private CartService cartService;

	 @Autowired
	 private ProductService productService;

	 @GetMapping("/add")
	 public String addToCart(@RequestParam("productid") int productId) {
	        Optional<Product> productOtp = productService.findById(productId);
	        if(productOtp.isPresent()) {
	        	Product product = productOtp.get();
	        	if (product != null) {
	 	            cartService.addToCart(product, 1);
	 	        }
	        }
	        
	        return "redirect:/cart";
	 }

	@GetMapping
	 public String index(Model model, HttpSession session) {
			User logginUser = (User) session.getAttribute("loggedInUser");
			if(logginUser == null) {
				return "redirect:/login";
			}
		    model.addAttribute("user", logginUser);
		    model.addAttribute("order", new Order());
	        model.addAttribute("carts", cartService.getCartItems());
	        model.addAttribute("total", cartService.getTotalAmount());
	        return "cart";
	 }

	 @GetMapping("/remove")
	 public String removeFromCart(@RequestParam("productid") int productId) {
	        cartService.removeProductToCart(productId);
	        return "redirect:/cart";
	 }

	 @GetMapping("/clear")
	 public String clearCart() {
	        cartService.clearCart();
	        return "redirect:/cart";
	 }
}
