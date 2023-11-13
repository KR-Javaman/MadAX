package com.javaman.madax.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.javaman.madax.board.model.dto.Board;

@Mapper
public interface BoardMapper {

	
	/**게시글 조회
	 * @param boardCode
	 * @return
	 */
	List<Board> selectBoard(int boardCode, RowBounds rowBounds);

	
	
	/**게시글 수 조회
	 * @param boardCode
	 * @return listCount
	 */
	int ListCount(int boardCode);



	/**게시글 상세조회
	 * @param boardCode
	 * @return board
	 */
	Board detail(Map<String, Object> map);

	
	//좋아요 체크
	int likeCheck(Map<String, Object> map);


	//좋아요 테이블에서 행 삭제
	int deleteBoardLike(Map<String, Object> paramMap);


	//좋아요 테이블에서 행 삽입
	int insertBoardLike(Map<String, Object> paramMap);


	//현재 게시글의 좋아요 수 반환
	int countBoardLike(Integer boardNo);


	





}
