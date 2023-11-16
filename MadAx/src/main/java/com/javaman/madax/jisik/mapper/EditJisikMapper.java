package com.javaman.madax.jisik.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.javaman.madax.board.model.dto.Board;

@Mapper
public interface EditJisikMapper {

	int jisikWrite(Board board);

}
