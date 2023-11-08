package com.javaman.madax.shorts.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javaman.madax.shorts.model.dto.Video;

@Mapper
public interface ShortsMapper {

	List<Video> selectVideoList(int boardCode);

}
