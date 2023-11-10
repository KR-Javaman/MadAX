package com.javaman.madax.jisik.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.javaman.madax.board.model.dto.Board;

@Mapper
public interface JisikMapper {


	Map<String, Object> JisikList(int boardCode);
	
	
}
