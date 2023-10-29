package com.javaman.madax.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
public class MemberController {
	

	/** 로그인 전용 페이지 forward
	 * @return "member/login"
	 */
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	@GetMapping("signup")
	public String signup() {
		return "member/signup";
	}

}
