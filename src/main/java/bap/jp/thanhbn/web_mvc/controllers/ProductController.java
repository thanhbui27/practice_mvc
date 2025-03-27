package bap.jp.thanhbn.web_mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import bap.jp.thanhbn.web_mvc.model.Product;
import bap.jp.thanhbn.web_mvc.model.User;
import bap.jp.thanhbn.web_mvc.service.product.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public String index(Model model) {
		
		List<Product> products = productService.getAllProduct();
		model.addAttribute("products", products);
		return "product";
	}
	@ModelAttribute
	public void addAttributes(Model model, @SessionAttribute(name = "user", required = false) User user) {
	        model.addAttribute("user", user);
	}
}
