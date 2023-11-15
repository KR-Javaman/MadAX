package com.javaman.madax.myPage.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.javaman.madax.board.model.dto.Board;

@Mapper
public interface MyCommunityMapper {

	int ListCount(int memberNo);


	List<Board> selectBoard(int memberNo, RowBounds rowBounds);

}
