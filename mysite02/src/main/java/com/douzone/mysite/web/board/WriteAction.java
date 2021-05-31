package com.douzone.mysite.web.board;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardReqository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;
import com.douzone.web.util.SessionUtils;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserVo userVo = SessionUtils.getUserBySession(request, response); 
		if(userVo != null) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			if("".equals(title)|| "".equals(content)) {
//				MvcUtils.forward(content, request, response);
				return ;
			}
			BoardReqository repo = new BoardReqository();
			BoardVo boardVo = new BoardVo();
			boardVo.setTitle(title);
			boardVo.setContent(content);
			boardVo.setRegDate(LocalDateTime.now().toString());
			boardVo.setGroupId(repo.maxGroupId()+1);
			boardVo.setOrderId(0);
			boardVo.setDepth(0);
			boardVo.setUserId(userVo.getId());
			
			repo.insert(boardVo);
		}
		
		MvcUtils.redirect(request.getContextPath()+"/board", request, response);
	}

}
