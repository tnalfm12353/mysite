package com.douzone.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		if(result.hasErrors()) {
//			List<ObjectError> errors = result.getAllErrors();
//			for(ObjectError error : errors) {
//				System.out.println(error);
//			}
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		// userService.joinUser(vo);
		return "user/joinsuccess";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@Auth
	@RequestMapping("/update")
	public String update(@AuthUser UserVo authUser, Model model) {
		Long id = authUser.getId();
		UserVo user = userService.getUser(id);

		model.addAttribute("profile",user);
		return "user/update";
	}
	@Auth
	@PostMapping("/update")
	public String update(@AuthUser UserVo authUser, UserVo userVo) {
		userVo.setId(authUser.getId());
		userService.updateUser(userVo);
		authUser.setName(userVo.getName());
		
		return "redirect:/";
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
	
	@RequestMapping(value="/auth", method = RequestMethod.POST)
	public void auth() {}
	@RequestMapping(value="/logout", method = RequestMethod.POST)
	public void logout() {}
}
