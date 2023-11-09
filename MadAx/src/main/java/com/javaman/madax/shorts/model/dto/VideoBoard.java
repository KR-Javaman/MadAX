package com.javaman.madax.shorts.model.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VideoBoard {
	private int boardVideoNo;
	private String videoTitle;
	private String videoContent;
	private int videoReadCount;
	private String videoDate;
	private String videoDelFl;
	private int memberNo;
	
	private int likeCount;
	private String videoPath;
	private String videoRename;
	
}
