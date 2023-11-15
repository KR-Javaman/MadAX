package com.javaman.madax.jisik.controller;

import java.text.ParseException; 
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.jisik.model.JisikService;
import com.javaman.madax.member.model.dto.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("jisik")
@SessionAttributes({ "loginMember" })
public class JisikController {
	
	@Autowired
	private JisikService service;
			
		// Board 
		// Board_Code -> 2이면 css,html 다르게
		// 
		
		// Board/1  Board/2가 존재
		// Board/2인 경우를 jisik/jisikList로 두고 css, html 다르게 
		
		// 게시글 중 BOARD_CODE가 '2'인 게시글만 모두 불러온 후
		// JisikList 페이지에 결과가 나오도록 하겠다.
		
		
		// 게시글 중 BOARD_CODE가 '2'인 게시글만 모두 불러온 후
		// JisikList 페이지에 결과가 나오도록 하겠다.
		
		// 얻어와야 하는 값 Board DTO 
		// Board DTO -> 
		// MAP으로 
	
		@GetMapping("jisikList")
		public String jisikList(Model model, 
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap
				) {
		
			
			Map<String, Object> map = service.jisikList(paramMap, cp);
		
			model.addAttribute("map", map);
			
			
			
//			if(paramMap.get("key") == null && paramMap.get("query") == null) { // 일반 목록 조회
//				Map<String, Object> map = service.selectJisikList(board, cp);
//				
//
//			} else {
//				
//				paramMap.put("board", board);
//				
//				Map<String, Object> map = service.searchJisikList(paramMap, cp);
//				
//				model.addAttribute("map", map);
//				
//				
//			}

		return "jisik/jisikList";
		
	}
		
	@GetMapping("jisikDetail/{boardNo:[0-9]+}")
	public String jisikDetail(Model model, 
			@PathVariable("boardNo") int boardNo,
			@SessionAttribute(value = "loginMember", required = false) Member loginMemberx,
				HttpServletRequest req, 
				HttpServletResponse resp) throws ParseException {
		
		
		Board board = service.jisikDetail(boardNo);
		
		
			
		model.addAttribute("board", board);
		return "jisik/jisikDetail";

	} 
	
	
		
			
		
}		