package com.javaman.madax.member.model.service;


import com.javaman.madax.member.model.dto.Member;


public interface MemberService {


	/** 로그인 서비스
	 * @param inputMember
	 * @return loginMember
	 */
	Member login(Member inputMember);

	int signup(Member inputMember);

	int checkEmail(String email);

	int checkNickname(String nickname);
	



}
