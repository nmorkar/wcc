package com.cric.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cric.model.PlayerSelection;
import com.cric.model.User;
import com.cric.service.UserService;
import com.cric.util.CricUtil;
import com.cric.util.ValidateUtil;

@Controller
public class AppController {
	
	@Autowired
	UserService userService;

	@RequestMapping("/login.htm")
	public String login() {
		return "login";
	}
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute("isAdmin");
		session.removeAttribute("uname");
		session.invalidate();
		return "redirect:login.htm";
	}
	
	@RequestMapping(value = "/validate.htm", method = RequestMethod.GET)
	public String validate1(){
		return "redirect:login.htm";
	}

	@RequestMapping(value = "/validate.htm", method = RequestMethod.POST)
	public String validate(
			@RequestParam(value = "username", required = true) String name,
			@RequestParam(value = "password", required = true) String password,
			Model model,
			HttpSession session) {
		System.out.println("name " + name);
		
		System.out.println(userService.getUser(name));
		
		if (!ValidateUtil.isValid(name, password)) {
			session.setAttribute("message", "Username or Password is invalid.");
			return "redirect:login.htm";
		}
		
		if( ValidateUtil.isAdmin(name) ){
			session.setAttribute("isAdmin", true);
		}
		
		session.setAttribute("uname", name);
		
		return "redirect:home.htm";
	}
	
	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	public String home(){

		return "home";
	}


	@RequestMapping(value = "/init.htm", headers = "Accept=application/json")
	public @ResponseBody String init() {
		return CricUtil.getModelJSON();
	}

	@RequestMapping(value = "/save.htm", method = RequestMethod.POST)
	public String save(){
		userService.saveUserMatchSelection(CricUtil.getModel());
		return "redirect:home.htm";
	}
	
	@RequestMapping(value = "/email.htm", method = RequestMethod.POST)
	public String email(){
		userService.saveUserMatchSelection(CricUtil.getModel());
		
		return "redirect:home.htm";
	}
	
	@RequestMapping(value = "/mlist.htm", method = RequestMethod.GET)
	public String mlist(Model model){
		
		Map<String,List<PlayerSelection>> m = userService.getMatchPlayerList();
		
		model.addAttribute("mlist", m);
		
		return "mlist";
	}

	@RequestMapping(value = "/checkMatch.htm")
	public @ResponseBody String checkMatch(@RequestParam(value = "matchname", required = true) String matchname) {
		if( userService.matchNameExists(matchname) )
			return "failed";
		return "success";
	}
	
	@RequestMapping(value = "/archive.htm")
	public @ResponseBody String archive(@RequestParam(value = "matchname", required = true) String matchname,HttpSession session) {
		if(isAdmin(session)){
			userService.archiveMatchSelection(matchname);
			return "success";
		}else{
			return "failed";
		}
	}
	
	@RequestMapping(value = "/delete.htm")
	public @ResponseBody String delete(@RequestParam(value = "matchname", required = true) String matchname,HttpSession session) {
		if(isAdmin(session)){
			userService.deleteMatchSelection(matchname);
			return "success";
		}else{
			return "failed";
		}
	}
	
	
	private boolean isAdmin(HttpSession session){
		if(session.getAttribute("uname") != null && ((String)session.getAttribute("uname")).equals(User.NIMESH.getName())){
			return true;
		}
		return false;
	}
	
}
