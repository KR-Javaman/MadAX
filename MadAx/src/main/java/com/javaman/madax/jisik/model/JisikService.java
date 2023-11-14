package com.javaman.madax.jisik.model;


import java.util.Map; 

import com.javaman.madax.board.model.dto.Board;

public interface JisikService {


	Map<String, Object> jisikList(Map<String, Object> paramMap, int cp);

	Board jisikDetail(int boardNo);

	

	

	
}

