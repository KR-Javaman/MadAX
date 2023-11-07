package com.javaman.madax.email.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.javaman.madax.email.model.mapper.EmailMapper;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // final 필드 DI 하는 구문
public class EmailServiceImpl implements EmailService{

	private final EmailMapper mapper;
	private final JavaMailSender mailSender; 
	private final SpringTemplateEngine templateEngine; 
	
	   @Override
	   public int sendEmail(String htmlName, String email) {
	        String authKey = createAuthKey();
	        
	        try {
	           
	     
	           String subject = null;
	           
	           switch(htmlName) {
	           case "signup" : subject = "[Madax] 회원가입 인증 번호 입니다."; break;
	           }
	           
	            MimeMessage mimeMessage = mailSender.createMimeMessage();
	            
	            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
	            
	            mimeMessageHelper.setTo(email); 
	            mimeMessageHelper.setSubject(subject); 
	            mimeMessageHelper.setText(loadHtml(authKey, htmlName), true); 
	            
	        
	            mimeMessageHelper.addInline("logo", new ClassPathResource("static/images/logo.png"));
	           
	            mailSender.send(mimeMessage);
	            
	        }catch (Exception e) {
	           e.printStackTrace();
	           return 0;
	        }
	        
	        Map<String, String> map = new HashMap<String, String>();
	        map.put("authKey", authKey);
	        map.put("email", email);
	        
	        int result = mapper.updateAuthKey(map);
	        
	        if(result == 0) {
	           result = mapper.insertAuthKey(map); // 삽입
	        }
	        
	      return result;
	      
	   }
	   
	   
	   @Override
	   public int checkAuthKey(Map<String, Object> paramMap) {
	      return mapper.checkAuthKey(paramMap);
	   }
	   
	   
	   
	    public String loadHtml(String authKey, String htmlName) {
	       // org.thymeleaf.context.Context
	        Context context = new Context();
	        context.setVariable("authKey", authKey); 
	        
	        return templateEngine.process("email/" + htmlName, context);
	        
	    }
	   
	    /** 인증번호 생성 (영어 대문자 + 소문자 + 숫자 6자리)
	     * @return authKey
	     */
	    public String createAuthKey() {
	        String key = "";
	        for(int i=0 ; i< 6 ; i++) {
	            
	            int sel1 = (int)(Math.random() * 3); // 0:숫자 / 1,2:영어
	            
	            if(sel1 == 0) {
	                
	                int num = (int)(Math.random() * 10); // 0~9
	                key += num;
	                
	            }else {
	                
	                char ch = (char)(Math.random() * 26 + 65); // A~Z
	                
	                int sel2 = (int)(Math.random() * 2); // 0:소문자 / 1:대문자
	                
	                if(sel2 == 0) {
	                    ch = (char)(ch + ('a' - 'A')); // 대문자로 변경
	                }
	                
	                key += ch;
	            }
	            
	        }
	        return key;
	    }
	
}
