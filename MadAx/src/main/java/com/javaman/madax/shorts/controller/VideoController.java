package com.javaman.madax.shorts.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaman.madax.shorts.model.service.VIdeoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("shorts")
//@PropertySource("classpath:/config.properties")
public class VideoController {
	private final VIdeoService service;
	
	@Value("${my.video.location}")
	private String uploadPath;
	
	@PostMapping("upload")
	public ResponseEntity<String> fileUpload(@RequestParam("video") MultipartFile video,
											@RequestParam("checkSize") String checkSize){
		
		if(video != null) {
			System.out.println("저장 중");
			try {
				service.fileUpload(video,checkSize);
			}catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>("fail",HttpStatus.NOT_IMPLEMENTED);
			}
		}
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
}
