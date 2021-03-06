package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(BoardVo vo) {
		int result = sqlSession.insert("board.insert", vo);
		return result == 1;
	}

	public List<BoardVo> findAll(int currentPage) {
		Map<String, Object> map = new HashMap<>();
		map.put("currentPage",currentPage);
		map.put("kwd", "");
		
		return sqlSession.selectList("board.findAll", map);
	}
	
	public List<BoardVo> searchedfindAll(int currentPage, String kwd) {
		Map<String, Object> map = new HashMap<>();
		map.put("currentPage",currentPage);
		map.put("kwd", kwd);
		
		return sqlSession.selectList("board.findAll", map);
	}
	
	public BoardVo findById(Long id) {
		return sqlSession.selectOne("board.findById",id); 
	}
	
	public int maxGroupId() {
		Integer result = sqlSession.selectOne("board.maxGroupId");
		if(result == null) {
			result = 1;
		}
		return result;
				 
	}
	
	public void delete (BoardVo boardVo) {
		sqlSession.delete("board.delete",boardVo);
	}

	public void updateBoard(BoardVo boardvo) {
		sqlSession.update("board.updateBoard",boardvo);
	}

	public void updateHit(BoardVo boardVo) {
		sqlSession.update("board.updateHit", boardVo);
	}

	public void updateOrder(BoardVo boardVo) {
		sqlSession.update("board.updateOrder",boardVo);
		
	}

	public int totalPage() {
		return sqlSession.selectOne("board.totalPage");
	}

	public int searchedTotalPage(String kwd) {
		System.out.println(kwd);
		return sqlSession.selectOne("board.totalPage", kwd);
	}
}
