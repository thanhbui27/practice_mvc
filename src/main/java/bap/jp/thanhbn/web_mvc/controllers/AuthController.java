package bap.jp.thanhbn.web_mvc.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import bap.jp.thanhbn.web_mvc.dto.LoginRequest;
import bap.jp.thanhbn.web_mvc.model.User;
import bap.jp.thanhbn.web_mvc.service.user.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/login") 
	public String index(){
		
		return "login";
	}
	
	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute LoginRequest login, HttpSession session) {
		
		Optional<User> userOpt = userService.findByEmail(login.getEmail());	
		
		if(userOpt.isPresent()) {
			User user = userOpt.get();
			if(user.getPassword().equals(login.getPassword())) {
				session.setAttribute("loggedInUser", userOpt.get());
				return new ModelAndView("redirect:/");
			}else {
				ModelAndView mav = new ModelAndView("login");
			    mav.addObject("error", "Mật khẩu sai");
			    return mav;
			}
		}else {
			ModelAndView mav = new ModelAndView("login");
		    mav.addObject("error", "Không tìm thấy tài khoản!");
		    return mav;
		}
	
	} 
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/login";
	}
}
