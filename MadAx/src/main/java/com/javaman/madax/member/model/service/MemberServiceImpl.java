package com.javaman.madax.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaman.madax.member.model.dto.Member;
import com.javaman.madax.member.model.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	

	@Override
	public Member login(Member inputMember) {

		// 1. 이메일이 일치하는 탈퇴하지 않은 회원 정보를 조회(pw 포함)
		Member loginMember = mapper.login(inputMember);
	
		// 2. 조회 결과가 없을 경우 return null;
		if(loginMember == null) return null;
	
		// 3. 입력 받은 비밀번호(평문)와 조회한 비밀번호가 같지 않으면 return null;
		if(!bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
			return null;
		}
		
		// 4. 비밀번호가 일치하면 비밀번호 제거 후 return 
		loginMember.setMemberPw(null);
		return loginMember;
	}

	@Override
	public int signup(Member inputMember) {
		
				// 비밀번호 암호화(DB에 암호화된 비밀번호 저장) 
				String encPw = bcrypt.encode(inputMember.getMemberPw());
				inputMember.setMemberPw(encPw);
				
				// Mapper 메서드 호출
				return mapper.signup(inputMember);
				
	}
	
	@Override
	public int checkEmail(String email) {
		return mapper.checkEmail(email);
	}
	
	@Override
	public int checkNickname(String nickname) {
		return mapper.checkNickname(nickname);
	}
	
	
	
	
	
}
