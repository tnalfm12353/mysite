package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.vo.SiteVo;

@Auth(role = "ADMIN")
@RequestMapping("/admin")
@Controller
public class AdminController {
	
	// 추후에 랜덤한 id를 받아서 사용자가 들어왔을때 
	// title, welcome, description이 다르게 보이게 할 수 있을꺼 같아서 id(식별자)를 살려둠.
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("")
	public String main(Model model) {
		SiteVo siteVo = adminService.getSite();
		model.addAttribute(siteVo);
		return "admin/main";
	}
	
	@PostMapping("/main/update")
	public String updateMain(SiteVo siteVo) {
		adminService.updateSite(siteVo);
		return "redirect:/admin";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
	
}
