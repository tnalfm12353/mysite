package com.douzone.mysite.web.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Long id = Long.valueOf(request.getParameter("id"));
		String password = request.getParameter("password");
		
		
		GuestbookVo vo = new GuestbookRepository().findById(id);
		
		if(password.equals(vo.getPassword())) {
			new GuestbookRepository().delete(vo);
		}
		MvcUtils.redirect(request.getContextPath()+"/guestbook", request, response);
	}

}
