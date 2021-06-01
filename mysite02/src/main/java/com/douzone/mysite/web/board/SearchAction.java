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

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		BoardReqository repo = new BoardReqository();
		String kwd = request.getParameter("kwd");
		int totalPage = repo.serachTotalPage(kwd);
		
		int currentPage= 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		int firstPageNo = currentPage > 3 ? firstPageNo = currentPage - 2 : 1;
		int lastPageNo = currentPage+2 >= totalPage? totalPage : currentPage + 2;
		request.setAttribute("firstPage", firstPageNo);
		request.setAttribute("lastPage", lastPageNo);
		request.setAttribute("currentPage", currentPage);
		int nextPageNo = currentPage+1;
		int prevPageNo = currentPage-1;
		List<BoardVo> list = repo.searchFindAll(kwd,currentPage);
		request.setAttribute("list", list);
		MvcUtils.forward("board/list", request, response);
	}

}
