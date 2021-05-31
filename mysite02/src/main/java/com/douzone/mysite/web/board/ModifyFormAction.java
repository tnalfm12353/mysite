package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardReqository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;
import com.douzone.web.util.SessionUtils;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserVo userVo = SessionUtils.getUserBySession(request, response); 
		if(userVo == null) {
			MvcUtils.redirect(request.getContextPath()+"/board", request, response);
			return ;
		}
		
		Long id = Long.valueOf(request.getParameter("id"));
		BoardVo vo = new BoardReqository().findById(id);
		request.setAttribute("board", vo);
		MvcUtils.forward("board/modify", request, response);

	}

}
