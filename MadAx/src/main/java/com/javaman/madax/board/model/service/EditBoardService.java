package com.javaman.madax.board.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.javaman.madax.board.model.dto.Board;

public interface EditBoardService {

	
	
	/**게시글 작성
	 * @param board
	 * @return
	 */
	int insertBoard(Board board, List<MultipartFile> images) throws IllegalStateException, IOException;


	

}
