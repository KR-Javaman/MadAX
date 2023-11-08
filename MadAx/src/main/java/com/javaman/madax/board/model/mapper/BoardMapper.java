package com.javaman.madax.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaman.madax.board.model.dto.Board;

@Mapper
public interface BoardMapper {

	List<Board> selectBoard(int boardCode);

}
