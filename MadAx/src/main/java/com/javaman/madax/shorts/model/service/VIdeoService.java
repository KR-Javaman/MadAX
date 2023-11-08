package com.javaman.madax.shorts.model.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface VIdeoService {

	void fileUpload(MultipartFile video, String checkSize) throws Exception;

}
