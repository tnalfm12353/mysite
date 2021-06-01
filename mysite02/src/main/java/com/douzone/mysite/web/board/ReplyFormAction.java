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

public class ReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserVo userVo = SessionUtils.getUserBySession(request, response); 
		if(userVo == null) {
			MvcUtils.redirect(request.getContextPath()+"/board", request, response);
		}
		BoardVo vo = new BoardReqository().findById(Long.valueOf(request.getParameter("id")));
		System.out.println(vo.getGroupId());
		System.out.println(vo.getOrderId());
		System.out.println(vo.getDepth());
		request.setAttribute("board", vo);
		MvcUtils.forward("board/reply", request, response);
	}

}
