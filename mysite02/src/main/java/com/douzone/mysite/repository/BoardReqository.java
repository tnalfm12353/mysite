package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

public class BoardReqository {
	
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.80.105:3307/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url,"webdb","webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		}
		
		return conn;
	}
	
	public Boolean insert(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Boolean result = false;
		try {
			conn = getConnection();
			
			String sql = "insert into board values(null,?,?,?,0,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getRegDate());
			pstmt.setInt(4, vo.getGroupId());
			pstmt.setInt(5, vo.getOrderId());
			pstmt.setInt(6, vo.getDepth());
			pstmt.setLong(7, vo.getUserId());
			int count = pstmt.executeUpdate();
			result = count == 1;
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

	public List<BoardVo> findAll(int currentPage) {
		List<BoardVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
	
			String sql = "select b.id, b.title, date_format(b.reg_date,'%Y-%m-%d %H:%i:%s'), b.hit, b.group_id, b.order_id, b.depth, b.user_id, u.name"
					+ "		from board b, user u"
					+ "		where b.user_id = u.id"
					+ "		order by group_id desc, order_id asc"
					+ "		limit ?, ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 3 * (currentPage-1));
			pstmt.setInt(2, 3);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setId(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setRegDate(rs.getString(3));
				vo.setHit(rs.getInt(4));
				vo.setGroupId(rs.getInt(5));
				vo.setOrderId(rs.getInt(6));
				vo.setDepth(rs.getInt(7));
				vo.setUserId(rs.getLong(8));
				vo.setUserName(rs.getString(9));
				
				result.add(vo);
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
	
	public BoardVo findById(Long id) {
		BoardVo vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
	
			String sql = "select id, title, content, hit, user_id, group_id, order_id, depth"
					+ "		from board"
					+ "		where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new BoardVo();
				vo.setId(rs.getLong(1));
				vo.setTitle(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setHit(rs.getInt(4));
				vo.setUserId(rs.getLong(5));
				vo.setGroupId(rs.getInt(6));
				vo.setOrderId(rs.getInt(7));
				vo.setDepth(rs.getInt(8));
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
		return vo; 
	}
	public int maxGroupId() {
		int maxGroupId = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
	
			String sql = "select max(group_id)"
					+ "		from board";
					
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				maxGroupId = rs.getInt(1);
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
		return maxGroupId; 
	}
	public void delete (BoardVo boardVo,UserVo userVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "delete from board"
						+"	where id = ?"
						+"	and user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, boardVo.getId());
			pstmt.setLong(2, userVo.getId());
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

	public void updateBoard(BoardVo boardvo,UserVo userVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql = "update board set title = ? , content = ? where id = ? and user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardvo.getTitle());
			pstmt.setString(2, boardvo.getContent());
			pstmt.setLong(3, boardvo.getId());
			pstmt.setLong(4, userVo.getId());
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

	public void updateHit(BoardVo boardvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql = "update board set hit = ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardvo.getHit()+1);
			pstmt.setLong(2, boardvo.getId());
			
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

	public void updateOrder(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql = "update board set order_id = order_id+1 where group_id = ? and order_id >= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getGroupId());
			pstmt.setInt(2, vo.getOrderId());
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

	public int totalPage() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = getConnection();
			String sql = "select ceil(count(*)/3) from board";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				// 자원정리(clean-up)
				if(pstmt != null) { pstmt.close();}
				if(conn != null) { conn.close();}
				if(rs != null) {rs.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int serachTotalPage(String kwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = getConnection();
			String sql = "select ceil(count(*)/3) "
					+ "		from board b, user u "
					+ "		where b.user_id = u.id"
					+ "		and title like '%?%' "
					+ "		and u.name like '%?%'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				// 자원정리(clean-up)
				if(pstmt != null) { pstmt.close();}
				if(conn != null) { conn.close();}
				if(rs != null) {rs.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
