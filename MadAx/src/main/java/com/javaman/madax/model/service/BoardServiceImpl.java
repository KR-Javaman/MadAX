package com.javaman.madax.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.javaman.madax.model.dto.Board;
import com.javaman.madax.model.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardMapper mapper;
	
	@Override
	public Map<String, Object> selectBoard(int boardCode) {
		
		List<Board> boardList = mapper.selectBoard(boardCode);
		
		Map<String , Object> map =  new HashMap<>();
		map.put("boardList", boardList);
		
		return map;
	}
	

}
