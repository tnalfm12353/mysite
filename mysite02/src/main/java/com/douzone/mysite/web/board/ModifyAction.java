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

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserVo userVo = SessionUtils.getUserBySession(request, response); 
		if(userVo != null) {
			BoardVo vo = new BoardVo();
			vo.setId(Long.valueOf(request.getParameter("id")));
			vo.setTitle(request.getParameter("title"));
			vo.setContent(request.getParameter("content"));
			if("".equals(vo.getTitle()) || "".equals(vo.getContent())) {
				String status = "Please Fill Out the Form above";
				String id = request.getParameter("id");
				MvcUtils.redirect(request.getContextPath()+"/board?a=modifyform&id="+id+"&status="+status, request, response);
				return ;
			}
			new BoardReqository().updateBoard(vo,userVo);
		}
		
		MvcUtils.redirect(request.getContextPath()+"/board", request, response);
	}

}
