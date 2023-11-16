package com.javaman.madax.jisik.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.board.model.dto.BoardImg;

@Mapper
public interface EditJisikMapper {

	

	

	int jisikWrite(Board board);

	int jisikWrite(MultipartFile image);

	int uploadList(List<BoardImg> uploadList);

	int insertUploadList(List<BoardImg> uploadList);

	int jisikDelete(Map<String, Integer> paramMap);

}
