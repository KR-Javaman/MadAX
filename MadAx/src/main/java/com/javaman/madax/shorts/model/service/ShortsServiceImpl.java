package com.javaman.madax.shorts.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaman.madax.shorts.model.dto.Video;
import com.javaman.madax.shorts.model.mapper.ShortsMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ShortsServiceImpl implements ShortsService{
	private final ShortsMapper mapper;
	
	@Override
	public Map<String, Object> main(int boardCode, int cp) {
//		int listCount = mapper.getListCount()
		
		 
		
		List<Video> videoList = mapper.selectVideoList(boardCode);
		
		return null;
	}
}
