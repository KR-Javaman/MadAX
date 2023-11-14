package com.javaman.madax.myPage.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.javaman.madax.shorts.model.dto.Video;
import com.javaman.madax.shorts.model.dto.VideoBoard;

@Mapper
public interface MyShortsMapper {

	int getListCount(int memberNo);
	

	List<VideoBoard> selectBoard(int memberNo, RowBounds rowBounds);
	
//	int writeInsert(VideoBoard videoBoard);
//
//	int uploadVideoFile(List<Video> uploadVideo);
//
//	void videoInsert(Video vd);
//
//	int selectVideo(List<Video> uploadVideo);
//
//	VideoBoard videoBoardDetail(int boardVideoNo);
//
//	int likeCLick(Map<String, Object> map);
//
//	int readCount(int boardVideoNo);
//
//	int deleteLike(Map<String, Object> paramMap);
//
//	int insertLike(Map<String, Object> paramMap);
//
//	int countLike(Integer boardVideoNo);

	

	

	

}
