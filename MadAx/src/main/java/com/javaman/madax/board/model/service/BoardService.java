package com.javaman.madax.board.model.service;

import java.util.Map;

import com.javaman.madax.board.model.dto.Board;

public interface BoardService {

	
	
	//게시글 전체 조회
	Map<String, Object> selectBoard(Map<String, Integer> code, int cp);
	
	
	
	
	//게시글 상세조회
	Board detail(Map<String, Object> map);


	//게시글 좋아요 여부 확인
	int likeCheck(Map<String, Object> map);


	//게시글 좋아요 처리
	int like(Map<String, Object> map);


	//게시글 조회수
	int updateBoardCount(int boardNo);

	



	//게시글 검색
	Map<String, Object> searchBoardList(Map<String, Object> paramMap, int cp);



	//카테고리별 게시글 검색
	Map<String, Object> CategoryBoard(Map<String, Integer> boardMap, int cp);




















	



	


	
	





	
	

}
