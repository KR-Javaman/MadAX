package com.javaman.madax.jisik.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.jisik.mapper.EditJisikMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@PropertySource("classpath:/config.properties")
public class EditJisikServiceImpl implements EditJisikService{
	
	private final EditJisikMapper mapper;
	
	@Value("${my.jisik.location}")
	private String folderPath;
	
	@Value("${my.jisik.webpath}")
	private String webPath;
	
	
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
	
	@Override
	public int jisikWrite(Board board) {
		// TODO Auto-generated method stub
		return mapper.jisikWrite(board);
	}
	
	
}

