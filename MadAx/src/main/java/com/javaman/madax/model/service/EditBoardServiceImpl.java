package com.javaman.madax.model.service;

import org.springframework.stereotype.Service;

import com.javaman.madax.model.dto.Board;
import com.javaman.madax.model.mapper.EditBoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditBoardServiceImpl implements EditBoardService{

	private final EditBoardMapper mapper;
	
	@Override
	public int insertBoard(Board board) {
		
	
		int result = mapper.insertBoard(board);
		
		if(result == 0) {
			return 0;
		}
		
		int boardNo = board.getBoardNo();
		
		return boardNo;
	}
}
