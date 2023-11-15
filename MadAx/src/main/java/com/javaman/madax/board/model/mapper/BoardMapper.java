package com.javaman.madax.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.javaman.madax.board.model.dto.Board;

@Mapper
public interface BoardMapper {

	
	/**게시글 전체조회
	 * @param codeMap
	 * @return
	 */
	List<Board> selectBoard(Map<String, Integer> codeMap, RowBounds rowBounds);

	
	
	/**게시글 수 조회
	 * @param codeMap
	 * @return listCount
	 */
	int ListCount(Map<String, Integer> codeMap);



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


	//게시글 죄회수증가
	int updateBoardCount(int boardNo);


	/**게시글 수 조회
	 * @param board
	 * @return
	 */
	int ListCount(Board board);


	/**카테고리별 게시글 조회
	 * @param boardMap
	 * @param rowBounds
	 * @return
	 */
	List<Board> CategoryBoard(Map<String, Integer> boardMap, RowBounds rowBounds);



	
	int searchListCount(Map<String, Object> paramMap);



	/**게시글 검색
	 * @param paramMap
	 * @param rowBounds
	 * @return
	 */
	List<Board> searchBoardList(Map<String, Object> paramMap, RowBounds rowBounds);



	//정렬 테스트
	int ListCount(int boardCode);








	
}
