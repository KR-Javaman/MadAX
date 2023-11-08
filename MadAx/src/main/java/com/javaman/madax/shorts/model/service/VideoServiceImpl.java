package com.javaman.madax.shorts.model.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFmpegUtils;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.progress.Progress;
import net.bramp.ffmpeg.progress.ProgressListener;

@Service
@PropertySource("classpath:/config.properties")
public class VideoServiceImpl implements VIdeoService{

	@Value("${my.video.location}")
	private String uploadPath;
	
	@Value("${ffmpeg.location}")
	private String ffmpegPath;
	
	@Value("${ffprobe.location}")
	private String ffprobePath;
	
	@Override
	public void fileUpload(MultipartFile video, String checkSize) 
			throws Exception {
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
//		if(checkSize.equals("Y")) {
//			videoEffect(fileName);
//		}
		
	}

//	private void videoEffect(String fileName) throws IOException {
//		FFmpeg ffmpeg = new FFmpeg(ffmpegPath);
//		FFprobe ffprobe = new FFprobe(ffprobePath);
//		
//		FFmpegBuilder builder = new FFmpegBuilder()
//				.setInput(uploadPath+fileName)
//				.overrideOutputFiles(true)
//				.addOutput(uploadPath + fileName + "(format).mp4")
//				.setFormat("mp4")
////				.setTargetSize(1000_000)
//				
//				.disableSubtitle()
//				
//				.setAudioChannels(1)
//				.setAudioCodec("aac")
//				.setAudioSampleRate(48_000) 
//				.setAudioBitRate(32768) // 32kbit
//				
//				.setVideoCodec("libx264")
//				.setVideoFrameRate(24, 1) // 24frame
//				.setVideoResolution(640, 480)
//				
//				.setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
//				.done();
//		
//		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
//		
//		executor.createJob(builder ,new ProgressListener() {
//			
//		    // Using the FFmpegProbeResult determine the duration of the input
//		
//		    @Override
//		    public void progress(Progress progress) {
//		
//		      // Print out interesting information about the progress
//		      System.out.println(
//		              String.format(
//		                      "[%.0f%%] status:%s frame:%d time:%s fps:%.0f speed:%.2fx",
//		                      progress.status,
//		                      progress.frame,
//		                      FFmpegUtils.toTimecode(progress.out_time_ns, TimeUnit.NANOSECONDS),
//		                      progress.fps.doubleValue(),
//		                      progress.speed));
//		    }
//		  }).run();
//		
//			 // Or run a two-pass encode (which is better quality at the cost of being slower)
//			 executor.createTwoPassJob(builder).run();
//				
//	}
}
