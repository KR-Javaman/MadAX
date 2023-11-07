package com.javaman.madax.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.javaman.madax.model.dto.Board;

@Mapper
public interface EditBoardMapper {

	int insertBoard(Board board);

}
