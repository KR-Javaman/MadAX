package com.javaman.madax.shorts.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.javaman.madax.member.model.dto.Member;
import com.javaman.madax.shorts.model.dto.Video;
import com.javaman.madax.shorts.model.dto.VideoBoard;
import com.javaman.madax.shorts.model.service.ShortsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("shorts")
@SessionAttributes({"loginMember"})
public class ShortsController {

	private final ShortsService service;
	
	@GetMapping("main")
	public String main(VideoBoard video ,Model model,
				@RequestParam(value="cp", required = false, defaultValue = "1") int cp) {

		
		Map<String, Object> map = service.main(cp,video);
		model.addAttribute("map", map);
		
		return "shorts/shorts-main";

	}
	
	
	@GetMapping("edit/insert")
	public String writeInsert(@SessionAttribute(value="loginMember", required=false) Member loginMember) {
		if(loginMember == null) return "redirect:/shorts/main";
		
		return "shorts/shortsWrite";
	}
	
	@PostMapping("edit/insert")
	public String writeInsert(@SessionAttribute("loginMember") Member loginMember, VideoBoard board,
							@RequestParam("shortsVideo") MultipartFile video) {
		
		board.setMemberNo(loginMember.getMemberNo());
		
		int result = service.writeInsert(board,video);
		return null;
	}
	
	
}
