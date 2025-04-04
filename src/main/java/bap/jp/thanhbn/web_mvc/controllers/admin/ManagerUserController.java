package bap.jp.thanhbn.web_mvc.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bap.jp.thanhbn.web_mvc.model.User;
import bap.jp.thanhbn.web_mvc.service.user.UserServiceImpl;

@Controller
@RequestMapping("/admin/user")
public class ManagerUserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@GetMapping
	public String userPage(Model model) {
			
		model.addAttribute("users", userServiceImpl.getAllUsers());
		return "/admin/usermanager";
	}
	
	@PostMapping("/add-user")
	public ModelAndView addUser(@ModelAttribute User u) {
		userServiceImpl.createUser(u);
		return new ModelAndView("redirect:/admin/user");
	}
	
	@PostMapping("/update-user")
	public ModelAndView updateUser(@ModelAttribute User u) {
		userServiceImpl.updateUser(u);
		return new ModelAndView("redirect:/admin/user");
	}
	
	@GetMapping("/delete-user")
	public ModelAndView deleteUser(@Param("id") String id) {
		
		User u = userServiceImpl.findById(Integer.parseInt(id)).get();
		
		if(u != null) {
			
			userServiceImpl.removeUser(u);

		}
		
		return new ModelAndView("redirect:/admin/user");
	} 
	
}
