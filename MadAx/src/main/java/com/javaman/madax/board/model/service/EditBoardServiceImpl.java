package com.javaman.madax.board.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.board.model.mapper.EditBoardMapper;

import com.javaman.madax.board.model.dto.BoardImg;
import com.javaman.madax.board.model.exception.BoardWriteException;
import com.javaman.madax.common.utility.Util;
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
	public int insertBoard(Board board, List<MultipartFile> images) throws IllegalStateException, IOException {
		
	
		int result = mapper.insertBoard(board);
		
		if(result == 0) { //삽입 실패
			
			return 0;
		}
		
		int boardNo = board.getBoardNo();  //삽입 성공
		
		
//		(uploadList에 모인 데이터를 가지고 DB INSERT)
		List<BoardImg> uploadList = new ArrayList<>();

		//images에서 업로드된 파일 선별하기
		for(int i = 0; i<images.size(); i++) {
			
			//i번째 요소의 파일 크기가 0보다 크다(파일이 있다)
			if(images.get(i).getSize() > 0) {
			
				BoardImg img = new BoardImg();
				
				img.setBoardNo(boardNo); //몇 번 게시글의 이미지?
				img.setImgOrder(i); 	 //몇 번째 이미지?(인덱스)
				
				//원본 파일명(다운로드에서 사용)
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

		//images에서 업로드된 파일을 선별했으나 아무것도 없을 때
		if(uploadList.isEmpty()) {
			return boardNo;
		}

		//images에 실제로 업로드된 이미지가 있을 때
		//uploadList 를 통째로 mapper로 전달해 일괄 삽입
		result = mapper.uploadList(uploadList);

		//전달된 데이터가 모두 삽입 되었을 경우
		if(result == uploadList.size()) {
			
			//업로드된 이미지를 서버(folderPath)에 저장
			for(BoardImg img : uploadList) {
				img.getUploadFile().transferTo(new File(folderPath + img.getImgRename()));
			}
		}else { //삽입이 일부 안되었을 경우
			//하나라도 실패하면 전체 롤백
			//-> 롤백하는 방법 == 예외 발생 롤백
			//-->강제 예외 발생(사용자 정의 예외)
			throw new BoardWriteException("DB 삽입 실패");
		}

		
		return boardNo;
	}
}
