package com.example.test.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.dao.MemberDAO;
import com.example.test.service.MemberService;

@Service("MemberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public List<Map<String, Object>> getMember() throws SQLException {
		return memberDAO.getMember();
	}
	
	@Override
	public List<Map<String, Object>> getQnaBoard() throws SQLException {
		return memberDAO.getQnaBoard();
	}
	
	@Override
	public List<Map<String, Object>> getQnaReply(Map<String, Object> param) throws SQLException {
		return memberDAO.getQnaReply(param);
	}
	
	@Override
	public Map<String, Object> getUser(Map<String, Object> param) throws SQLException {
		return memberDAO.getUser(param);
	}
	
	@Override
	public int userCnt(Map<String, Object> param) throws SQLException {
		return memberDAO.userCnt(param);
	}
	
	@Override
	public Map<String, Object> insertUser(Map<String, Object> param) throws SQLException {
		return memberDAO.insertUser(param);
	}
	
	@Override
	public Map<String, Object> getQnaDetail(Map<String, Object> param) throws SQLException {
		return memberDAO.getQnaDetail(param);
	}
}
