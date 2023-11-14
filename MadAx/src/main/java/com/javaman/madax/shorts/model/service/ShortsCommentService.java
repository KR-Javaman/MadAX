package com.javaman.madax.shorts.model.service;

import java.util.List;
import java.util.Map;

import com.javaman.madax.shorts.model.dto.VideoComment;

public interface ShortsCommentService {

	List<VideoComment> selectComment(int boardVideoNo);

	int insertComment(VideoComment videoComment);

	int updateComment(VideoComment videoComment);

	int deleteComment(int commentNo);

	int likeClick(Map<String, Object> map);

	int likeComment(Map<String, Object> paramMap);

}
