package com.javaman.madax.jisik.model;



import java.util.HashMap; 
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaman.madax.board.model.dto.Board;

import com.javaman.madax.jisik.mapper.JisikMapper;
import com.javaman.madax.jisik.model.dto.JisikPagination;
import com.javaman.madax.member.model.dto.Member;


@Service
@Transactional(rollbackFor = Exception.class)

public class JisikServiceImpl implements JisikService {
	
	@Autowired
	private JisikMapper mapper;
	
	@Override
	public Map<String, Object> jisikList(Map<String, Object> paramMap, int cp) {
		int listCount = mapper.getListCount(paramMap);
		
		JisikPagination jisikPagination = new JisikPagination(cp, listCount);
		
		int offset = (jisikPagination.getCurrentPage() - 1) * jisikPagination.getLimit();
		
		int limit = jisikPagination.getLimit();
		
		RowBounds rowbounds = new RowBounds(offset, limit);
		
		List<Board> jisikList = mapper.jisikList(paramMap, rowbounds);
		
		
		// Map에 담아서 반환
		Map<String, Object> map = new HashMap<>();
		map.put("jisikList", jisikList);
		map.put("jisikPagination", jisikPagination);
		
		return map;
	}
	
//	@Override
//	public Map<String, Object> jisikDetail(Map<String, Object> paramMap, int cp, int boardNo) {
//		// TODO Auto-generated method stub
//		Map<String, Object> map = new HashMap<>();
//		map.put("boardNo", boardNo);
//		return map;
//	}
//	
	@Override
	public Board jisikDetail(int boardNo) {
		// TODO Auto-generated method stub
		return mapper.jisikDetail(boardNo);
	}
	
	@Override
	public int likeCheck(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.likeCheck(map);
	}
	
	@Override
	public int like(Map<String, Object> paramMap) {
		int result = 0;
		
		if( (Integer) (paramMap.get("check")) == 1) {
			result = mapper.deleteBoardLike(paramMap);
		} else {
			result = mapper.insertBoarLike(paramMap);
		}
		
		if(result == 0) return -1;
		
		return mapper.countBoardLike( (Integer)(paramMap.get("boardNo")));
	}
	
	@Override
	public int updateBoardCount(int boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
