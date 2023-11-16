package com.javaman.madax.board.model.service;


import java.io.File; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.javaman.madax.board.model.exception.BoardUpdateException;
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
			
			//1. 게시글(BOARD 테이블) 부분만 먼저 INSERT
			
			//1)게시글 삽입 mapper메서드 호출 후 결과 반환 받기
			int result = mapper.insertBoard(board);
			
			if(result == 0) return 0; //삽입 실패
	
			int boardNo = board.getBoardNo();
			
			List<BoardImg> uploadList = new ArrayList<>();
			
			
			for(int i = 0; i<images.size(); i++) {
				
				
				if(images.get(i).getSize() > 0) {
					
					BoardImg img = new BoardImg();
					
					img.setBoardNo(boardNo); 
					img.setImgOrder(i); 	 
					
					
					img.setImgOriginalName(images.get(i).getOriginalFilename()); 
					
					//웹 접근 경로
					img.setImgPath(webPath);
				
					//변경된 파일명
					img.setImgRename(Util.fileRename(images.get(i).getOriginalFilename())); 
					
					//실제 업로드된 파일을 img에 세팅
					img.setUploadFile(images.get(i));
					
					//uploadList에 추가
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
	
	
	//게시글 수정
	@Override
	public int updateBoard(Board board, List<MultipartFile> images) throws IllegalStateException, IOException{
		
		//1. 게시글 수정(제목, 내용)수정
		int result = mapper.updateBoard(board);
		
		//수정 실패 시
		if(result == 0) {
			
			return 0;
		}
		
		
		
		List<BoardImg> uploadList = new ArrayList<>();
		
	
		for(int i = 0; i<images.size(); i++) {
		
			if(images.get(i).getSize() > 0) {
				
				BoardImg img = new BoardImg();
				
				img.setBoardNo(board.getBoardNo()); 
				img.setImgOrder(i); 	
				img.setImgOriginalName(images.get(i).getOriginalFilename()); 
				img.setImgPath(webPath);
				img.setImgRename(Util.fileRename(images.get(i).getOriginalFilename())); 
				img.setUploadFile(images.get(i));
				uploadList.add(img);
				
				result = mapper.updateBoardImg(img);
				
				if(result == 0) {
					mapper.boardImgInsert(img);
				}
			}
		}
		
		if(!uploadList.isEmpty()) {
			result = 1;
			
			for(BoardImg img : uploadList) {
				img.getUploadFile().transferTo(new File(folderPath + img.getImgRename() ) );
			}
		}
		
		return result;
		
	}
}
