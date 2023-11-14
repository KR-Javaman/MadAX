package com.javaman.madax.board.model.mapper;


<<<<<<< HEAD
import org.apache.ibatis.annotations.Mapper;  

import java.util.List;
=======
import java.util.Map;
>>>>>>> 7fcfe7d9c6944bfbe345775bbb4dc0e9403dd23f

import org.apache.ibatis.annotations.Mapper;

import com.javaman.madax.board.model.dto.Board;

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

}
