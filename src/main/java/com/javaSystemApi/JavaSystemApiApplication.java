package com.javaSystemApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class JavaSystemApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSystemApiApplication.class, args);
	}
	
}
