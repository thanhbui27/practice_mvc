package bap.jp.thanhbn.web_mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bap.jp.thanhbn.web_mvc.model.User;
import bap.jp.thanhbn.web_mvc.repository.UserRepository;
import bap.jp.thanhbn.web_mvc.service.user.UserService;
import bap.jp.thanhbn.web_mvc.service.user.UserServiceImpl;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService; 
	
	@GetMapping("/users")
	public String users(Model model) {
		
		List<User> u = userService.getAllUsers();
		
		model.addAttribute("users", u);
		return "list-user";
	}
}
