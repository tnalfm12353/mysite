package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/join")
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public String join(UserVo vo) {
		userService.joinUser(vo);
		
		return "user/joinsuccess";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session,
						@RequestParam(value="email", required = true , defaultValue = "")String email,
						@RequestParam(value="password", required = true , defaultValue = "")String password,
						Model model) {
		UserVo authUser = userService.getUser(email,password);
		if(authUser == null) {
			model.addAttribute("result","fail");
			model.addAttribute("email",email);
			return "user/login";
		}
		// 로그인 처리 (세션)
		session.setAttribute("authUser", authUser);
		
		return "redirect:/";
	}
	
	@Auth
	@RequestMapping("/update")
	public String update(HttpSession session, Model model) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		Long id = authUser.getId();
		userService.getUser(id);

		return "user/update";
	}
	
	@PostMapping("/update")
	public String update() {
		return null;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser != null) {
			// 로그아웃 처리
			session.removeAttribute("authUser");
			session.invalidate();
		}
		
		return "redirect:/";
	}
}
