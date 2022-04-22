package com.demo.librarymanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	/*
	 * @RequestMapping("/login") public String showMyLoginPage() { return "login"; }
	 */

	@GetMapping("/home")
	public String list() {
		return "index";
	}

	
}
