package com.javaman.madax.shorts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaman.madax.shorts.model.dto.VideoComment;
import com.javaman.madax.shorts.model.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("shorts/detail")
public class CommentController {
	
	private final CommentService service;
	
	@GetMapping(value="videoComment", produces="application/json")
	public List<VideoComment> selectComment(int boardVideoNo){
		return service.selectComment(boardVideoNo);
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
}
