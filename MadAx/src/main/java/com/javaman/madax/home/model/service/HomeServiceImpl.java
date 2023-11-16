package com.javaman.madax.home.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.home.model.mapper.HomeMapper;
import com.javaman.madax.shorts.model.dto.VideoBoard;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@PropertySource("classpath:/config.properties")
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
	
	private final HomeMapper mapper;
	
	@Override
	public Map<String, Object> selectCommunity(Board board, int cp) {
		
		RowBounds rowBounds = new RowBounds(0, 9);
		
		//게시글 조회
		List<Board> boardList = mapper.selectCommunity(board, rowBounds);
		
		Map<String , Object> map =  new HashMap<>();
		map.put("boardList", boardList);
		
		return map; 
		
		
	}
	
	@Override
	public Map<String, Object> selectJisikin(Board board, int cp) {
		

		RowBounds rowBounds2 = new RowBounds(0, 9);
				
		//게시글 조회
		List<Board> boardList2 = mapper.selectJisikin(board, rowBounds2);
		
		Map<String , Object> map2 =  new HashMap<>();
		map2.put("boardList2", boardList2);
		
		return map2; 
	}
	@Override
	public Map<String, Object> selectVideo(VideoBoard video, int cp) {
		RowBounds rowBounds3 = new RowBounds(0, 5);
		
		//게시글 조회
		List<Board> videoList = mapper.selectVideo(video, rowBounds3);
		
		Map<String , Object> map3 =  new HashMap<>();
		map3.put("videoList", videoList);
		
		return map3; 
	}
	

}
