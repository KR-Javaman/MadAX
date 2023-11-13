package com.javaman.madax.shorts.model.dto;

import java.util.List;


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
	private String videoDelFl;
	private int memberNo;
	private String videoWriteDate;
	private String videoUpdateDate;
	
	private int commentCount;
	private int likeCount;
	private String memberNickname;
	private String videoPath;
	private String videoRename;
	
	private String profileImg;
	
	private List<Video> videoList;
	private List<VideoComment> videoCommentList;
}
