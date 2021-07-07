package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource dataSource;
	
	
	public void insert(GuestbookVo vo) {
		sqlSession.insert("guestbook.insert",vo);
	}

	public List<GuestbookVo> findAll() {
		return sqlSession.selectList("guestbook.findAll");
	}
	
	public List<GuestbookVo> findAll(Long id) {
		return sqlSession.selectList("guestbook.findAllById",id);
	}
	
	public GuestbookVo findById(Long id) {
		GuestbookVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
	
			String sql = "select id, password"
					+ "		from guestbook"
					+ "		where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = new GuestbookVo();
				result.setId(rs.getLong(1));
				result.setPassword(rs.getString(2));
			}
				
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				// 자원정리(clean-up)
				if(pstmt != null) { pstmt.close();}
				if(conn != null) { conn.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result; 
	}
	
	public boolean delete (GuestbookVo vo) {
		int count = sqlSession.delete("guestbook.delete",vo);
		return count == 1;
	}
}
