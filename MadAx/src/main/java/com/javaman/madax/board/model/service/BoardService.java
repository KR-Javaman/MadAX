package com.javaman.madax.board.model.service;

import java.util.Map;

import com.javaman.madax.board.model.dto.Board;

public interface BoardService {

	
	
	//게시글 전체 조회
	Map<String, Object> selectBoard(int boardCode, int cp);
	
	
	
	
	//게시글 상세조회
	Board detail(Map<String, Object> map);


	//게시글 좋아요 여부 확인
	int likeCheck(Map<String, Object> map);


	//게시글 좋아요 처리
	int like(Map<String, Object> map);


	//게시글 조회수
	int updateBoardCount(int boardNo);

	
	

	/**카테고리별 게시글 조회
	 * @param boardCode
	 * @param categoryCode
	 * @param categoryCodeTwo
	 * @param cp
	 * @return
	 */
	Map<String, Object> CategoryBoard(int boardCode, int categoryCode, int categoryCodeTwo, int cp);




	Map<String, Object> searchBoardList(Map<String, Object> paramMap, int cp);




	














	


	
	





	
	

}
