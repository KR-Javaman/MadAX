package com.javaman.madax.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.javaman.madax.model.dto.Board;

@Mapper
public interface EditBoardMapper {

	/**게시글 작성
	 * @param board
	 * @return
	 */
	int insertBoard(Board board);

}
