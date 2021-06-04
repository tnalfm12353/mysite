package com.douzone.mysite.web.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		BoardReqository repo = new BoardReqository();
		int totalPage = repo.totalPage();
//		int totalPage = 7;
		Map<String, Integer> map = new HashMap<>();
		
		int currentPage= 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		int firstPageNo = currentPage > 3 ? totalPage - currentPage <= 2 ? totalPage - 4 : currentPage - 2 : 1;
		int lastPageNo = currentPage + 2 >= totalPage ? totalPage : currentPage > 3 ? currentPage + 2 : 5;
		
		map.put("firstPage", firstPageNo);
		map.put("currentPage", currentPage);
		map.put("lastPage", lastPageNo);
		
		request.setAttribute("pages", map);
		int nextPageNo = currentPage+1;
		int prevPageNo = currentPage-1;
		List<BoardVo> list = repo.findAll(currentPage);
//		List<BoardVo> list = null;
		request.setAttribute("list", list);
		MvcUtils.forward("board/list", request, response);
	}

}
