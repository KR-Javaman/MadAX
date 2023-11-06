package com.javaman.madax.myPage.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaman.madax.myPage.model.mapper.MyPageMapper;

@Service
@Transactional
public class MyPageServiceImpl implements MyPageService{

	
	@Autowired
	private MyPageMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	public int secession(String memberPw, int memberNo) {
		// 1. 로그인한 회원의 암호화된 비밀번호 조회
				String encPw = mapper.selectMemberPw(memberNo);
				if(!bcrypt.matches(memberPw, encPw)) { // 같지 않으면
					return 0;
				}
				
				// 탈퇴 처리 SQL 수행
				return mapper.secession(memberNo);
	}
	
}
