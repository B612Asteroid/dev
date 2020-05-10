package com.song.dev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		registry.addMapping("/api/**")
		.allowedOrigins("http://localhost:3000");
		
		registry.addMapping("/lck/**")
		.allowedOrigins("http://localhost:3000");
	}
	
	
}
