package com.javaman.madax.email.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaman.madax.email.model.service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController // @Controller 역할 + @ResponseBody (둘이 섞여있는 annotation)
@RequestMapping("email")
@RequiredArgsConstructor 
public class EmailController {
	

	private final EmailService service;
	
	/** 회원 가입 인증번호 생성 + 이메일 발송 + insert 또는 update
	 * @return
	 */
	@PostMapping("signup")
	public int signup(@RequestBody String email ) {
		return service.sendEmail("signup",email);
		
	}
	
	@PostMapping("checkAuthKey")
	public int checkAuthKey(@RequestBody Map<String, Object> paramMap) {
		return service.checkAuthKey(paramMap);
	}
	
}
