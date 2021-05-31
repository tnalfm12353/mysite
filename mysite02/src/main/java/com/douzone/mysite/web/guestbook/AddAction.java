package com.douzone.mysite.web.guestbook;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		GuestbookVo vo = new GuestbookVo();
		vo.setName(request.getParameter("name"));
		vo.setPassword(request.getParameter("pass"));
		vo.setMessage(request.getParameter("content"));
		vo.setRegDate(LocalDateTime.now().toString());
		
		Boolean result = new GuestbookRepository().insert(vo);
		if(result) {
			MvcUtils.redirect(request.getContextPath() + "/guestbook", request, response);
		}
	}

}
