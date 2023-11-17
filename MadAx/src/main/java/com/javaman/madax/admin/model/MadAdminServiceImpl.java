package com.javaman.madax.admin.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaman.madax.admin.dto.AdminPagination;
import com.javaman.madax.admin.mapper.MadAdminMapper;
import com.javaman.madax.member.model.dto.Member;


@Service
@Transactional
public class MadAdminServiceImpl implements MadAdminService {

	@Autowired
	private MadAdminMapper mapper;
	
	
	@Override
	public Member selectMember(String inputEmail) {
		return mapper.selectMember(inputEmail);
	}
	
	@Override
	public Map<String, Object> selectAll(Member member, int cp) {
		int listCount = mapper.listCount(member);
		
		AdminPagination pagination = new AdminPagination(cp, listCount);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> memberList = mapper.memberList(rowBounds);
		
		Map<String, Object> map = new HashMap<>();
		map.put("memberList", memberList);
		map.put("pagination", pagination);
		
		return map;
	}
	
	@Override
	public Map<String, Object> searchMember(Map<String, Object> paramMap, int cp) {
		int listCount = mapper.memberCount(paramMap);
		AdminPagination pagination = new AdminPagination(cp, listCount);
		
		int offset = (pagination.getCurrentPage()-1) * pagination.getLimit();
		int limit = pagination.getLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Member> memberList = mapper.searchMember(rowBounds, paramMap);
		
		Map<String, Object> map = new HashMap<>();
		map.put("pagination", pagination);
		map.put("memberList", memberList);
		return map;
	}
	
	
	@Override
	public int changeAuthority(int memberNo) {
		return mapper.changeAuthority(memberNo);
	}

	
}
