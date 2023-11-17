package com.javaman.madax.jisik.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.jisik.model.EditJisikService;
import com.javaman.madax.jisik.model.JisikService;
import com.javaman.madax.member.model.dto.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("jisik")
@SessionAttributes({"loginMember"})

public class EditJisikController {
	
	private final EditJisikService service;
	
	private final JisikService jisikService;
	
	
	@GetMapping("jisikDelete/{boardNo:[0-9]+}")
	public String jisikDelete(
		@PathVariable("boardNo") int boardNo,
		RedirectAttributes ra,
		@SessionAttribute(value = "loginMember", required = false) Member loginMember) {
		
		if(loginMember == null) {
			ra.addFlashAttribute("message", "로그인 후 이용해주세요");
			return "redirect:/member/login";
		}
		
		Map<String, Integer> paramMap = new HashMap<>();
		paramMap.put("boardNo", boardNo);
		paramMap.put("memberNo", loginMember.getMemberNo());
		
		int result = service.jisikDelete(paramMap);
		
		// 
		String path = null;
		String message = null;
		
		if(result > 0) {
			message = "삭제 되었습니다";
			path = "redirect:/jisik/jisikList";
		} else {
			
			message = "삭제 실패";
			path = "redirect:/"; 
		}
		
		ra.addFlashAttribute("message", message);
			return  path;

	}
		
//	-----------------------------------------------------------------------------------------------
	
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

			RedirectAttributes ra) throws IllegalStateException, IOException {
		
			
			board.setMemberNo(loginMember.getMemberNo());
			
			int boardNo = service.jisikWrite(board, images);
			
			if(boardNo > 0) {
				
				ra.addFlashAttribute("message","게시글 작성 완료");
				return "redirect:/jisik/jisikDetail/" + boardNo;
//				return String.format("redirect:/jisik/jisikDetail/%d", boardNo);
			}
			
				ra.addFlashAttribute("message", "게시글 작성 실패");
				
			return "redirect:/jisik/jisikWrite";
	}
	
	
	@GetMapping("jisikUpdate/{boardNo:[0-9]+}")
	public String jisikUpdate(
			@PathVariable("boardNo") int boardNo,
			Model model) {
		
		Board board = jisikService.jisikDetail(boardNo);
		
		model.addAttribute("board", board);
		
		return "jisik/jisikUpdate";
		
	}
	
	@PostMapping("jisikUpdate/{boardNo:[0-9]+}")
	public String jisikUpdate(Model model,
			@PathVariable("boardNo") int boardNo,
			Board board,
			String querystring,
			String deleteOrder,		
			@RequestParam("images") List<MultipartFile> images,
			RedirectAttributes ra) throws IllegalStateException, IOException {
		
		board.setBoardNo(boardNo);
		
		int result = service.jisikUpdate(board, images, deleteOrder);
		
		model.addAttribute(boardNo);
		
		String message = null;
		String path = null;
		
		if(result > 0) {
			message = "게시글 수정 완료";
			path = "redirect:/jisik/jisikDetail/" + boardNo; 
			
//			path = String.format("redirect:/jisik/jisikDetail/%d%s" , boardNo, querystring);
			
			
		} else {
			message = "수정 실패";
			path = "redirect:/jisik/jisikDetail/" + boardNo; 
		}
		ra.addFlashAttribute("message", message);
		return path;
		
	}
	
}

		
		
		
	
		
