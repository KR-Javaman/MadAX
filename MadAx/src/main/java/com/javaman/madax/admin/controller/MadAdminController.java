package com.javaman.madax.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaman.madax.admin.model.MadAdminService;

@Controller
@RequestMapping("/admin")
public class MadAdminController {
	
	@Autowired
	private MadAdminService service;
	
	@GetMapping("/main")
	public String adminMain() {
		
		return "null";
	}
			
			
}
