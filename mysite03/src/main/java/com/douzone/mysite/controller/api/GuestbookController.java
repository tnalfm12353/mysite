package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller("guestbookcontrollerApi")
@RequestMapping("/guestbook/api")
public class GuestbookController {

	@Autowired
	private GuestBookService guestBookService;
	
	@ResponseBody
	@GetMapping("/list/{id}")
	public JsonResult list(@PathVariable Long id) {
		List<GuestbookVo> list = guestBookService.getMessageList(id);
		return JsonResult.success(list);
	}
	
	@ResponseBody
	@PostMapping("/add")
	public JsonResult add(@RequestBody GuestbookVo vo) {
		guestBookService.addMessage(vo);
		return JsonResult.success(vo);
	}
	
	@ResponseBody
	@PostMapping("/delete/{id}")
	public JsonResult delete(@PathVariable Long id, String password) {
		
		Long data = 0l;
		
		data = guestBookService.deleteMessage(id,password)? id : -1l;
		return JsonResult.success(data);
	}
	
}
