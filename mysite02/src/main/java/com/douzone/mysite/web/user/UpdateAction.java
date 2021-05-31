package com.douzone.mysite.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;
import com.douzone.web.util.SessionUtils;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserVo vo = SessionUtils.getUserBySession(request, response);
		if(vo == null) {
			MvcUtils.redirect(request.getContextPath(), request, response);
			return ;
		}
		vo.setName(request.getParameter("name"));
		vo.setPassword(request.getParameter("password"));
		vo.setGender(request.getParameter("gender"));
		
		if("".equals(vo.getName())) { MvcUtils.forward("user/updateform", request, response); return ;}
		
		UserRepository userRepo = new UserRepository();
		
		if(!"".equals(vo.getPassword())) {
			userRepo.updatePassword(vo);
		}
		userRepo.update(vo);
				
		MvcUtils.redirect(request.getContextPath(), request, response);
	}

}
