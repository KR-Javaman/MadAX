package com.javaman.madax.jisik.model;

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
	public Map<String, Object> JisikList(int boardCode) {
		// TODO Auto-generated method stub
		return mapper.JisikList(boardCode);
	}
	
}
