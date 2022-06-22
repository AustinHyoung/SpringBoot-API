package com.example.test.controller;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.service.MemberService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberController {
	
	@Resource(name = "MemberService")
	private MemberService memberService;

	@GetMapping ("/api1")
	public String apiTest1() {
		return "api test!";
	}
	
	
	@RequestMapping (path = "apis/session", produces = "application/json")
	public @ResponseBody Object apiSession(HttpServletRequest request, Model model, HttpSession session) {
		
		
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			
			Map<String, Object> getUser = (Map<String, Object>) session.getAttribute("user_info");
			
			
			if(getUser == null) {
				res.put("checkSession", null);
				res.put("msg", "세션이 만료되었습니다.");
			}
			
			res.put("code", HttpStatus.CREATED.value());
			
			return res;
			
		} catch(Exception e) {
			res.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			
			return res;
		}
	}
	
	@RequestMapping (path = "/apis/member", produces = "application/json")
	public @ResponseBody Object apiMember() {
		
		try {
			
			List<Map<String, Object>> getMember = memberService.getMember();
			System.out.println(getMember);
			return getMember;
		}catch(Exception e) {
			e.printStackTrace();
			return e;
		}
		
	}
	
	@RequestMapping (path = "/apis/qnaboard", produces = "application/json")
	public @ResponseBody Object apiQnaBoard() {
		Map<String, Object> res =  new HashMap<String, Object>();
		try {
			
			List<Map<String, Object>> getMainBoard = memberService.getQnaBoard();
			return getMainBoard;
		}catch(Exception e) {
			e.printStackTrace();
			return e;
		}
		
	}
	
	@RequestMapping (path = "/apis/qnaPagingBoard", produces = "application/json")
	public @ResponseBody Object apiQnaPagingBoard() {
		Map<String, Object> res =  new HashMap<String, Object>();
		try {
			
			List<Map<String, Object>> getQnaPagingBoard = memberService.getQnaPagingBoard();
			return getQnaPagingBoard;
		}catch(Exception e) {
			e.printStackTrace();
			return e;
		}
		
	}
	
	@RequestMapping (path = "/apis/qnareply", produces = "application/json")
	public @ResponseBody Object apiQnaReply(@RequestBody Map<String, Object> param) {
		Map<String, Object> res =  new HashMap<String, Object>();
		try {
			
			List<Map<String, Object>> getQnaReply = memberService.getQnaReply(param);
			return getQnaReply;
		}catch(Exception e) {
			e.printStackTrace();
			return e;
		}
		
	}
	
	@RequestMapping (path = "/apis/qnadetail", produces = "application/json")
	public @ResponseBody Object apiQnaDetail(@RequestBody Map<String, Object> param) {
		
		try {
			Map<String, Object> getQnaDetail = memberService.getQnaDetail(param);
			
			String dateStr = getQnaDetail.get("QNA_DATE").toString();
			dateStr = dateStr.replace("T", " ");
			getQnaDetail.put("QNA_DATE", dateStr);
			return getQnaDetail;
			
		} catch (Exception e) {
			return e;
		}
	}
	
	@PostMapping (path = "/apis/login", produces = "application/json")
	public @ResponseBody Object apiLogin(@RequestBody Map<String, Object> param, HttpSession session) {
		
		System.out.println(param);
		
		try {
			
			Map<String, Object> res = new HashMap<String, Object>();
			Map<String, Object> user = memberService.getUser(param);
			if(user == null) {
				res.put("msg", "아이디 또는 비밀번호를 확인해주세요.");
				
				return res;
			}
			
			
			res.put("code", HttpStatus.CREATED.value());
			res.put("msg", "로그인 성공");
			
			session.setAttribute("user_info", user);
			session.setMaxInactiveInterval(60*30);
			
			res.put("session", session.getAttribute("user_info"));
			System.out.println("==============");
			System.out.println("session ::: " + session.getAttribute("user_info"));
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			return e;
		}
		
	}
	
	@PostMapping (path = "/apis/replyInsert", produces = "application/json")
	public @ResponseBody Object apiReplyInsert(@RequestBody Map<String, Object> param, HttpSession session) {
		
		Map<String, Object> res = new HashMap<String, Object>();
		
		try {
			
			memberService.insertReply(param);
			res.put("msg", "답변 등록 완료");
			
			return res;
			
		} catch(Exception e) {
			e.printStackTrace();
			
			return e;
		}
	}
	
	@PostMapping (path = "/apis/signup", produces = "application/json")
	public @ResponseBody Object apiSignup(@RequestBody Map<String, Object> param, HttpSession session) {
		
		System.out.println(param);
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			
			
			int userCnt = memberService.userCnt(param);

			if(userCnt >= 1) {
				res.put("msg", "이미 존재하는 아이디 입니다.");
				
			} else {
				
				memberService.insertUser(param);
				res.put("msg", "가입이 완료 되었습니다.");
			}

			
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			return e;
		}
		
	}
	
	@PostMapping (path = "/apis/logout", produces = "application/json")
	public @ResponseBody Object apiLogout(HttpServletRequest request, HttpSession session) {
		
		
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			res.put("code", HttpStatus.CREATED.value());
			res.put("msg", "로그아웃 완료");

			session.removeAttribute("user_info");
			
			return res;
		}catch(Exception e) {
			res.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			
			return res;
		}
		
	}
	
	@PostMapping (path = "/apis/qnaViewCnt", produces = "application/json")
	public @ResponseBody Object apiQnaViewCnt(@RequestBody Map<String, Object> param, HttpSession session) {
		
		try {
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			
			int cnt = memberService.getQnaViewCnt(param) + 1;
			int qnaNo = Integer.parseInt(param.get("qnaNo").toString());
			System.out.println(qnaNo);
			
			paramMap.put("qnaViewCnt", cnt);
			paramMap.put("qnaNo", qnaNo);
			
			memberService.updateQnaViewCnt(paramMap);
			
			int cntAddOne = memberService.getQnaViewCnt(param);
			
			return cntAddOne;
		}catch(Exception e) {
			
			e.printStackTrace();
			return e;
		}
		
	}
	
}
