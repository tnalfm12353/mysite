package com.douzone.mysite.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping
	public String boardList(@RequestParam(name="page", required = false) Integer currentPage, String kwd,Model model) {
		
		return "board/list";
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
