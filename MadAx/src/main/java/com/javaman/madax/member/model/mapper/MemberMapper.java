package com.javaman.madax.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.javaman.madax.member.model.dto.Member;

@Mapper
public interface MemberMapper {

	Member login(Member inputMember);
	

	int signup(Member inputMember);


	int checkEmail(String email);


	int checkNickname(String nickname);

}
