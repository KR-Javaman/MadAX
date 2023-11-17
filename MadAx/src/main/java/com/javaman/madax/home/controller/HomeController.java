package com.javaman.madax.home.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.home.model.service.HomeService;
import com.javaman.madax.shorts.model.dto.VideoBoard;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("homePage")
@RequiredArgsConstructor
public class HomeController {
		
	private final HomeService service;
	
	@GetMapping("homePage")
	public String homePage(Board board,
			Model model, VideoBoard video,
			@RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		
			
		Map<String, Object> map = service.selectCommunity(board, cp);
		Map<String, Object> map2 = service.selectJisikin(board, cp);
		Map<String, Object> map3 = service.selectVideo(video, cp);
		
		model.addAttribute("map",map);
		model.addAttribute("map2",map2);
		model.addAttribute("map3",map3);
		
		
		
		return "homePage/homePage";
	}
	
	
	@GetMapping("terms")
	public String terms() {
		
		return "homePage/terms";
	}
	
	@GetMapping("terms2")
	public String terms2() {
		
		return "homePage/terms2";
	}
	
	

}
