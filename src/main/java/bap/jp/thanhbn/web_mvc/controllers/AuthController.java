package bap.jp.thanhbn.web_mvc.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import bap.jp.thanhbn.web_mvc.dto.LoginRequest;
import bap.jp.thanhbn.web_mvc.dto.Register;
import bap.jp.thanhbn.web_mvc.enums.Role;
import bap.jp.thanhbn.web_mvc.model.User;
import bap.jp.thanhbn.web_mvc.service.user.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/login") 
	public String login(){
		
		return "login";
	}
	
	@GetMapping("/register") 
	public String register(){
		
		return "register";
	}
	
	@PostMapping("/register")
	public ModelAndView register(@ModelAttribute Register register) {
		
		Optional<User> userOpt = userService.findByEmail(register.getEmail());	
		
		ModelAndView mv = new ModelAndView("register");

		if(userOpt.isPresent()) {
			mv.addObject("error", "Email da ton tai");
			return mv;
		}
		
		if(!register.getPassword().equals(register.getRePassword())) {
			mv.addObject("error", "tai khoan hoac mat khau k dung");
			return mv;
		}
		
		User u = new User();
		u.setEmail(register.getEmail());
		u.setPassword(passwordEncoder.encode(register.getPassword()));
		u.setUserName(register.getUsername());
		u.setRole(Role.USER);
		userService.createUser(u);
		
		return new ModelAndView("redirect:/login");

	}
		
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/login";
	}
}
