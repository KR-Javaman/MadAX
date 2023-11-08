package com.javaman.madax.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MainController {

	
	
	@RequestMapping("/")
	public String MainPage() {
		
		
		return "common/main";
		
	}
	
	
}
