package com.javaman.madax.shorts.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.javaman.madax.shorts.model.dto.Video;
import com.javaman.madax.shorts.model.dto.VideoBoard;

public interface ShortsService {

	Map<String, Object> main(int cp, VideoBoard video);

	int writeInsert(VideoBoard videoBoard, List<MultipartFile> video) throws IllegalStateException, IOException;

	VideoBoard videoBoardDetail(int boardVideoNo);

	int likeClick(Map<String, Object> map);

	int readCount(int boardVideoNo);

	int like(Map<String, Object> paramMap);

	int deleteBoard(Map<String, Object> paramMap);

	int updateBoard(VideoBoard videoBoard, List<MultipartFile> video) throws IllegalStateException, IOException;

	Map<String, Object> searchMain(Map<String, Object> paramMap, int cp);

	



}
