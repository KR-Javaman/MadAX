package com.javaman.madax.jisik.model;

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
import com.javaman.madax.common.utility.Util;
import com.javaman.madax.jisik.mapper.EditJisikMapper;



import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@PropertySource("classpath:/config.properties")
public class EditJisikServiceImpl implements EditJisikService{
	
	private final EditJisikMapper mapper;
	
	@Value("${my.board.location}")
	private String folderPath;
	
	@Value("${my.board.webpath}")
	private String webPath;
	
	@Override
	public int jisikWrite(Board board, List<MultipartFile> images) throws IllegalStateException, IOException {
		int result = mapper.jisikWrite(board);
		
		if(result == 0) return 0; 
		
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
	
	@Override
	public int jisikDelete(Map<String, Integer> paramMap) {
		// TODO Auto-generated method stub
		return mapper.jisikDelete(paramMap);
	}
	
	
	@Override
	public int jisikUpdate(Board board, List<MultipartFile> images, String deleteOrder) throws IllegalStateException, IOException {
		int result = mapper.jisikUpdate(board);
		
		if(result == 0) {
			return 0;
			
		} 
			
	List<BoardImg> uploadList = new ArrayList<>();
	
	for(int i=0; i<images.size(); i++) {
		
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
	
	@Override
	public Board jisikDetail(int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}
}
	
	
	





// ----------------------------------------------------------------------------------------------------------------------------------------
	// 게시글 작성 
//	
//	@Override
//	public Map<String, Object> jisikWrite(Map<String, Object> paramMap) {
//		
//		Map<String, Object> map = new HashMap<>();
//		return mapper.jisikWrite(paramMap);
//	}
	
//	@Override
//	public Map<String, Object> jisikWrite(Map<String, Object> paramMap) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	
//	@Override
//	public int jisikWrite(Board board, List<MultipartFile> images) {
//		int result = mapper.jisikWrite(board);
//		
//		if(result == 0) return 0;
//		
//		int boardNo = board.getBoardNo();
//		return boardNo;
//	}
	
//	@Override
//	public int jisikWrite(Board board, List<MultipartFile> images) {
//		// TODO Auto-generated method stub
//		int result = mapper.jisikWrite(board);
//		if(result == 0) return 0;
//		
//		int boardNo = board.getBoardNo();
//		return boardNo;
//	}
	
//		@Override
//		public int jisikWrite(Board board, MultipartFile images) throws IllegalStateException, IOException {
//			// TODO Auto-generated method stub
//			
//			int result = mapper.jisikWrite(board);
//			
//			if(result == 0) return 0;
//			
//			int boardNo = board.getBoardNo();
//			
//			int imageResult = mapper.jisikWrite(images);
//			
//			if(imageResult > 0) {
//				BoardImg img = new BoardImg();
//				
//				img.setBoardNo(boardNo);
//				img.setImgPath(webPath);
//				img.setUploadFile(images.get());
//			}
//			
//			for(int i = 0 ; i < images.size(); i++) {
//				if(images.get(i).getSize() > 0) {
//					BoardImg img = new BoardImg();
//					
//					img.setBoardNo(boardNo);
//					
//					img.setImgPath(webPath);
//					
//					img.setUploadFile(images.get(i));
//					
//					uploadList.add(img);
//				}
//			}
//				
//				
//					if(uploadList.isEmpty()) {
//						return boardNo;
//					}
//					
//						return boardNo;
////					result = mapper.uploadList(uploadList);
////					
////					if(result == uploadList.size()) {
////						
////						for(BoardImg img : uploadList) {
////							img.getUploadFile().transferTo(new File(folderPath + img.get));
////						}
////					}
//		}
//		

		
//		@Override
//		public int jisikWrite(Board board, MultipartFile images) throws IllegalStateException, IOException {
//			// TODO Auto-generated method stub
//			
//			Map<String, Object> map = new HashMap<>();
//			map.put("board", board);
//			map.put("images", images);
//			
//			for(int i = 0 ; i < images.getSize(); i++) {
//				if(images.get(i).getSize() > 0) {
//					BoardImg img = new BoardImg();
//					
//					img.setBoardNo(boardNo);
//					
//					img.setImgPath(webPath);
//					
//					img.setUploadFile(images.get(i));
//					
//					uploadList.add(img);
//				}
//			}
//				
//				
//					if(uploadList.isEmpty()) {
//						return boardNo;
//					}
//					
//						return boardNo;
//		}
//		
////		
////		
////		List<BoardImg> uploadList = new ArrayList<>();
////		
////		for(int i=0 ; i<images.size() ; i++) {
////			
////			// i번째 요소의 파일 크기가 0보다 크다 == 파일이 있다!
////			if(images.get(i).getSize() > 0) {
////				
////				BoardImg img = new BoardImg();
////				
////				img.setBoardNo(boardNo); // 몇 번 게시글의 이미지?
////				img.setImgOrder(i); // 몇 번째 이미지? (인덱스)
////				
////				// 원본 파일명(다운로드에서 사용)
////				img.setImgOriginalName( images.get(i).getOriginalFilename() ); 
////				
////				// 웹 접근 경로
////				img.setImgPath(webPath);
////				
////				// 변경된 파일명
////				img.setImgRename(Util.fileRename( images.get(i).getOriginalFilename() ));
////				
////				// 실제 업로드된 파일을 img에 세팅
////				img.setUploadFile(images.get(i));
////				
////				// uploadList에 추가
////				uploadList.add(img);
//				
////			} // if 끝
////			
////		} // for 끝
////		
//		
//	}
//	
//
//
