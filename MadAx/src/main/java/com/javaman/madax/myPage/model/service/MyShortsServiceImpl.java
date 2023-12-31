package com.javaman.madax.myPage.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.javaman.madax.myPage.model.mapper.MyShortsMapper;
import com.javaman.madax.shorts.model.dto.Video;
import com.javaman.madax.shorts.model.dto.VideoPagination;
import com.javaman.madax.shorts.model.dto.VideoBoard;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@PropertySource("classpath:/config.properties")
public class MyShortsServiceImpl implements MyShortsService{
	private final MyShortsMapper mapper;
	
	@Value("${my.video.webpath}")
	private String webPath;
	@Value("${my.video.location}")
	private String folderPath;
	
	@Override
	public Map<String, Object> selectBoard(int memberNo, int cp) {
		int listCount = mapper.getListCount(memberNo);
		VideoPagination pagination = new VideoPagination(cp, listCount);
		 
		int offset = (pagination.getCurrentPage()-1)*pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<VideoBoard> videoList = mapper.selectBoard(memberNo, rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("videoList", videoList);
		map.put("pagination", pagination);
		
		return map;
	}
	
//	//	글 작성
//	@Override
//	public int writeInsert(VideoBoard videoBoard, List<MultipartFile> video) throws IllegalStateException, IOException {
//
//		int result = mapper.writeInsert(videoBoard);
//		
//		if(result == 0) return 0;
//	
//		List<Video> uploadVideo = new ArrayList<>();
//		for(int i= 0; i<video.size(); i++) {
//			if(video.get(i).getSize()>10485760) {
//
//				result = 0;
//			}
//
//			if(video.get(i).getSize()>0) {
//				Video vd = new Video();
//				
//				vd.setBoardVideoNo(videoBoard.getBoardVideoNo());
//				vd.setVideoOrder(i);
//				vd.setVideoPath(webPath);
//				vd.setVideoRename(Util.fileRename(video.get(i).getOriginalFilename()));
//				vd.setUploadFile(video.get(i));
//				uploadVideo.add(vd);
//				
//				mapper.videoInsert(vd);
//			}
//		}
//		if(!uploadVideo.isEmpty()) {
//			result = 1;
//			for(Video vd : uploadVideo) {
//				vd.getUploadFile().transferTo(new File(folderPath + vd.getVideoRename()));
//			}
//		}else {
//			result = 0;
//		}
//		return result;
//	}
//	
//	
//	// 글 상세 조회
//	@Override
//	public VideoBoard videoBoardDetail(int boardVideoNo) {
//		return mapper.videoBoardDetail(boardVideoNo);
//	}
//	
//	// 좋아요 
//	@Override
//	public int likeClick(Map<String, Object> map) {
//		return mapper.likeCLick(map);
//	}
//	
//	@Override
//	public int readCount(int boardVideoNo) {
//		return mapper.readCount(boardVideoNo);
//	}
//	
//	@Override
//	public int like(Map<String, Object> paramMap) {
//		
//		int result = 0;
//		
//		if((Integer)(paramMap.get("check")) == 1) {
//			result = mapper.deleteLike(paramMap);
//		}else {
//			result = mapper.insertLike(paramMap);
//		}
//		if(result == 0) {
//			return -1;
//		}
//		
//		return mapper.countLike((Integer)(paramMap.get("boardVideoNo")));
//	}
	
}
