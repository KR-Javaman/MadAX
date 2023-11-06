package com.javaman.madax.shorts.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaman.madax.shorts.model.service.UploadService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("shorts")
public class UploadController {
	private final UploadService service;
	
	@GetMapping("main")
	public String main() {
		return "shorts/shorts-main";
	}	
	
	@PostMapping("video")
	public ResponseEntity<String> chunkUpload(
			@RequestParam("chunk") MultipartFile shortsVideo,
            @RequestParam("chunkNumber") int chunkNumber,
            @RequestParam("totalChunks") int totalChunks) throws IOException {
boolean isDone = service.chunkUpload(shortsVideo, chunkNumber, totalChunks);

return isDone ?
ResponseEntity.ok("File uploaded successfully") :
ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).build();
}
}
