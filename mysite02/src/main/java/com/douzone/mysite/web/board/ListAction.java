package com.douzone.mysite.web.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardReqository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		int totalPage = 0;
//		int firstPageNo = 0;
//		int lastPageNo = 0;
//		int nextPageNo = 0;
//		int prevPageNo = 0;
//		int currentPage= 4;
		BoardReqository repo = new BoardReqository();
		List<BoardVo> list = repo.findAll();
		request.setAttribute("list", list);
		MvcUtils.forward("board/list", request, response);
	}

}
