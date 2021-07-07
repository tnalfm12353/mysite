package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestbookRepository guestbookRepository;
	
	public List<GuestbookVo> getMessageList() {
		return guestbookRepository.findAll();
	}
	
	public List<GuestbookVo> getMessageList(Long id) {
		return guestbookRepository.findAll(id);
	}
	
	public Boolean deleteMessage(GuestbookVo vo) {
		return guestbookRepository.delete(vo);
	}
	
	public Boolean deleteMessage(Long id, String password) {
		GuestbookVo vo = new GuestbookVo();
		vo.setId(id);
		vo.setPassword(password);
		return guestbookRepository.delete(vo);
	}
	public void addMessage(GuestbookVo vo) {
		guestbookRepository.insert(vo);
	}
}
