package com.javaman.madax.shorts.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaman.madax.shorts.model.dto.VideoComment;
import com.javaman.madax.shorts.model.mapper.ShortsCommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ShortsCommentServiceImpl implements ShortsCommentService{

	private final ShortsCommentMapper mapper;
	
	@Override
	public List<VideoComment> selectComment(int boardVideoNo) {
		return mapper.selectComment(boardVideoNo);
	}
	
	@Override
	public int[] selectCommentNo(int boardVideoNo) {
		return mapper.selectCommentNo(boardVideoNo);
	}
	
	@Override
	public int insertComment(VideoComment videoComment) {
		return mapper.insertComment(videoComment);
	}
	
	
	@Override
	public int updateComment(VideoComment videoComment) {
		return mapper.updateComment(videoComment);
	}
	
	
	@Override
	public int deleteComment(int commentNo) {
		return mapper.deleteComment(commentNo);
	}
	
	
	
	@Override
	public int likeClick(Map<String, Object> map) {
		return mapper.likeClick(map);
	}
	
	
	@Override
	public int likeComment(Map<String, Object> paramMap) {

		int result = 0;
		
		if((Integer)(paramMap.get("check")) == 1) {
			result = mapper.deleteCommentLike(paramMap);
		}else {
			result = mapper.insertCommentLike(paramMap);
		}
		if(result == 0) return -1;
		
		return mapper.countCommentLike(String.valueOf(paramMap.get("commentNo")));
	}
}
