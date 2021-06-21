package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public Boolean insert(UserVo vo) {
		int result = sqlSession.insert("user.insert", vo);
		return result == 1;
	}

	public UserVo findByEmailAndPassword(String email, String password) {
		Map<String, String> map = new HashMap<>();
		map.put("e", email);
		map.put("p", password);
		
		return sqlSession.selectOne("user.findByEmailAndPassword", map);
	}
	
	public UserVo findById(Long id) {
		return sqlSession.selectOne("user.findById",id);
	}
	
	public UserVo findByEmail(String email) {
		return sqlSession.selectOne("user.findByEmail",email);
	}

	public void update(UserVo vo) {
		sqlSession.update("user.update",vo);
	}

}
