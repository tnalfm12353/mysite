package com.douzone.mysite.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.exception.EmptyAuthUserException;
import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@RequestMapping("/board")
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = {"","/{page}"})
	public String boardList(@PathVariable(name = "page", required = false )Integer currentPage, Model model) {
		if(currentPage == null) {
			currentPage = 1;
		}
		Map<String,Integer> pages = boardService.getPages(currentPage);
		
		List<BoardVo> list = boardService.getBoardList(currentPage);

		model.addAttribute("list", list);
		model.addAttribute("pages",pages);
		return "board/list";
	}
	
	@RequestMapping(value ={"/search","/search/{page}"})
	public String searchedBoardList(@PathVariable(name = "page", required = false )Integer currentPage, String kwd, Model model ) {
		if(currentPage == null) {
			currentPage = 1;
		}
		if("".equals(kwd)) {
			return "redirect:/board";
		}
		
		Map<String,Integer> pages = boardService.getSearchedPages(currentPage,kwd);
		
		List<BoardVo> list = boardService.getSearchedBoardList(currentPage,kwd);
		model.addAttribute("kwd", kwd);
		model.addAttribute("list", list);
		model.addAttribute("pages",pages);
		return "board/list";
	}

	@RequestMapping("/view/{id}")
	public String view(@AuthUser UserVo authUser,@PathVariable Long id, Model model) {
		checkingEmptyAuthUser(authUser);
		BoardVo boardVo = getBoard(id, model);
		boardService.updateHit(boardVo);
		return "board/view";
	}
	
	@Auth
	@RequestMapping("/write")
	public String write() {
		return "board/write";
	}
	
	@Auth
	@PostMapping("/write")
	public String write(@AuthUser UserVo authUser , BoardVo boardVo, Model model) {
		checkingEmptyAuthUser(authUser);
		if("".equals(boardVo.getTitle())|| "".equals(boardVo.getContent())) {
			String status = "Please Fill Out the Form above";
			model.addAttribute("status",status);
			return "board/write";
		}
		boardVo.setUserId(authUser.getId());
		boardVo.setGroupId(boardService.getMaxGroupId()+1);
		boardVo.setOrderId(0);
		boardVo.setDepth(0);
		boardService.writeBoard(boardVo);
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping("/reply/{id}")
	public String reply(@PathVariable Long id, Model model) {
		getBoard(id, model);
		return "board/reply";
	}
	
	@Auth
	@PostMapping("/reply")
	public String reply(@AuthUser UserVo authUser, BoardVo boardVo) {
		checkingEmptyAuthUser(authUser);
		
		boardVo.setOrderId(boardVo.getOrderId()+1);
		boardVo.setDepth(boardVo.getDepth()+1);
		boardVo.setUserId(authUser.getId());
		
		boardService.updateOrder(boardVo);
		boardService.writeBoard(boardVo);
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping("/modify/{id}")
	public String modify(@PathVariable Long id, Model model) {
		getBoard(id, model);
		return "board/modify";
	}
	
	@Auth
	@PostMapping("/modify/{id}")
	public String modify(@AuthUser UserVo authUser,@PathVariable Long id, BoardVo boardVo) {
		checkingEmptyAuthUser(authUser);
		boardVo.setId(id);
		boardService.updateBoard(authUser, boardVo);
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping("/delete/{id}")
	public String delete(@AuthUser UserVo authUser, @PathVariable Long id) {
		checkingEmptyAuthUser(authUser);
		
		BoardVo boardVo = boardService.getBoard(id);
		boardService.deleteBoard(authUser,boardVo);
		
		checkingEmptyAuthUser(authUser);
		return "redirect:/board";
	}
	
	private BoardVo getBoard(Long id, Model model) {
		BoardVo boardVo = boardService.getBoard(id);
		model.addAttribute("board", boardVo);
		
		return boardVo;
	}
	private void checkingEmptyAuthUser(UserVo authUser) {
		if(authUser == null) {
			throw new EmptyAuthUserException();
		}
	}
}
