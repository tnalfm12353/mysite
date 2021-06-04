package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/join")
	public String join() {
		return "user/joinform";
	}
	
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public String join(UserVo vo, Model model) {
		userService.joinUser(vo);
		
		return "user/joinsuccess";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "user/loginform";
	}
	
	@RequestMapping("/update")
	public String update(Model model) {
		return "user/updateform";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "redirect:/";
	}
}
