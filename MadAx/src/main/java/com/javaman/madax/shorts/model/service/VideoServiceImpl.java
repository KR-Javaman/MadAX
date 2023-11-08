package com.javaman.madax.shorts.model.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoServiceImpl implements VIdeoService{

	@Value("${my.video.location}")
	private String uploadPath;
	
	@Value("${ffmpeg.location}")
	private String ffmpegPath;
	
	@Value("${ffprobe.location}")
	private String ffprobePath;
	
	@Override
	public void fileUpload(MultipartFile video, String checkSize) 
			throws IllegalStateException, IOException {
		MultipartFile file = video;
		Map<String, Object> map = new HashMap<>();
		
		String filePath = uploadPath;
		String saveFileName = "";
		String svFilePath = "";
		
		String fileName = file.getOriginalFilename();
		String fileChunkName = fileName.substring(0, fileName.lastIndexOf("."));
		String fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
		String saveFilePath = filePath + File.separator + fileName;
		File folderFile = new File(filePath);
		
		if(!folderFile.exists()) {
			if(!folderFile.mkdirs()) {
				System.out.println("file.mkdirs : Success");
			}else {
				System.out.println("file.mkdirs : Fail");
			}
		}
		
		File saveFile = new File(saveFilePath);
		if(saveFile.isFile()) {
			boolean exist = true;
			
			int index = 0;
			while(exist) {
				index++;
				saveFileName = fileChunkName + "(" + index + ")" + fileExt;
				String fileDic = filePath + File.separator + saveFileName;
				exist = new File(fileDic).isFile();
				
				if(!exist) {
					svFilePath = fileDic;
				}
			}
		file.transferTo(new File(svFilePath));	
		}else {
			file.transferTo(saveFile);
		}
		
		
	}
}
