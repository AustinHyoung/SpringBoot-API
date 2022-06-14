package com.example.test.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Transactional
	public List<Map<String, Object>> getMember() throws SQLException {
		return sqlSession.selectList("UMemberDAO.getMember");
	}
	
	@Transactional
	public List<Map<String, Object>> getQnaBoard() throws SQLException {
		return sqlSession.selectList("UMemberDAO.getQnaBoard");
	}
	
	@Transactional
	public List<Map<String, Object>> getQnaPagingBoard() throws SQLException {
		return sqlSession.selectList("UMemberDAO.getQnaPagingBoard");
	}
	
	//게시글의 답글 가져오기
	@Transactional
	public List<Map<String, Object>> getQnaReply(Map<String, Object> param) throws SQLException {
		return sqlSession.selectList("UMemberDAO.getQnaReply", param);
	}
	
	@Transactional
	public Map<String, Object> getUser (Map<String, Object> param) throws SQLException {
		return (Map<String, Object>) sqlSession.selectOne("UMemberDAO.getUser", param);
	}
	
	@Transactional
	public int userCnt (Map<String, Object> param) throws SQLException {
		return sqlSession.selectOne("UMemberDAO.userCnt", param);
	}
	
	@Transactional
	public Map<String, Object> insertUser (Map<String, Object> param) throws SQLException {
		return (Map<String, Object>) sqlSession.selectOne("UMemberDAO.insertUser", param);
	}
	
	@Transactional
	public Map<String, Object> getQnaDetail (Map<String, Object> param) throws SQLException {
		return (Map<String, Object>) sqlSession.selectOne("UMemberDAO.getQnaDetail", param);
	}
}
