package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.SiteVo;

@Repository
public class AdminRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public SiteVo findById() {
		return sqlSession.selectOne("site.findById", 1);
	}
	
	public void updateSite(SiteVo siteVo) {
		sqlSession.update("site.updateSite", siteVo);
	}
}
