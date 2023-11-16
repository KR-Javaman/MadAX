package com.javaman.madax.myPage.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaman.madax.member.model.dto.Member;
import com.javaman.madax.myPage.model.service.MyShortsService;
import com.javaman.madax.shorts.model.dto.Video;
import com.javaman.madax.shorts.model.dto.VideoBoard;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("myPage")
@SessionAttributes({"loginMember"})
public class MyShortsController {

	private final MyShortsService service;
	
	@GetMapping("myPage-shorts")
	public String selectBoard(@SessionAttribute(value="loginMember", required=false /*필수는 아니다*/) Member loginMember,Model model,
				@RequestParam(value="cp", required = false, defaultValue = "1") int cp) {

		Map<String, Object> map = service.selectBoard(loginMember.getMemberNo(), cp);
		model.addAttribute("map", map);
		
		return "myPage/myPage-shorts";

	}

	
}