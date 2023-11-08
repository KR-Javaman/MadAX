package com.javaman.madax.myPage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaman.madax.member.model.dto.Member;
import com.javaman.madax.myPage.model.service.MyPageService;


@Controller
@RequestMapping("myPage")
@SessionAttributes({"loginMember"})
public class MyPageController {
	
	@Autowired
	private MyPageService service;
	
	
	@GetMapping("myPage-home") // /myPage/info (이런 모양의 요청이 왔을 때!)
	public String myPageHome() {
		
		// templates/myPage/myPage-profile.html로 forward하겠다라는 뜻
		return "myPage/myPage-home";
	}
	
	@GetMapping("myPage-profile") // /myPage/info (이런 모양의 요청이 왔을 때!)
	public String myPageProfile() {
		
		// templates/myPage/myPage-profile.html로 forward하겠다라는 뜻
		return "myPage/myPage-profile";
	}
	
	@GetMapping("myPage-community") // /myPage/info (이런 모양의 요청이 왔을 때!)
	public String myPageCommunity() {
		
		// templates/myPage/myPage-profile.html로 forward하겠다라는 뜻
		return "myPage/myPage-community";
	}
	
	@GetMapping("myPage-secession") // /myPage/info (이런 모양의 요청이 왔을 때!)
	public String myPageSecession() {
		
		// templates/myPage/myPage-profile.html로 forward하겠다라는 뜻
		return "myPage/myPage-secession";
	}


	
	@PostMapping("changeNickname")
	public String changeNickname(Member updateMember, @SessionAttribute("loginMember") Member loginMember, RedirectAttributes ra) {
		
		updateMember.setMemberNo( loginMember.getMemberNo());
		
		int result = service.changeNickname(updateMember);
		
		String message = null;
		
		if(result > 0) {
			message = "닉네임이 수정되었습니다.";
			loginMember.setMemberNickname(updateMember.getMemberNickname());
		}
		
		else { 
			message = "회원 정보 수정 실패..";
			
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:myPage-profile";
	}
	
	
	@PostMapping("changePw")
	public String changePw(String currentPw, String newPw,
			@SessionAttribute("loginMember") Member loginMember, 
			RedirectAttributes ra) {
	
		int memberNo = loginMember.getMemberNo();
		
		int result = service.changePw(currentPw, newPw, memberNo);
		
		String path = null;
		String message = null;
		
		if(result > 0) {
			message="비밀번호 수정이 완료되었습니다!";
			path="redirect:myPage-profile";
			
		}
		
		else {
			
			message= "현재 비밀번호가 일치하지 않습니다.";
			path = "redirect:myPage-profile";
		}
		
		ra.addFlashAttribute("message",message);
		return path;
	}
	
	
	
	
	

	@PostMapping("secession")
	public String secession(String memberPw, @SessionAttribute("loginMember") Member loginMember, 
			RedirectAttributes ra, SessionStatus status) {
		
		int memberNo = loginMember.getMemberNo();
		
		int result = service.secession(memberPw, memberNo);
		
		String path = null;
		String message = null;
		
		if(result > 0) {
			message = "탈퇴 되었습니다.";
			path = "redirect:/";
			status.setComplete();
		}
		
		else {
			message = "비밀번호가 일치하지 않습니다.";
			path = "redirect:secession";
		}
		
		ra.addFlashAttribute("message", message);
		
		return path;
	}
	
}


