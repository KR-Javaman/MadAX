package com.javaman.madax.shorts.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PropertySource("classpath:/config.properties")
@RequestMapping("vod")
public class VodController {

	@Value("${my.video.webpath}")
	private String webPath;
	
	@Value("${my.video.location}")
	private String folderPath;
	
	@GetMapping("{filename}")
	public ResponseEntity<ResourceRegion> resource(
			@RequestHeader HttpHeaders headers,
			@PathVariable String filename) throws IOException{
		String Path = folderPath + "/" + filename;
		Resource resource = new FileSystemResource(Path);
		
		long chunkSize = 1024*1024;
		long videoLength = resource.contentLength();
		
		HttpRange httpRange = headers.getRange().stream().findFirst()
				.orElse(HttpRange.createByteRange(0, videoLength-1));
		
		
		long calculateRange = calculateRangeLength(httpRange, videoLength, chunkSize);
		
		ResourceRegion region = new ResourceRegion(resource, httpRange.getRangeStart(videoLength), calculateRange);

		return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
				.cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES))
				.contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
				.header("Accept-Ranges", "bytes")
				.eTag(Path)
				.body(region);
	}

	private long calculateRangeLength(HttpRange httpRange, long videoLength, long chunkSize) {
		long start = httpRange.getRangeStart(videoLength);
		long end = httpRange.getRangeEnd(videoLength);
		return Long.min(chunkSize, end-start+1);
	}
}
