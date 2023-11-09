package com.javaman.madax.shorts.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.javaman.madax.shorts.model.dto.Video;
import com.javaman.madax.shorts.model.dto.VideoBoard;

@Mapper
public interface ShortsMapper {

	int getListCount(VideoBoard video);
	List<VideoBoard> selectList(RowBounds rowBounds);

	

	

}
