package com.javaman.madax.myPage.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.javaman.madax.member.model.dto.Member;
import com.javaman.madax.myPage.model.service.MyCommunityService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("myPage")
@SessionAttributes({"loginMember"})
@RequiredArgsConstructor
public class MyCommunityController {
	
private final MyCommunityService service;

@GetMapping("myPage-community")
public String selectBoard(@SessionAttribute(value="loginMember", required=false /*필수는 아니다*/) Member loginMember, Model model,
						@RequestParam(value = "cp", required = false, defaultValue = "1") int cp
						) {
	
	
	
	Map<String, Object> map = service.selectBoard(loginMember.getMemberNo(), cp);
	
	model.addAttribute("map",map);
	
	
	return "myPage/myPage-community";
}





}
