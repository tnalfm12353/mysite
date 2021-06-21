package com.douzone.mysite.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.dto.BoardDTO;
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
	
	@RequestMapping("")
	public String boardList(BoardDTO dto ,Model model) {
		Map<String,Integer> pages = boardService.getPages(dto.getPage(), dto.getKwd());
		List<BoardVo> list = boardService.getBoardList(dto.getPage(), dto.getKwd());
		model.addAttribute("list", list);
		model.addAttribute("pages",pages);
		return "board/list?page=" + dto.getPage() + "&kwd=" + dto.getKwd();
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
	public String write(@ModelAttribute BoardVo boardVo) {
		return "board/write";
	}
	
	@Auth
	@PostMapping("/write")
	public String write(@AuthUser UserVo authUser , @ModelAttribute @Valid BoardVo boardVo, BindingResult result, Model model,
						@RequestParam(name = "page", defaultValue = "1")Integer currentPage, 
						@RequestParam(defaultValue = "")String kwd) {
		checkingEmptyAuthUser(authUser);
		
		if(result.hasErrors()) {
			model.addAttribute(result.getModel());
			return "board/write";
		}
		boardService.writeBoard(authUser,boardVo);
		return "redirect:/board?page=" + currentPage + "&kwd=" + kwd;
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
		
		boardService.writeBoard(authUser,boardVo);
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
		
		boardService.updateBoard(authUser, boardVo);
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping("/delete/{id}")
	public String delete(@AuthUser UserVo authUser, @PathVariable Long id) {
		checkingEmptyAuthUser(authUser);
		boardService.deleteBoard(authUser.getId(),id);
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