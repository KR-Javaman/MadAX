package com.javaman.madax.shorts.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaman.madax.shorts.model.dto.Video;
import com.javaman.madax.shorts.model.dto.VideoPagination;
import com.javaman.madax.shorts.model.dto.VideoBoard;
import com.javaman.madax.shorts.model.mapper.ShortsMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ShortsServiceImpl implements ShortsService{
	private final ShortsMapper mapper;
	
	@Override
	public Map<String, Object> main(int cp,VideoBoard video) {
		int listCount = mapper.getListCount(video);
		VideoPagination pagination = new VideoPagination(cp, listCount);
		 
		int offset = (pagination.getCurrentPage()-1)*pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<VideoBoard> videoList = mapper.selectList(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("videoList", videoList);
		map.put("pagination", pagination);
		
		return map;
	}
	
	
}
