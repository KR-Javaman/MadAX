package com.javaman.madax.board.model.service;

import java.util.Map;

import com.javaman.madax.board.model.dto.Board;

public interface BoardService {

	
	
	//게시글 조회
	Map<String, Object> selectBoard(int boardCode, int cp);
	
	
	
	//게시글 상세조회
	Board detail(Map<String, Object> map);



	
	

}
