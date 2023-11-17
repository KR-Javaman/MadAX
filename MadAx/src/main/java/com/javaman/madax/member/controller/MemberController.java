package com.javaman.madax.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaman.madax.member.model.dto.Member;
import com.javaman.madax.member.model.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("member") 
@SessionAttributes({"loginMember"}) // 세션 올리기
public class MemberController {

	@Autowired
	private MemberService service;
	
	
	/** 로그인
	 * @param inputMember : 아이디, 비밀번호 파라미터
	 * @param model : 데이터 전달 객체
	 * @param ra : 리다이렉트 시 request scope로 데이터 전달
	 * @param saveId : 아이디 저장 체크 시 "on", 미체크 시 null
	 * @param resp : 응답 방법을 제공하는 객체
	 * @return 메인 페이지(/) 리다이렉트
	 */
	@PostMapping("login") // /member/login (POST)
	public String login(Member inputMember, 
			Model model, RedirectAttributes ra,
			String saveId, HttpServletResponse resp) {
		
		Member loginMember = service.login(inputMember);
		
		if(loginMember != null) {
			
			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
			
			if(saveId != null) {
				
				cookie.setMaxAge(60 * 60 * 24 * 30); // 30일
			}
			
			else {
				
				cookie.setMaxAge(0);
			}
			
			cookie.setPath("/"); 
			
			resp.addCookie(cookie);
			
			Cookie cookie2 = new Cookie("testCookie",  "테스트");
			cookie2.setMaxAge(60 * 60 * 24 * 30);
			cookie2.setPath("/");
			resp.addCookie(cookie2);
		}
		
		if(loginMember == null) {
			ra.addFlashAttribute("message", 
								"아이디 또는 비밀번호가 일치하지 않습니다");
			
			return "redirect:/member/login";
		}
		
		model.addAttribute("loginMember", loginMember);
		
		return "redirect:/homePage/homePage";
	}
	
	
	@GetMapping("logout")
	public String logout(SessionStatus status) {
		status.setComplete(); //
		return "redirect:/homePage/homePage";
	}
	
	/** 로그인 전용 페이지 forward
	 * @return "member/login"
	 */
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	
	/** 회원 가입 페이지 forward
	 * @return
	 */
	@GetMapping("signup")
	public String signup() {
		return "member/signup";
	}
	
	/** 회원 가입
	 * @param inputMember : 파라미터가 저장된 커맨드 객체
	 * @param memberAddress : 주소 입력 값이 저장된 배열(가공 예정)
	 * @param ra : 리다이렉트 시 request scope로 값 전달
	 * @return
	 */
	@PostMapping("signup")
	public String signup(Member inputMember, 
			 RedirectAttributes ra ) {
		
		int result = service.signup(inputMember);
		
		if(result > 0) {
			ra.addFlashAttribute("message", "회원 가입 성공");
			return "redirect:/homePage/homePage"; // 메인 페이지
		}
		
		ra.addFlashAttribute("message","가입 실패...");
		return "redirect:signup"; // 회원 가입 페이지 (상대경로 작성)
	}
	
	
	@GetMapping("checkEmail")
	@ResponseBody
	public int checkEmail(String email) {
		return service.checkEmail(email);
	}
	
	@GetMapping("checkNickname")
	@ResponseBody
	public int checkNickname(String nickname) {
		return service.checkNickname(nickname);
		
	}
	
	
	
	}