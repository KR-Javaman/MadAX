package com.javaman.madax.myPage.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.multipart.MultipartFile;

import com.javaman.madax.board.model.dto.Board;
import com.javaman.madax.member.model.dto.Member;

@Mapper
public interface MyPageMapper {


	/** 닉네임 수정
	 * @param updateMember
	 * @return result
	 */
	int changeNickname(Member updateMember);
	
	/** 회원 비밀번호(암호화) 조회
	 * @param memberNo
	 * @return encPw
	 */
	String selectMemberPw(int memberNo);

	/** 비밀번호 변경
	 * @param map
	 * @return result
	 */
	int changePw(Map<String, Object> map);
	
	/** 회원 탈퇴
	 * @param memberNo
	 * @return result
	 */
	int secession2(int memberNo);

	/** 프로필 이미지 수정
	 * @param loginMember
	 * @return result
	 */
	int profile(Member loginMember);

	int background(Member loginMember);


	int deleteBackground(Member backgroundImg);



	List<Board> selectCommunity(int memberNo, RowBounds rowBounds);
	
	List<Board> selectJisikin(int memberNo, RowBounds rowbounds2);

	List<Board> selectVideo(int memberNo, RowBounds rowBounds3);
	
	


}
