package com.javaman.madax.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.board.model.dto.Pagination;
import com.javaman.madax.board.model.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardMapper mapper;
	
	@Override
	public Map<String, Object> selectBoard(int boardCode, int cp) {
		
		
		
		//게시글 수 조회
		int listCount = mapper.ListCount(boardCode);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();

		int limit = pagination.getLimit(); 
		
		
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		
		
		//게시글 조회
		List<Board> boardList = mapper.selectBoard(boardCode, rowBounds);
		
		Map<String , Object> map =  new HashMap<>();
		map.put("boardList", boardList);
		
		return map;
	}
	

}
