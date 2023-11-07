package com.javaman.madax.admin.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
	
	   private int memberNo;
	   private String memberEmail;
	   private String memberPw;
	   private String memberNickname; 
	   private String profileImg;
	   private String enrollDate;
	   private int authority;

	
}
