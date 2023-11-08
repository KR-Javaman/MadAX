package com.javaman.madax.shorts.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Videoboard {
	private int boardVideoNo;
	private String videoTitle;
	private String videoContent;
	private int videoReadCount;
	private String videoDate;
	private String videoDelFl;
	private int memberNo;
	
	private List<Videoboard> videoList;
}
