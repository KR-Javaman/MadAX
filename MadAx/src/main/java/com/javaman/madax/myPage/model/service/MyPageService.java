package com.javaman.madax.myPage.model.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.javaman.madax.member.model.dto.Member;

public interface MyPageService {


	/** 회원 탈퇴
	 * @param memberPw
	 * @param memberNo
	 * @return result
	 */
	int secession2(String memberPw, int memberNo);

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
	
	/** 프로필 이미지 수정
	 * @param profileImg
	 * @param loginMember
	 * @return result
	 */
	int profile(MultipartFile profileImg, Member loginMember) throws IllegalStateException, IOException;

	int background(MultipartFile backgroundImg, Member loginMember) throws IllegalStateException, IOException;

	int deleteBackground(Member backgroundImg, Member loginMember) throws IllegalStateException, IOException;

	
	Map<String, Object> selectCommunity(int memberNo, int cp);

	Map<String, Object> selectJisikin(int memberNo, int cp);



}
