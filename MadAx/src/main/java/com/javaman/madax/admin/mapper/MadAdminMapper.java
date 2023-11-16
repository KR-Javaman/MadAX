package com.javaman.madax.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.javaman.madax.member.model.dto.Member;

@Mapper
public interface MadAdminMapper {

	Member selectMember(String inputEmail);
	
//	List<Member> selectAll();

	int listCount(Member member);

	List<Member> memberList(RowBounds rowBounds);

	int memberCount(Map<String, Object> paramMap);

	List<Member> searchMember(RowBounds rowBounds, Map<String, Object> paramMap);


}

