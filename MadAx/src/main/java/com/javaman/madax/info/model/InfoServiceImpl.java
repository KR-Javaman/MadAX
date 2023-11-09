package com.javaman.madax.info.model;


import java.util.Map;

import org.springframework.stereotype.Service;

import com.javaman.madax.info.mapper.InfoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService{
	
	
	private final InfoMapper mapper;
	
	@Override
	public Map<String, Object> selectBoardList(int boardCode, int cp) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<String, Object> searchBoardList(Map<String, Object> paramMap, int cp) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
