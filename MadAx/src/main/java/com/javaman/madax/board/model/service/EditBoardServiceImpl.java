package com.javaman.madax.board.model.service;

import org.springframework.stereotype.Service;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.board.model.mapper.EditBoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditBoardServiceImpl implements EditBoardService{

	private final EditBoardMapper mapper;
	
	
	//게시글 작성
	@Override
	public int insertBoard(Board board) {
		
	
		int result = mapper.insertBoard(board);
		
		if(result == 0) { //삽입 실패
			
			return 0;
		}
		
		int boardNo = board.getBoardNo();  //삽입 성공
		
		return boardNo;
	}
}
