package com.javaman.madax.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("myPage")
public class MyPageController {
	
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

}
