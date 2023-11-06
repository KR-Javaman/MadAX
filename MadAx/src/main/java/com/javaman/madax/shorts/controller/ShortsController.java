package com.javaman.madax.shorts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("shorts")
public class ShortsController {

	@GetMapping("main")
	public String main() {
		return "shorts/shorts-main";
	}
}
