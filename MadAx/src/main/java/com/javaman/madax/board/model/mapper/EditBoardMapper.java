package com.javaman.madax.board.model.mapper;

<<<<<<< HEAD
import org.apache.ibatis.annotations.Mapper; 
=======
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
>>>>>>> 63241a8f0d54fef264ea7c3d6e93def472c46f94

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.board.model.dto.BoardImg;

@Mapper
public interface EditBoardMapper {

	/**게시글 작성
	 * @param board
	 * @return
	 */
	int insertBoard(Board board);

	int uploadList(List<BoardImg> uploadList);

}
