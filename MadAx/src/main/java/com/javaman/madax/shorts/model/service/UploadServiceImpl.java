package com.javaman.madax.shorts.model.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@PropertySource("classpath:/config.properties")
public class UploadServiceImpl implements UploadService{

	@Value("${my.video.location}")
	private String folderPath; 
	
	@Override
	public boolean chunkUpload(MultipartFile shortsVideo, int chunkNumber, int totalChunks) throws IOException {
		
//        File dir = new File(folderPath);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }

		// 임시 저장 파일 이름
        String filename = shortsVideo.getOriginalFilename() + ".part" + chunkNumber;

        Path filePath = Paths.get(folderPath, filename);
        // 임시 저장
        Files.write(filePath, shortsVideo.getBytes());

		// 마지막 조각이 전송 됐을 경우
        if (chunkNumber == totalChunks-1) {
            String[] split = shortsVideo.getOriginalFilename().split("\\.");
            String outputFilename = UUID.randomUUID() + "." + split[split.length-1];
            Path outputFile = Paths.get(folderPath, outputFilename);
            Files.createFile(outputFile);
            
            // 임시 파일들을 하나로 합침
            for (int i = 0; i < totalChunks; i++) {
                Path chunkFile = Paths.get(folderPath, shortsVideo.getOriginalFilename() + ".part" + i);
                Files.write(outputFile, Files.readAllBytes(chunkFile), StandardOpenOption.APPEND);
                // 합친 후 삭제
                Files.delete(chunkFile);
            }
            log.info("File uploaded successfully");
            return true;
        } else {
            return false;
        }
	}
}
