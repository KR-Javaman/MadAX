package com.javaman.madax.member.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class Member {
	
	private int memberNo;
	private String memberEmail;
	private String memberPw;
	private String memberNickname;
	private String profileImg;
	private String enrollDate;
	private String memberDelfl;
	private int authority;
	
	
	

}
