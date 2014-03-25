package com.cric.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cric.util.CricUtil;
import com.cric.util.ValidateUtil;

@Controller
public class AppController {

	@RequestMapping("/login.htm")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/validate.htm", method = RequestMethod.POST)
	public String validate(
			@RequestParam(value = "username", required = true) String name,
			@RequestParam(value = "password", required = true) String password,
			Model model) {
		System.out.println("name " + name);
		
		if (!ValidateUtil.isValid(name, password)) {
			model.addAttribute("message", "Username or Password is invalid.");
			return "login";
		}
		
		if( ValidateUtil.isAdmin(name) ){
			model.addAttribute("isAdmin", true);
		}
		
		model.addAttribute("uname", name);
		return "home";
	}


	@RequestMapping(value = "/init.htm", headers = "Accept=application/json")
	public @ResponseBody String init() {
		return CricUtil.getModelJSON();
	}

}
