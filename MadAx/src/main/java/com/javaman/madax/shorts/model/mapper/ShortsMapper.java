package com.javaman.madax.shorts.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.javaman.madax.shorts.model.dto.Video;
import com.javaman.madax.shorts.model.dto.VideoBoard;

@Mapper
public interface ShortsMapper {

	int getListCount(VideoBoard video);
	
	List<VideoBoard> selectList(RowBounds rowBounds);
	
	int writeInsert(VideoBoard videoBoard);

	int uploadVideoFile(List<Video> uploadVideo);

	void videoInsert(Video vd);

	int selectVideo(List<Video> uploadVideo);

	VideoBoard videoBoardDetail(int boardVideoNo);

	int likeCLick(Map<String, Object> map);

	int readCount(int boardVideoNo);

	int deleteLike(Map<String, Object> paramMap);

	int insertLike(Map<String, Object> paramMap);

	int countLike(Integer boardVideoNo);

	int deleteBoard(Map<String, Object> paramMap);

	int updateBoard(VideoBoard videoBoard);

	int videoUpdate(Video vd);

	

	

	

}
