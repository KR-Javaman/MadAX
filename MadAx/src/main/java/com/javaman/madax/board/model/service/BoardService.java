package com.javaman.madax.board.model.service;

import java.util.Map;

import com.javaman.madax.board.model.dto.Board;

public interface BoardService {

	
	
	//게시글 조회
	Map<String, Object> selectBoard(int boardCode, int cp);
	
	
	
	//게시글 상세조회
	Board detail(Map<String, Object> map);


	//게시글 좋아요 여부 확인
	int likeCheck(Map<String, Object> map);


	
	int like(Map<String, Object> paramMap);






	
	

}
