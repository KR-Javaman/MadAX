package com.javaman.madax.shorts.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VideoComment {

	   private int commentNo;
	    private String commentContent;
	    private String commentWriteDate;
	    private String commentDelFl;
	    private int boardVideoNo;
	    private int memberNo;
	    private int parentNo;
	    
	    private String memberNickname;
	    private String profileImg;
	    
	    private int likeCountComment;
}
