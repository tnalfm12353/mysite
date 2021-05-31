package com.douzone.mysite.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class UpdateFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// 접근제어
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(session == null || authUser == null) {
			MvcUtils.redirect(request.getContextPath(), request, response);
			return;
		}
		Long userId = authUser.getId();
		UserVo userVo = new UserRepository().findById(userId);
		request.setAttribute("profile", userVo);
		MvcUtils.forward("user/updateform", request, response);
	}
}
