package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestbookVo;

@RequestMapping("/guestbook")
@Controller
public class GuestbookController {

	@Autowired
	private GuestBookService guestBookService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GuestbookVo> list = guestBookService.getMessageList();
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	
	@PostMapping("/add")
	public String add(GuestbookVo vo) {
		guestBookService.addMessage(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id")Long id,Model model) {
		model.addAttribute("id", id);
		return "guestbook/deleteform";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id")Long id, GuestbookVo vo) {
		vo.setId(id);
		guestBookService.deleteMessage(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping("/spa")
	public String spaLanding() {
		return "guestbook/spa-landing";
	}
}
