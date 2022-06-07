package com.example.test.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

public interface MemberService {
	
	@Transactional
	public List<Map<String, Object>> getMember() throws SQLException;
	
	@Transactional
	public List<Map<String, Object>> getMainBoard() throws SQLException;
	
	@Transactional
	public Map<String, Object> getUser(Map<String, Object> param) throws SQLException;
	
	@Transactional
	public int userCnt(Map<String, Object> param) throws SQLException;
	
	
	@Transactional
	public Map<String, Object> insertUser(Map<String, Object> param) throws SQLException;
	
	@Transactional
	public Map<String, Object> getQnaDetail(Map<String, Object> param) throws SQLException;
}
