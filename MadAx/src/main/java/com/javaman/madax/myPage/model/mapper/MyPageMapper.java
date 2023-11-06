package com.javaman.madax.myPage.model.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {

	String selectMemberPw(int memberNo);

	int secession(int memberNo);

}
