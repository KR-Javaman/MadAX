package com.javaman.madax.board.model.service;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.board.model.dto.BoardImg;
import com.javaman.madax.board.model.exception.BoardWriteException;
import com.javaman.madax.board.model.mapper.EditBoardMapper;
import com.javaman.madax.common.utility.Util;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@PropertySource("classpath:/config.properties") 
public class EditBoardServiceImpl implements EditBoardService{

	private final EditBoardMapper mapper;
	
	
	@Value("${my.board.location}")
	private String folderPath;  //서버 저장 폴더 경로
	
	@Value("${my.board.webpath}")
	private String webPath;
	
	
	//게시글 작성
	@Override
	public int insertBoard(Board board, List<MultipartFile> images) throws IllegalStateException, IOException {
		
	
		int result = mapper.insertBoard(board);
		
		if(result == 0) { //삽입 실패
			
			return 0;
		}
		
		int boardNo = board.getBoardNo();  
		
		
				List<BoardImg> uploadList = new ArrayList<>();
				
				
				for(int i = 0; i<images.size(); i++) {
					
					
					if(images.get(i).getSize() > 0) {
						
						BoardImg img = new BoardImg();
						
						img.setBoardNo(boardNo); 
						img.setImgOrder(i); 	
						img.setImgOriginalName(images.get(i).getOriginalFilename()); 
						img.setImgPath(webPath);
						img.setImgRename(Util.fileRename(images.get(i).getOriginalFilename())); 
						img.setUploadFile(images.get(i));
						
						uploadList.add(img);
					}
				}
				
				
				if(uploadList.isEmpty()) {
					return boardNo;
				}
				
				
				result = mapper.insertUploadList(uploadList);
				
				
				if(result == uploadList.size()) {
					
					for(BoardImg img : uploadList) {
						img.getUploadFile().transferTo(new File(folderPath + img.getImgRename()));
					}
				}else { 
					
					throw new BoardWriteException("파일 정보 DB 삽입 실패");
				}
				
				return boardNo;
			}
		
		
	
	//게시글 삭제
	@Override
	public int delete(Map<String, Integer> map) {
	
		return mapper.delete(map);
	}
}
