package com.douzone.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.Action;
import com.douzone.mvc.util.MvcUtils;
import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;

public class JoinAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserVo vo = new UserVo();
		vo.setName(request.getParameter("name"));
		vo.setEmail(request.getParameter("email"));
		vo.setPassword(request.getParameter("password"));
		vo.setGender(request.getParameter("gender"));
		
		Boolean result = new UserRepository().insert(vo);
		if(result) {
			MvcUtils.redirect(request.getContextPath() + "/user?a=joinsuccess", request, response);
		}
	}

}
