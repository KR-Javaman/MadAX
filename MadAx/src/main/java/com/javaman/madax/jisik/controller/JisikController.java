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
	
	@GetMapping("jisikList")
	public String JisikList(Board board) {
		
		
		// 게시글 중 BOARD_CODE가 '2'인 게시글만 모두 불러온 후
		// JisikList 페이지에 결과가 나오도록 하겠다.
			
		List<Board> JisikList = service.JisikList();
		
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
//
//		
//		
		return "jisik/jisikList"; 
	}
	
}
