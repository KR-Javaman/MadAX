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
	public Map<String, Object> selectBoard(Map<String, Integer> codeMap, int cp) {
		
		
		
		//게시글 수 조회
		int listCount = mapper.ListCount(codeMap);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
						//현재 페이지에서 1을 빼고 한 페이지 목록에 보여지는 게시글 수를 곱함(결과 만큼을 건너뛰고 조회)
		int limit = pagination.getLimit(); 
		
		
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		
		
		//게시글 조회
		List<Board> boardList = mapper.selectBoard(codeMap, rowBounds);
		
		Map<String , Object> map =  new HashMap<>();
		map.put("boardList", boardList);
		map.put("pagination", pagination);
		
		return map;
	}
	
	
	
	
	
	
		//게시글 상세 조회
		@Override
		public Board detail(Map<String, Object> map) {
			
			
			return mapper.detail(map);
		}
		
		
		//게시글 좋아요 여부 확인
		@Override
		public int likeCheck(Map<String, Object> map) {
			
			return mapper.likeCheck(map);
		}
		
		
		//좋아요 처리
		@Override
		public int like(Map<String, Object> map) {
			
			int result = 0; //Mapper 호출 결과를 저장 변수
			
		
			if( (Integer) (map.get("check")) == 1 ) {
				result = mapper.deleteBoardLike(map);
				
			}else {
			
				result = mapper.insertBoardLike(map);
			}
			
			 
			//SQL 수행 결과가 0 == 파라미터에 문제 있음
			if(result == 0) {
				return -1;
			}
			
			//SQL 성공 시 
			//-> 현재 게시글의 좋아요 수를 조회해서 반환
			return mapper.countBoardLike((Integer) map.get("boardNo") );
			
			
		}
	
	
	@Override
	public int updateBoardCount(int boardNo) {
	
		return mapper.updateBoardCount(boardNo);
	}
	
	
	
	//게시글 검색
	@Override
	public Map<String, Object> searchBoardList(Map<String, Object> paramMap, int cp) {
		
		int listCount = mapper.searchListCount(paramMap);

	
		Pagination pagination = new Pagination(cp, listCount);

		
		int offset = (pagination.getCurrentPage() - 1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);


		List<Board> boardList = mapper.searchBoardList(paramMap, rowBounds);

		Map<String, Object> map = new HashMap<>();
		map.put("boardList", boardList);
		map.put("pagination", pagination);

		return map;
	}
	


	
	
	

	
	
	

	
	
	
	
	
	
	
	

}
