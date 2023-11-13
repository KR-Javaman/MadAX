package com.javaman.madax.board.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.board.model.service.BoardService;
import com.javaman.madax.member.model.dto.Member;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController{
	
private final BoardService service;
	
	
	/**게시글 조회
	 * @param boardCode
	 * @param model
	 * @param cp
	 * @return
	 */
	@GetMapping("{boardCode:[0-9]+}")
	public String selectBoard(@PathVariable("boardCode") int boardCode,
								Model model,
								@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		
			
		Map<String, Object> map = service.selectBoard(boardCode, cp);
		
		model.addAttribute("map",map);
		
		
		return "board/boardList";
	}
	
	
	
	
	@GetMapping("{boardCode:[0-9]+}/{boardNo:[0-9]+}")
	public String detail(@PathVariable("boardCode")int boardCode,
						@PathVariable("boardNo")int boardNo, 
						Model model,
						RedirectAttributes ra) {
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("boardCode", boardCode);
		map.put("boardNo", boardNo);
		
		Board board = service.detail(map);
		
		if(board != null) {
			model.addAttribute("board",board);
			return "board/boardDetail";
		}
			
		return "board/boardDetail";
		
	}
	
	
	
	
	
	
	
	

}


