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




}
