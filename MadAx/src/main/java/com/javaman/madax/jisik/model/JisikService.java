package com.javaman.madax.jisik.model;


import java.util.Map; 

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.member.model.dto.Member;

public interface JisikService {

	Map<String, Object> jisikList(Map<String, Object> paramMap, int cp);

	Board jisikDetail(int boardNo);


	int likeCheck(Map<String, Object> map);

	int like(Map<String, Object> paramMap);

	int updateBoardCount(int boardNo);

}

