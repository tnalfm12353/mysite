package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.vo.UserVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;
import com.douzone.web.util.SessionUtils;

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserVo vo = SessionUtils.getUserBySession(request, response); 
		if(vo == null) {
			MvcUtils.redirect(request.getContextPath()+"/board", request, response);
			return ;
		}
		
		MvcUtils.forward("board/write", request, response);
	}

}
