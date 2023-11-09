package com.javaman.madax.info.model;

import java.util.Map;

public interface InfoService {

	Map<String, Object> selectBoardList(int boardCode, int cp);

	Map<String, Object> searchBoardList(Map<String, Object> paramMap, int cp);

}
