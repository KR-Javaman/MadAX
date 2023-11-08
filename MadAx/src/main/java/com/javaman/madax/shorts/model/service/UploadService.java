package com.javaman.madax.shorts.model.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

	boolean chunkUpload(MultipartFile shortsVideo, int chunkNumber, int totalChunks) throws IOException;

}
