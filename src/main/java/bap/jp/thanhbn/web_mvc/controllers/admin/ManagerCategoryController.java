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

import bap.jp.thanhbn.web_mvc.model.Category;
import bap.jp.thanhbn.web_mvc.model.Product;
import bap.jp.thanhbn.web_mvc.model.User;
import bap.jp.thanhbn.web_mvc.repository.CategoryRepository;
import bap.jp.thanhbn.web_mvc.service.category.CategoryServiceImpl;
import bap.jp.thanhbn.web_mvc.service.product.ProductServiceImpl;

@Controller
@RequestMapping("/admin/category")
public class ManagerCategoryController {
	
	
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	
	
	@GetMapping
	public String index(Model model) {
		
		model.addAttribute("categories", categoryServiceImpl.getAllCategory());
		return"/admin/categorymanager";
	}
	
	@PostMapping("/add-category")
	public ModelAndView addCategory(@ModelAttribute Category p) {
		categoryServiceImpl.createCategory(p);
		return new ModelAndView("redirect:/admin/category");
	}
	
	@PostMapping("/update-category")
	public ModelAndView updateCategory(@ModelAttribute Category p) {
		categoryServiceImpl.updateCategory(p);
		return new ModelAndView("redirect:/admin/category");
	}
	
	@GetMapping("/delete-category")
	public ModelAndView deleteCategory(@Param("id") String id) {
		
		Category p = categoryServiceImpl.findById(Integer.parseInt(id)).get();
		
		if(p != null) {
			
			categoryServiceImpl.removeCategory(p);

		}
		
		return new ModelAndView("redirect:/admin/category");
	} 

}
