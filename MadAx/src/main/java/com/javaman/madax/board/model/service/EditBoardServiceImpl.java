package com.javaman.madax.board.model.service;


import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaman.madax.board.model.dto.Board;


import com.javaman.madax.board.model.mapper.EditBoardMapper;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditBoardServiceImpl implements EditBoardService{

	private final EditBoardMapper mapper;
	
	
	@Value("${my.board.location}")
	private String folderPath;  //서버 저장 폴더 경로
	
	@Value("${my.board.webpath}")
	private String webPath;
	
	
	//게시글 작성
	@Override
	public int insertBoard(Board board, List<MultipartFile> images) {
		
	
		int result = mapper.insertBoard(board);
		
		if(result == 0) { //삽입 실패
			
			return 0;
		}
		
		int boardNo = board.getBoardNo();  
		
		
		return boardNo;
	}
	
	
	//게시글 삭제
	@Override
	public int delete(Map<String, Integer> map) {
	
		return mapper.delete(map);
	}
}
