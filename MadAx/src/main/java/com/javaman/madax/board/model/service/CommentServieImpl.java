package com.javaman.madax.board.model.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.javaman.madax.board.model.dto.Comment;
import com.javaman.madax.board.model.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServieImpl implements CommentService{

	private final CommentMapper mapper;
	
	//댓글 목록 조회
	@Override
	public List<Comment> select(int boardNo) {
		
		return mapper.select(boardNo);
	}
	
	
	//댓글 등록
	@Override
	public int Insert(Comment comment) {
		
		return mapper.Insert(comment);
	}
	
	//댓글 수정
	@Override
	public int update(Comment comment) {
		
		return mapper.update(comment);
	}
	
	//댓글 삭제
	@Override
	public int delete(int commentNo) {
		
		return mapper.delete(commentNo);
	}
	
	
	
	
	
}
