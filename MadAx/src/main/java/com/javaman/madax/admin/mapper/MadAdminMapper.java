package com.javaman.madax.admin.mapper;

import java.util.List; 

import org.apache.ibatis.annotations.Mapper;

import com.javaman.madax.member.model.dto.Member;

@Mapper
public interface MadAdminMapper {

	Member selectMember(String inputEmail);
	
	List<Member> selectAll();


}

