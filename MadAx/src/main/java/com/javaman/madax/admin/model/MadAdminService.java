package com.javaman.madax.admin.model;

import java.util.List;
import java.util.Map;

import com.javaman.madax.member.model.dto.Member;

public interface MadAdminService {



//	List<Member> selectSort(int sort);

//	List<Member> selectAll();

	Map<String, Object> searchMember(Map<String, Object> paramMap, int cp);

	Map<String, Object> selectAll(Member member, int cp);

	Member selectMember(String inputEmail);
	
	


	

	
}
