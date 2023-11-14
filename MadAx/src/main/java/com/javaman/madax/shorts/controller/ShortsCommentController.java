package com.javaman.madax.shorts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.javaman.madax.member.model.dto.Member;
import com.javaman.madax.shorts.model.dto.VideoComment;
import com.javaman.madax.shorts.model.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@SessionAttributes({"loginMember"})
@RequestMapping("shorts/detail")
public class ShortsCommentController {
	
	private final CommentService service;
	
	@GetMapping(value="videoComment", produces="application/json")
	public String selectComment(int boardVideoNo, Model model,
						@SessionAttribute(value="loginMeber", required = false) Member loginMember){
		
		Map<String, Object> map = new HashMap<>();
		map.put("boardVideoNo", boardVideoNo);
		
		List<VideoComment> videoComment = service.selectComment(boardVideoNo);
		
		String path = "null";
		
		if(videoComment != null) {
			model.addAttribute("videoComment", videoComment);
			path = "shorts/shortsComment";
			
			if(loginMember != null) {
				map.put("memberNo", loginMember.getMemberNo());
				int likeClick = service.likeClick(map);
				
				if(likeClick == 1) {
					model.addAttribute("likeClickComment", "on");
				}
			}
		}
		return path;
	}
	
	
	@PostMapping("videoComment")
	public int insertComment(@RequestBody VideoComment videoComment) {
		return service.insertComment(videoComment);
	}
	
	
	@PutMapping("videoComment")
	public int updateComment(@RequestBody VideoComment videoComment) {
		return service.updateComment(videoComment);
	}
	
	
	@DeleteMapping("videoComment")
	public int deleteComment(@RequestBody int commentNo) {
		return service.deleteComment(commentNo);
	}
	
	
	@PostMapping("videoComment/like")
	public int likeComment(@RequestBody Map<String, Object> paramMap,
						@SessionAttribute("loginMember") Member loginMember) {
		
		paramMap.put("memberNo", loginMember.getMemberNo());
		
		return service.likeComment(paramMap);
	}
}
