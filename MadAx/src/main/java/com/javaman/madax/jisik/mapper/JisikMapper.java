package com.javaman.madax.jisik.mapper;

import java.util.List;  
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.member.model.dto.Member;

@Mapper
public interface JisikMapper {


	int getListCount(Map<String, Object> paramMap);
	

	List<Board> searchJisikList(Map<String, Object> paramMap, RowBounds rowbounds);

	List<Board> selectJisikList(Board board, RowBounds rowbounds);

	int searchJisikListCount(Map<String, Object> paramMap);

	Map<String, Object> jisikMapper(Board board);

	List<Board> jisikList(Map<String, Object> paramMap, RowBounds rowbounds);


	Board jisikDetail(int boardNo);



	int deleteBoardLike(Map<String, Object> paramMap);


	int insertBoarLike(Map<String, Object> paramMap);


	int countBoardLike(Integer integer);


	int likeCheck(Map<String, Object> map);



		
	
}
