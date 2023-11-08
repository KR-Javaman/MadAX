package com.javaman.madax.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.MultipartConfigElement;

@Configuration
@PropertySource("classpath:/config.properties") 
public class FileConfig implements WebMvcConfigurer{

   
   @Value("${my.images.connectpath}")
   private String connectPath; 
   
   @Value("${my.images.resourcepath}")
   private String resourcePath; 
   
   @Value("${my.video.location}")
   private String folderPath; 
   
   @Value("${spring.servlet.multipart.file-size-threshold}")
   private long fileSizeThreshold; 
   
   @Value("${spring.servlet.multipart.max-file-size}")
   private long maxFileSize; 
   
   @Value("${spring.servlet.multipart.max-request-size}")
   private long maxRequestSize; 
   
   @Value("${spring.servlet.multipart.location}")
   private String location; 
   
   
   
   
   
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      
      registry.addResourceHandler(connectPath)
         .addResourceLocations(resourcePath);
   }
   
   
   
   
   @Bean
   public MultipartConfigElement multipartConfigElement() {
      MultipartConfigFactory factory = new MultipartConfigFactory();
      
      factory.setFileSizeThreshold(DataSize.ofBytes(fileSizeThreshold)); 
      
      factory.setMaxFileSize(DataSize.ofBytes(maxFileSize)); 
      
      factory.setMaxRequestSize(DataSize.ofBytes(maxRequestSize)); 
      
      factory.setLocation(location); 
      
      return factory.createMultipartConfig();
   }
   
   
   @Bean
   public MultipartResolver multipartResolver() {
      StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
      return multipartResolver;
   }
   

   
}