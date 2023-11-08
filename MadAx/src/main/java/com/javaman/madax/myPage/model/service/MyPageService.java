package com.javaman.madax.myPage.model.service;

import com.javaman.madax.member.model.dto.Member;

public interface MyPageService {

	/** 회원 정보 수정
	 * @param memberPw
	 * @param memberNo
	 * @return result
	 */
	int secession(String memberPw, int memberNo);

	/** 닉네임 변경
	 * @param updateMember
	 * @return result
	 */
	int changeNickname(Member updateMember);

	/** 비밀번호 변경
	 * @param currentPw
	 * @param newPw
	 * @param memberNo
	 * @return result
	 */
	int changePw(String currentPw, String newPw, int memberNo);

}
