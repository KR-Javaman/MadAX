package com.javaman.madax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MadAxApplication {

	public static void main(String[] args) {
		SpringApplication.run(MadAxApplication.class, args);
	}

}
