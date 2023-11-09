package com.javaman.madax.info.model;


import org.springframework.stereotype.Service;

import com.javaman.madax.info.mapper.InfoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService{
	
	
	private final InfoMapper mapper;
	

}
