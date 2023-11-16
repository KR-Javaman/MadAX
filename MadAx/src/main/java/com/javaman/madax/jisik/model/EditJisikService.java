package com.javaman.madax.jisik.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.javaman.madax.board.model.dto.Board;

public interface EditJisikService {

	int jisikWrite(Board board, List<MultipartFile> images) throws IllegalStateException, IOException;

	int jisikDelete(Map<String, Integer> paramMap);

	

//	int jisikWrite(Board board, List<MultipartFile> images);
//
//	Map<String, Object> jisikWrite(Map<String, Object> paramMap);
		

	

	

	

}
