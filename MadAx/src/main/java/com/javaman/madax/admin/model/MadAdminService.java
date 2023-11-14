package com.javaman.madax.admin.model;

import java.util.List; 

import com.javaman.madax.member.model.dto.Member;

public interface MadAdminService {

	Member selectMember(String inputEmail);
	
	List<Member> selectAll();


	

	
}
