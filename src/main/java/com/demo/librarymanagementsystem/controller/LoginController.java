package com.demo.librarymanagementsystem.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.librarymanagementsystem.entity.Login;
import com.demo.librarymanagementsystem.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	LoginService loginService;

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("user", new Login());
		return mav;
	}

	@RequestMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new Login());
		return "register";
	}
	@RequestMapping(value="/process_register",method=RequestMethod.POST)
	public String processRegister(@ModelAttribute Login login) {
		loginService.save(login);
		return "register_success";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") Login user) {

		Login oauthUser = loginService.login(user.getUsername(), user.getPassword());

		System.out.print(oauthUser);
		if (Objects.nonNull(oauthUser)) {

			return "redirect:/home";

		} else {
			return "redirect:/login";

		}
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.POST)
	public String logoutDo(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/login";
	}

}
