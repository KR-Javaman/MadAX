package com.javaman.madax.admin.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaman.madax.admin.mapper.MadAdminMapper;

@Service
public class MadAdminServiceImpl implements MadAdminService {

	@Autowired
	private MadAdminMapper mapper;
	
	
}
