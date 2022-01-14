package com.example.pollingapplication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// enabling the use of Cors in the application
public class WebMvcConfig implements WebMvcConfigurer {

	private final long Max_AGE_SECS = 3600;
	
	@Value("${app.cors.allowedOrigins}")
	
	private String[] allowedOrigins;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/**")
		.allowedOrigins(allowedOrigins)
		.allowedMethods("HEAD","OPTIONS","GET","POST","PUT","PATCH","DELETE")
		.maxAge(Max_AGE_SECS);
	}
	
	
	
}
