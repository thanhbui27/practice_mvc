package bap.jp.thanhbn.web_mvc.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bap.jp.thanhbn.web_mvc.model.Product;
import bap.jp.thanhbn.web_mvc.model.User;
import bap.jp.thanhbn.web_mvc.repository.CategoryRepository;
import bap.jp.thanhbn.web_mvc.service.product.ProductServiceImpl;

@Controller
@RequestMapping("/admin/product")
public class ManagerProductController {
	
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping
	public String index(Model model) {
		
		model.addAttribute("products", productServiceImpl.getAllProduct());
		model.addAttribute("categories", categoryRepository.findAll());
		return"/admin/productmanager";
	}
	
	@PostMapping("/add-product")
	public ModelAndView addProduct(@ModelAttribute Product p) {
		productServiceImpl.createProduct(p);
		return new ModelAndView("redirect:/admin/product");
	}
	
	@PostMapping("/update-product")
	public ModelAndView updateProduct(@ModelAttribute Product p) {
		productServiceImpl.updateProduct(p);
		return new ModelAndView("redirect:/admin/product");
	}
	
	@GetMapping("/delete-product")
	public ModelAndView deleteProduct(@Param("id") String id) {
		
		Product p = productServiceImpl.findById(Integer.parseInt(id)).get();
		
		if(p != null) {
			
			productServiceImpl.removeProduct(p);

		}
		
		return new ModelAndView("redirect:/admin/product");
	} 

}
