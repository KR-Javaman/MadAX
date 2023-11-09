package com.javaman.madax.info;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaman.madax.info.model.InfoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

public class InfoController{
	
	private final InfoService service;
	
	
	@GetMapping("{boardCode:[0-9]+}")
	public String selectBoardList(@PathVariable("boardCode") int boardCode, Model model,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp,
			@RequestParam Map<String, Object> paramMap) {
			
		if(paramMap.get("key") == null && paramMap.get("query") == null) { // 일반 목록 조회
			Map<String, Object> map = service.selectBoardList(boardCode, cp);
			
			model.addAttribute("map", map);
			
		} else {
			
			
			paramMap.put("boardCode", boardCode);
			
			Map<String, Object> map = service.searchBoardList(paramMap, cp);
			
			model.addAttribute("map", map);
			
			
		}
		

		return "board/boardList";
	}
	
	
	
	
}




