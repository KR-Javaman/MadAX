package com.javaman.madax.admin.model;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaman.madax.admin.mapper.MadAdminMapper;
import com.javaman.madax.member.model.dto.Member;


@Service
public class MadAdminServiceImpl implements MadAdminService {

	@Autowired
	private MadAdminMapper mapper;
	
	
	@Override
	public List<Member> selectAll() {
		// TODO Auto-generated method stub
		return mapper.selectAll();
	}
	
}
