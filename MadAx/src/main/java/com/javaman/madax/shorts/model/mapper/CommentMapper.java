package com.javaman.madax.shorts.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.javaman.madax.shorts.model.dto.VideoComment;

@Mapper
public interface CommentMapper {

	List<VideoComment> selectComment(int boardVideoNo);

	int insertComment(VideoComment videoComment);

	int updateComment(VideoComment videoComment);

	int deleteComment(int commentNo);

	int likeClick(Map<String, Object> map);

	int deleteCommentLike(Map<String, Object> paramMap);

	int insertCommentLike(Map<String, Object> paramMap);

	int countCommentLike(Integer commentNo);

}
