package com.javaman.madax.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaman.madax.admin.model.MadAdminService;
import com.javaman.madax.member.model.dto.Member;

@Controller
@RequestMapping("admin")
public class MadAdminController {
	
	@Autowired 
	private MadAdminService service;
	
	@GetMapping("main")
	public String adminMain(Model model,Member member,
					@RequestParam(value="cp",required = false, defaultValue = "1") int cp,
					@RequestParam Map<String, Object> paramMap) {
		if(paramMap.get("key") == null && paramMap.get("query") == null) {
			Map<String, Object> map = service.selectAll(member,cp);
			model.addAttribute("map", map);
		}else {
			Map<String, Object> map = service.searchMember(paramMap,cp);
			model.addAttribute("map",map);
			
		}
		return "admin/main";
	}
	
	@GetMapping("selectMember")
	public String selectMember(String inputEmail, Model model) {
		
		Member searchMember = service.selectMember(inputEmail);
			
		if(searchMember != null) {
			model.addAttribute("searchMember", searchMember);
			return "admin/success"; 
		}
		
		return "redirect:/admin/main";
	}
	

	@PostMapping("changeAuthority")
	public String changeAuthority(int memberNo, String memberEmail, RedirectAttributes ra) {
		int result = service.changeAuthority(memberNo);
		
		if(result>0) {
			ra.addFlashAttribute("message", "변경 성공");
		}else {
			ra.addFlashAttribute("message", "변경 실패");
		}
		return "redirect:selectMember?inputEmail=" + memberEmail;
	}
			
	
	
			
}
