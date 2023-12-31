package com.javaman.madax.shorts.model.service;

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

import com.javaman.madax.board.model.exception.BoardWriteException;
import com.javaman.madax.common.utility.Util;
import com.javaman.madax.shorts.model.dto.Video;
import com.javaman.madax.shorts.model.dto.VideoPagination;
import com.javaman.madax.shorts.model.dto.VideoBoard;
import com.javaman.madax.shorts.model.mapper.ShortsMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@PropertySource("classpath:/config.properties")
public class ShortsServiceImpl implements ShortsService{
	private final ShortsMapper mapper;
	
	@Value("${my.video.webpath}")
	private String webPath;
	@Value("${my.video.location}")
	private String folderPath;
	
	// 목록 조회
	@Override
	public Map<String, Object> main(int cp,VideoBoard video) {
		int listCount = mapper.getListCount(video);
		VideoPagination pagination = new VideoPagination(cp, listCount);
		 
		int offset = (pagination.getCurrentPage()-1)*pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<VideoBoard> videoList = mapper.selectList(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("videoList", videoList);
		map.put("pagination", pagination);
		
		return map;
	}
	
	// 검색 목록 조회
	@Override
	public Map<String, Object> searchMain(Map<String, Object> paramMap, int cp) {
		int listCount = mapper.searchCount(paramMap);

		VideoPagination pagination = new VideoPagination(cp, listCount);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<VideoBoard> videoList = mapper.searchMain(rowBounds, paramMap);
		
		Map<String, Object> map = new HashMap<>();
		map.put("videoList", videoList);
		map.put("pagination", pagination);
		return map;
	}
	
	
		//글 작성
	@Override
	public int writeInsert(VideoBoard videoBoard, List<MultipartFile> video) throws IllegalStateException, IOException {

		
		
		int result = mapper.writeInsert(videoBoard);
		
		if(result == 0) return 0;
		int boardVideoNo = videoBoard.getBoardVideoNo();
		
		
		if(video.isEmpty() && video == null) {
			return 0;
		}
		List<Video> uploadVideo = new ArrayList<>();
		for(int i = 0; i<video.size(); i++) {
			
			if(!video.isEmpty() ) {
					Video vd = new Video();
					
					vd.setBoardVideoNo(videoBoard.getBoardVideoNo());
					vd.setVideoOrder(i);
					vd.setVideoPath(webPath);
					vd.setVideoRename(Util.fileRename(video.get(i).getOriginalFilename()));
					vd.setUploadFile(video.get(i));
					uploadVideo.add(vd);
					
					mapper.videoInsert(vd);
					vd.getUploadFile().transferTo(new File(folderPath + vd.getVideoRename()));
			}
			
		}
		return result;
	}
	
//	@Override
//	public int writeInsert(VideoBoard videoBoard, List<MultipartFile> video) throws IllegalStateException, IOException {
//	    int result = mapper.writeInsert(videoBoard);
//
//	    if (result == 0) {
//	        return 0;
//	    }
//
//	    int boardVideoNo = videoBoard.getBoardVideoNo();
//
//	    if (video == null || video.isEmpty()) {
//	        return 0; // No file selected, return 0.
//	    }
//
//	    List<Video> uploadVideo = new ArrayList<>();
//
//	    for (int i = 0; i < video.size(); i++) {
//	        try {
//	            MultipartFile file = video.get(i);
//	            if (file != null && !file.isEmpty()) {
//	                Video vd = new Video();
//	                vd.setBoardVideoNo(videoBoard.getBoardVideoNo());
//	                vd.setVideoOrder(i);
//	                vd.setVideoPath(webPath);
//
//	                // Assuming Util.fileRename is a method that safely renames the file
//	                String originalFilename = file.getOriginalFilename();
//	                String renamedFilename = Util.fileRename(originalFilename);
//	                vd.setVideoRename(renamedFilename);
//
//	                vd.setUploadFile(file);
//	                uploadVideo.add(vd);
//
//	                mapper.videoInsert(vd);
//
//	                // Transfer the file to the specified folder
//	                file.transferTo(new File(folderPath + renamedFilename));
//	            }
//	        } catch (IllegalStateException | IOException e) {
//	            // Handle the exception, log it, or take appropriate action.
//	            e.printStackTrace(); // or log.error("File upload failed", e);
//	            // You might want to remove the Video object from uploadVideo if the upload fails.
//	        }
//	    }
//
//	    return boardVideoNo;
//	}



	
	
	
	
	// 글 상세 조회
	@Override
	public VideoBoard videoBoardDetail(int boardVideoNo) {
		return mapper.videoBoardDetail(boardVideoNo);
	}
	
	// 좋아요 여부 확인
	@Override
	public int likeClick(Map<String, Object> map) {
		return mapper.likeCLick(map);
	}
	
	@Override
	public int readCount(int boardVideoNo) {
		return mapper.readCount(boardVideoNo);
	}
	
	@Override
	public int like(Map<String, Object> paramMap) {
		
		int result = 0;
		
		if((Integer)(paramMap.get("check")) == 1) {
			result = mapper.deleteLike(paramMap);
		}else {
			result = mapper.insertLike(paramMap);
		}
		if(result == 0) {
			return -1;
		}
		
		return mapper.countLike((Integer)(paramMap.get("boardVideoNo")));
	}
	
	// 삭제 화면 
	@Override
	public int deleteBoard(Map<String, Object> paramMap) {
		return mapper.deleteBoard(paramMap);
	}
	
	
	// 삭제 요청
	@Override
	public int updateBoard(VideoBoard videoBoard, List<MultipartFile> video) throws IllegalStateException, IOException {
		
		int result = mapper.updateBoard(videoBoard);
		
		if(result == 0) {
			return 0;
		}
		List<Video> uploadVideo = new ArrayList<>();
		for(int i= 0; i<video.size(); i++) {
			if(video.get(i).getSize()>10485760) {

				result = 0;
			}

			if(video.get(i).getSize()>0) {
				Video vd = new Video();
				
				vd.setBoardVideoNo(videoBoard.getBoardVideoNo());
				vd.setVideoOrder(i);
				vd.setVideoPath(webPath);
				vd.setVideoRename(Util.fileRename(video.get(i).getOriginalFilename()));
				vd.setUploadFile(video.get(i));
				uploadVideo.add(vd);
				
				result = mapper.videoUpdate(vd);
			}
		}
		if(!uploadVideo.isEmpty()) {
			result = 1;
			for(Video vd : uploadVideo) {
				vd.getUploadFile().transferTo(new File(folderPath + vd.getVideoRename()));
			}
		}else {
			result = 0;
		}
		return result;
	}
	
	
	
	
	
}
