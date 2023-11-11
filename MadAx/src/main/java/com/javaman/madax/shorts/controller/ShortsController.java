package com.javaman.madax.shorts.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaman.madax.member.model.dto.Member;
import com.javaman.madax.shorts.model.dto.Video;
import com.javaman.madax.shorts.model.dto.VideoBoard;
import com.javaman.madax.shorts.model.service.ShortsService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("shorts")
@SessionAttributes({"loginMember"})
public class ShortsController {

	private final ShortsService service;
	
	@GetMapping("main")
	public String main(VideoBoard video ,Model model,
				@RequestParam(value="cp", required = false, defaultValue = "1") int cp) {

		Map<String, Object> map = service.main(cp,video);
		model.addAttribute("map", map);
		
		return "shorts/shorts-main";

	}
	
	// 글쓰기 페이지
	@GetMapping("edit/insert")
	public String writeInsert(@SessionAttribute(value="loginMember", required=false) Member loginMember) {
		if(loginMember == null) return "redirect:/shorts/main";
		
		return "shorts/shortsWrite";
	}
	// 업로드
	@PostMapping("edit/insert")
	public String writeInsert(@SessionAttribute("loginMember") Member loginMember, VideoBoard videoBoard,
							@RequestParam("shortsVideo") List<MultipartFile> video,
							RedirectAttributes ra) throws IllegalStateException, IOException {
		
		videoBoard.setMemberNo(loginMember.getMemberNo());
		
		int result = service.writeInsert(videoBoard,video);
		
		if(result>0) {
			ra.addFlashAttribute("message", "글 작성 완료");
			return "redirect:/shorts/main";
		}else {
			ra.addFlashAttribute("message", "글 작성 실패");
			return "redirect:/shorts/edit/insert";
		}
	}
	
	
	// 상세 조회
	@GetMapping("detail/{boardVideoNo:[0-9]+}")
	public String videoBoardDetail(@PathVariable("boardVideoNo") int boardVideoNo,
								@SessionAttribute(value="loginMember",required = false) Member loginMember,
								Model model,HttpServletRequest req, HttpServletResponse resp,
								RedirectAttributes ra) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		map.put("boardVideoNo", boardVideoNo);
		
		VideoBoard videoBoard = service.videoBoardDetail(map);
		
		String Path = null;
		
		if(videoBoard != null) {
			model.addAttribute("videoBoard", videoBoard);
			Path = "shorts/shortsDetail";
			
			if(loginMember != null) {
				map.put("memberNo", loginMember.getMemberNo());
				int likeClick = service.likeClick(map);
				
				//
				if(likeClick == 1) {
					model.addAttribute("likeClick", "on");
				}
			}
		if(loginMember == null || loginMember.getMemberNo() != videoBoard.getMemberNo()) {
			Cookie c = null;
			Cookie[] cookies = req.getCookies();
			
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					if(cookie.getName().equals("readBoardNo")) {
						c = cookie;
						break;
					}
				}
			}
			
			int result = 0;
			if(c == null) {
				c = new Cookie("readBoardNo", "|" + boardVideoNo + "|");
				result = service.readCount(boardVideoNo);
			}else {
				if(c.getValue().indexOf("|"+boardVideoNo+"|") == -1) {
					c.setValue(c.getValue()+"|"+boardVideoNo+"|");
					result = service.readCount(boardVideoNo);
				}
			}
			if(result > 0) {
				videoBoard.setVideoReadCount(videoBoard.getVideoReadCount() + 1);
				c.setPath("/");
				
				Calendar cal = Calendar.getInstance();
				cal.add(cal.DATE, 1);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				Date d = new Date();
				Date temp = new Date(cal.getTimeInMillis());
				Date a = sdf.parse(sdf.format(temp));
				
				long diff = (a.getTime() - d.getTime()) / 1000;
				
				c.setMaxAge((int)diff);
				
				resp.addCookie(c);
			}
			
		}
	}else { // 게시글이 없을 경우
		Path = "redirect:/shorts/main";
		
		ra.addFlashAttribute("message", "해당 게시글이 존재하지 않습니다");
		}
		
	return Path;
	}
}