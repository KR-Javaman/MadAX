package com.javaman.madax.myPage.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.board.model.dto.MyPagePagination;
import com.javaman.madax.myPage.model.mapper.MyCommunityMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MyCommunityServiceImpl implements MyCommunityService {
	
	private final MyCommunityMapper mapper;
	
	@Override
	public Map<String, Object> selectBoard(int memberNo, int cp) {
		
		//게시글 수 조회
				int listCount = mapper.ListCount(memberNo);
				
				MyPagePagination pagination = new MyPagePagination(cp, listCount);
				
				
				int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
								//현재 페이지에서 1을 빼고 한 페이지 목록에 보여지는 게시글 수를 곱함(결과 만큼을 건너뛰고 조회)
				int limit = pagination.getLimit(); 
				
				
				RowBounds rowBounds = new RowBounds(offset,limit);
				
				
				
				//게시글 조회
				List<Board> boardList = mapper.selectBoard(memberNo, rowBounds);
				
				Map<String , Object> map =  new HashMap<>();
				map.put("boardList", boardList);
				map.put("pagination", pagination);
				
				return map;
		
	
	}
	
	
	
	
	
	
	
	
	

}
