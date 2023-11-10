package com.javaman.madax.jisik.controller;

import java.util.List;
import java.util.Map;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.jisik.model.JisikService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("jisik")
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
		public String JisikList(Board board, Model model) {
			
			
			Map<String, Object> map = service.jisikList(board);
			model.addAttribute("map", map);
	
		return "jisik/jisikList";
		
		
//		if(boardCode != 2) {
//			
//			return "null";
//			
//		} else {
//			
//			return "jisik/jisikList";
//		}
//		
//		
//		
//		// 뭘 받아 와야 하는지	
//		//
//			
	}
	
}
