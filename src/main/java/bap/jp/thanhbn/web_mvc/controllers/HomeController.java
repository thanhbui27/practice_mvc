package bap.jp.thanhbn.web_mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import bap.jp.thanhbn.web_mvc.model.User;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	 @GetMapping("/")
	 public String home(HttpSession session,Model model) {
		 User logginUser = (User) session.getAttribute("loggedInUser");
	     model.addAttribute("user", logginUser);
	     return "index";
	 }
	 

}
