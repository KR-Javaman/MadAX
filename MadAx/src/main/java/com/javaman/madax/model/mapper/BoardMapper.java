package com.javaman.madax.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaman.madax.model.dto.Board;

@Mapper
public interface BoardMapper {

	List<Board> selectBoard(int boardCode);

}
