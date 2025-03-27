package bap.jp.thanhbn.web_mvc.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

import bap.jp.thanhbn.web_mvc.model.User;

@ControllerAdvice
public class GlobalControllerAdvice {
	@ModelAttribute
    public void addAttributes(Model model, @SessionAttribute(name = "user", required = false) User user) {
        model.addAttribute("user", user);
    }

}
