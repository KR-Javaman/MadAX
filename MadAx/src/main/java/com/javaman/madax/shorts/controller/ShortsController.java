package com.javaman.madax.shorts.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaman.madax.shorts.model.dto.Video;
import com.javaman.madax.shorts.model.dto.VideoBoard;
import com.javaman.madax.shorts.model.service.ShortsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("shorts")
public class ShortsController {

	private final ShortsService service;
	
	@GetMapping("main")
	public String main(VideoBoard video ,Model model,
				@RequestParam(value="cp", required = false, defaultValue = "1") int cp) {

		
		Map<String, Object> map = service.main(cp,video);
		model.addAttribute("map", map);
		
		return "shorts/shorts-main";

	}
	
	
	
	
}
