package com.javaman.madax.jisik.controller;

import java.io.IOException; 
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.jisik.model.EditJisikService;
import com.javaman.madax.jisik.model.JisikService;
import com.javaman.madax.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("jisik")

public class EditJisikController {
	
	private final EditJisikService service;
	
	private final JisikService jisikservice;
	
	
	
	
	@GetMapping("jisikWrite")
	public String jisikWrite( 	
			@SessionAttribute(value="loginMember", required=false) Member loginMember) {
			
			if(loginMember == null) {
				return "jisik/jisikList";
			} 
			
			return "jisik/jisikWrite";
			
		}

//	"{boardCode:[0-9]+}/{categoryCode:[0-9]+}/{categoryCodeTwo:[0-9]+}/jisikWrite"
	

	
	
	// 받아 와야 하는 파라미터 : 제목, 내용, 이미지, 밸류값
	
	@PostMapping("jisikWrite")
	public String jisikWrite(
			@SessionAttribute("loginMember") Member loginMember,
			Board board,
			@RequestParam("images") List<MultipartFile> images,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap,
			RedirectAttributes ra) 
	
					throws IllegalStateException, IOException {
			
			if(paramMap.get("Key") == null && paramMap.get("query") == null) {
				return "";
			}
			
			Map<String, Object> map = service.jisikWrite(paramMap, cp);
			
			board.setMemberNo(loginMember.getMemberNo());
			
			int boardNo = service.jisikWrite(board, images);
			
			if(boardNo > 0) {
				ra.addFlashAttribute("message","게시글 작성 완료");
				return "/jisikList";
			}
			
				ra.addFlashAttribute("message", "게시글 작성 실패");
				
			return "redirect:jisikWrite/";
	}
	


}
