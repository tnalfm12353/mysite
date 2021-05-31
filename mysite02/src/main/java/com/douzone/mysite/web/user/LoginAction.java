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

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo vo = new UserRepository().findByEmailAndPassword(email, password);
		if(vo == null) {
			request.setAttribute("result", "fail");
			request.setAttribute("email", email);
			MvcUtils.forward("user/loginform", request, response);
			return ;
		}
		// 인증처리(session 처리)
		// 매개변수 true면 create, false면 세션이 존재 하면 만들지 않고, 존재하지 않으면 만듦.
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", vo);
		// 메인으로
		MvcUtils.redirect(request.getContextPath(), request, response);
	}

}
