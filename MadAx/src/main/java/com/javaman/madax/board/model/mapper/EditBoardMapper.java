package com.javaman.madax.board.model.mapper;




import org.apache.ibatis.annotations.Mapper;   

import java.util.List;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.board.model.dto.BoardImg;

@Mapper
public interface EditBoardMapper {

	/**게시글 작성
	 * @param board
	 * @return
	 */
	int insertBoard(Board board);

	/**게시글 삭제
	 * @param map
	 * @return
	 */
	int delete(Map<String, Integer> map);

	/** 이미지 일괄 삽입
	 * @param uploadList
	 * @return
	 */
	int insertUploadList(List<BoardImg> uploadList);


	
	/**게시글 수정
	 * @param board
	 * @return
	 */
	int updateBoard(Board board);



	int imageDelete(Map<String, Object> map);



	int updateBoardImg(BoardImg img);



	void boardImgInsert(BoardImg img);





}
