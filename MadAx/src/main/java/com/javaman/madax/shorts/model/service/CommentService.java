package com.javaman.madax.shorts.model.service;

import java.util.List;

import com.javaman.madax.shorts.model.dto.VideoComment;

public interface CommentService {

	List<VideoComment> selectComment(int boardVideoNo);

	int insertComment(VideoComment videoComment);

	int updateComment(VideoComment videoComment);

	int deleteComment(int commentNo);

}
