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

import com.cric.model.Performance;
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
		try{
			System.out.println("name " + name);
			
			//System.out.println(userService.getUser(name));
			
			if (!ValidateUtil.isValid(name, password)) {
				session.setAttribute("message", "Username or Password is invalid.");
				return "redirect:login.htm";
			}
			
			if( ValidateUtil.isAdmin(name) ){
				session.setAttribute("isAdmin", true);
			}
			
			session.setAttribute("uname", name);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return "redirect:home.htm";
	}
	
	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	public String home(){

		return "home";
	}


	@RequestMapping(value = "/init.htm", headers = "Accept=application/json")
	public @ResponseBody String init() {
		try{
			return CricUtil.getModelJSON();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(value = "/save.htm", method = RequestMethod.POST)
	public @ResponseBody String save(){
		try{
		userService.saveUserMatchSelection(CricUtil.getModel());
		}catch(Exception e){
			e.printStackTrace();
			return "failed";
		}
		return "success";
		//return "redirect:home.htm";
	}
	
	@RequestMapping(value = "/email.htm", method = RequestMethod.GET)
	public @ResponseBody String email(){
		//userService.saveUserMatchSelection(CricUtil.getModel());
		try{
			userService.sendEmail();
		}catch(Exception e){
			e.printStackTrace();
			return "failed";
		}
		return "success";
		//return "redirect:home.htm";
	}
	
	@RequestMapping(value = "/mlist.htm", method = RequestMethod.GET)
	public String mlist(Model model){
		try{
		Map<String,List<PlayerSelection>> m = userService.getMatchPlayerList();
		
		model.addAttribute("mlist", m);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "mlist";
	}
	
	@RequestMapping(value = "/performance.htm", method = RequestMethod.GET)
	public String performance(Model model){
		try{
		List<Performance> m = userService.getPerformance();
		
		model.addAttribute("mlist", m);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "performance";
	}
	
	@RequestMapping(value = "/savePerformance.htm", method = RequestMethod.POST)
	public @ResponseBody String savePerformance(@RequestParam(value = "matchName", required = false) String matchName,
			@RequestParam(value = "performance", required = false) String performance){
		try{
			
			userService.savePerformance(matchName, performance);
		
		}catch(Exception e){
			e.printStackTrace();
			return "failed";
		}
		return "success";
		//return "redirect:home.htm";
	}

	@RequestMapping(value = "/checkMatch.htm")
	public @ResponseBody String checkMatch(@RequestParam(value = "matchname", required = true) String matchname) {
		try{
		if( userService.matchNameExists(matchname) )
			return "failed";
		return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping(value = "/archive.htm")
	public @ResponseBody String archive(@RequestParam(value = "matchname", required = true) String matchname,HttpSession session) {
		try{
		if(isAdmin(session)){
			userService.archiveMatchSelection(matchname);
			return "success";
		}else{
			return "failed";
		}
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping(value = "/delete.htm")
	public @ResponseBody String delete(@RequestParam(value = "matchname", required = true) String matchname,HttpSession session) {
		try{
		if(isAdmin(session)){
			userService.deleteMatchSelection(matchname);
			return "success";
		}else{
			return "failed";
		}
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	
	private boolean isAdmin(HttpSession session){
		if(session.getAttribute("uname") != null && ((String)session.getAttribute("uname")).equals(User.NIMESH.getName())){
			return true;
		}
		return false;
	}
	
}
