package com.javaman.madax.home.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.shorts.model.dto.VideoBoard;

@Mapper
public interface HomeMapper {

	List<Board> selectCommunity(Board board, RowBounds rowBounds);

	List<Board> selectJisikin(Board board, RowBounds rowBounds2);

	List<Board> selectVideo(VideoBoard video, RowBounds rowBounds3);

}
