package com.javaman.madax.jisik.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.jisik.mapper.JisikMapper;

@Service
public class JisikServiceImpl implements JisikService {
	
	@Autowired
	private JisikMapper mapper;
	
	@Override
	public Map<String, Object> jisikList(Board board) {
		
		List<Board> jisikList =  mapper.jisickList(board);
		
		Map<String, Object> map = new HashMap<>();
		map.put("jisikList", jisikList);
		
		return map;
	}
	
	
}
