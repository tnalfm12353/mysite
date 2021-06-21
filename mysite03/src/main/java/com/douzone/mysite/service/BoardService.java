package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public Map<String, Integer> getPages(int currentPage, String kwd) {
		int totalPage = boardRepository.totalPage(kwd);
		return calculatePages(currentPage, totalPage);
	}
	
	public List<BoardVo> getBoardList(Integer currentPage, String kwd) {
		if(currentPage == null){
			currentPage = 1;
		}
		return boardRepository.findAll(currentPage,kwd);
	}
	
	@Transactional
	public void writeBoard(UserVo authUser, BoardVo boardVo) {
		if(boardVo.getGroupId() == 0) {
			boardVo.setUserId(authUser.getId());
			boardVo.setGroupId(getMaxGroupId()+1);
			boardVo.setOrderId(0);
			boardVo.setDepth(0);
		} else {
			updateOrder(boardVo);
			boardVo.setOrderId(boardVo.getOrderId()+1);
			boardVo.setDepth(boardVo.getDepth()+1);
			boardVo.setUserId(authUser.getId());
		}
		boardRepository.insert(boardVo);
	}

	public BoardVo getBoard(Long id) {
		return boardRepository.findById(id);
	}
	
	public void updateBoard(UserVo authUser, BoardVo boardVo) {
		if(checkingAuthor(authUser, boardVo)) {
			boardVo.setUserId(authUser.getId());
			boardRepository.updateBoard(boardVo);
		}
	}
	
	public void deleteBoard(Long userId, Long boardId) {
			boardRepository.delete(userId,boardId);
	}

	public void updateHit(BoardVo boardVo) {
		boardRepository.updateHit(boardVo);
	}
	
	private void updateOrder(BoardVo boardVo) {
		boardRepository.updateOrder(boardVo);
	}
	
	private int getMaxGroupId() {
		Integer maxGroupId = boardRepository.maxGroupId();
		if(maxGroupId == null) {
			maxGroupId = 1;
		}
		return maxGroupId;
	}
	
	private boolean checkingAuthor (UserVo authUser, BoardVo boardVo) {
		return authUser.getId().equals(boardVo.getUserId());
	}
	
	private Map<String, Integer> calculatePages(int currentPage, int totalPage){
		Map<String, Integer> pages = new HashMap<>();		
		
		totalPage = 13;
		
		int firstPage = currentPage > 3 ? totalPage - currentPage <= 2 ? totalPage >= 5? totalPage - 4: 1 : currentPage - 2 : 1;
		int lastPage = totalPage >=5? currentPage + 2 >= totalPage ? totalPage : currentPage > 3 ? currentPage + 2 : 5: totalPage;
		int prevPage = firstPage > 3 ? currentPage-2 : 1;
		int nextPage = currentPage >= lastPage ? lastPage : currentPage+2;
		
		pages.put("firstPage", firstPage);
		pages.put("lastPage", lastPage);
		pages.put("currentPage", currentPage);
		pages.put("prevPage", prevPage);
		pages.put("nextPage", nextPage);
		
		return pages;
	}
}
