package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

public class MyBoardService {
/*
	@Autowired
	private BoardRepository boardRepository;
	
	public Map<String, Integer> getPages(int currentPage) {
		int totalPage = boardRepository.totalPage(null);
		return calculatePages(currentPage, totalPage);
	}
  
	public Map<String, Integer> getSearchedPages(int currentPage,String kwd) {
		int totalPage = boardRepository.searchedTotalPage(kwd);
		return calculatePages(currentPage, totalPage);
	}
	
	public List<BoardVo> getBoardList(int currentPage) {
		return boardRepository.findAll(currentPage);
	}
	
	public List<BoardVo> getSearchedBoardList(int currentPage,String kwd) {
		return boardRepository.searchedfindAll(currentPage, kwd);
	}

	public void writeBoard(BoardVo boardVo) {
		boardRepository.insert(boardVo);
	}

	public BoardVo getBoard(Long id) {
		return boardRepository.findById(id);
	}
	
	public void updateBoard(UserVo authUser, BoardVo boardVo) {
		if(checkingAuthor(authUser, boardVo)) {
			boardRepository.updateBoard(boardVo);
		}
	}
	
	public void deleteBoard(UserVo authUser, BoardVo boardVo) {
		if(checkingAuthor(authUser,boardVo)) {
			boardRepository.delete(boardVo);
		}
	}

	public void updateHit(BoardVo boardVo) {
		boardRepository.updateHit(boardVo);
	}
	
	public void updateOrder(BoardVo boardVo) {
		boardRepository.updateOrder(boardVo);
	}
	
	public int getMaxGroupId() {
		return boardRepository.maxGroupId();
	}
	
	private boolean checkingAuthor (UserVo authUser, BoardVo boardVo) {
		return authUser.getId().equals(boardVo.getUserId());
	}
	
	private Map<String, Integer> calculatePages(int currentPage, int totalPage){
		Map<String, Integer> pages = new HashMap<>();
		
		int firstPage = 1;
		int lastPage = 1;
		firstPage = currentPage > 3 ? totalPage - currentPage <= 2 ? totalPage >= 5? totalPage - 4: 1 : currentPage - 2 : 1;
		lastPage = totalPage >=5? currentPage + 2 >= totalPage ? totalPage : currentPage > 3 ? currentPage + 2 : 5: totalPage;
		//TODO: 다시 생각해서 이쁘게 만들길..
//		if(currentPage > 3) {
//			firstPage = currentPage - 2;
//			if(totalPage - currentPage <= 2) {
//				if( totalPage >= 5) {
//					firstPage = totalPage - 4;
//				}else {
//					firstPage = 1;
//				}
//			}
//		}
//		
//		if(currentPage +2 >= totalPage) {
//			lastPage = totalPage;
//		} else {
//			if(currentPage > 3 ) {
//				lastPage = currentPage + 2;
//			}
//		}
		
		pages.put("firstPage", firstPage);
		pages.put("lastPage", lastPage);
		pages.put("currentPage", currentPage);
		
		return pages;
	}
*/
}
