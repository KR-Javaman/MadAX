package com.javaman.madax.home.model.service;

import java.util.Map;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.shorts.model.dto.VideoBoard;

public interface HomeService {

	Map<String, Object> selectCommunity(Board board, int cp);

	Map<String, Object> selectJisikin(Board board, int cp);

	Map<String, Object> selectVideo(VideoBoard video, int cp);

}
