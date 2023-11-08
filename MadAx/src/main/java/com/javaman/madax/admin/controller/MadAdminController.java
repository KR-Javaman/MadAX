package com.javaman.madax.admin.controller;

import java.util.List;  

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaman.madax.admin.model.MadAdminService;
import com.javaman.madax.member.model.dto.Member;

@Controller
@RequestMapping("admin")
public class MadAdminController {
	
	@Autowired 
	private MadAdminService service;
	
	@GetMapping("main")
	public String adminMain() {
		
		
		
		return "admin/main";
	}
	
	

	@GetMapping("selectMember")
	public String selectMember(String inputEmail, Model model) {
		
		Member searchMember = service.selectMember(inputEmail);
		
		
		// 이메일이 일치하는 회원이 존재하는 경우
		// model.addAttribute("searchMember", value);
		// return "admin/success"; // forward
		if(searchMember != null) {
			model.addAttribute("searchMember", searchMember);
			return "admin/success"; 
		}
		
		// 이메일이 일치하는 회원이 존재하지 않는 경우
		// return "admin/fail"; // forward
		return "admin/fail";
	}
	
	
	
	@GetMapping("selectAll")
	public String selectAll(Model model) {
		
		List<Member> memberList = service.selectAll();
		
		model.addAttribute("memberList", memberList);
		
		return "admin/selectAll";
	}
	
	
	
			
	
	
			
}
