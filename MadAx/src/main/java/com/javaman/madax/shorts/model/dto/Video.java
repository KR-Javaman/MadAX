package com.javaman.madax.shorts.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Video {
	private int videoNo;
	private String videoPath;
	private String videoRename;
	private String videoOriginalName;
	private int videoOrder;
	private int boardVideoNo;
	
	private MultipartFile uploadFile;
	
	
}
