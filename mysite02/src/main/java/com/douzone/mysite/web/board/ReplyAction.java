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

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserVo userVo = SessionUtils.getUserBySession(request, response); 
		if(userVo != null) {
			int boardGroupId = Integer.parseInt(request.getParameter("group"));
			int boardOrderId = Integer.parseInt(request.getParameter("order"));
			int boardDepth = Integer.parseInt(request.getParameter("depth"));
			System.out.println("Reply Action");
			System.out.println(boardGroupId);
			System.out.println(boardOrderId);
			System.out.println(boardDepth);
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			if("".equals(title) || "".equals(content)) {
				//TODO forward로 데이터를 다시 주고싶다면..?
				MvcUtils.redirect(request.getContextPath()+"/board", request, response);
				return ;
			}
			BoardReqository repo = new BoardReqository();
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setRegDate(LocalDateTime.now().toString());
			vo.setGroupId(boardGroupId);
			vo.setOrderId(boardOrderId+1);
			vo.setDepth(boardDepth+1);
			vo.setUserId(userVo.getId());
			
			repo.updateOrder(vo);
			repo.insert(vo);
			
		}

		MvcUtils.redirect(request.getContextPath()+"/board", request, response);
	}

}
