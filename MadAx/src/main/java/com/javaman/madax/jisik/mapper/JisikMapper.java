package com.javaman.madax.jisik.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaman.madax.board.model.dto.Board;

@Mapper
public interface JisikMapper {

	List<Board> JisikList();
	
	
}
