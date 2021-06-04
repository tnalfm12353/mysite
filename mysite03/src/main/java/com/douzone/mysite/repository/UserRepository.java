package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		UserVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
	
			String sql = "select id, email, name, gender"
					+ "		from user"
					+ "		where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = new UserVo();
				result.setId(rs.getLong(1));
				result.setEmail(rs.getString(2));
				result.setName(rs.getString(3));
				result.setGender(rs.getString(4));
			}
				
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				// 자원정리(clean-up)
				if(pstmt != null) { pstmt.close();}
				if(conn != null) { conn.close();}
				if(rs != null)	{ rs.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result; 
	}

	public void update(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql = "update user set name = ? , gender = ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getGender());
			pstmt.setLong(3, vo.getId());
			pstmt.executeUpdate();
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
	}

	public void updatePassword(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql = "update user set password = ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPassword());
			pstmt.setLong(2, vo.getId());
			pstmt.executeUpdate();
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
		
	}
}
