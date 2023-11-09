package com.javaman.madax.board.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaman.madax.board.model.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController{
	
private final BoardService service;
	
	
	@GetMapping("{boardCode:[0-9]+}")
	public String selectBoard(@PathVariable("boardCode") int boardCode,
								Model model,
								@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		
			
		Map<String, Object> map = service.selectBoard(boardCode, cp);
		
		model.addAttribute("map",map);
		
		
		return "board/boardList";
	}
	
	

	
	
	
	
	
	
	
	
	
	
}


