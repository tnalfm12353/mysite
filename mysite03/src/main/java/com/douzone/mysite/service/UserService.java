package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public Boolean joinUser(UserVo vo) {
		return userRepository.insert(vo);
	}
	
	public UserVo getUser(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}
}
