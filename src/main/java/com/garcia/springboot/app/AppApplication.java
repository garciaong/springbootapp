package com.garcia.springboot.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.garcia.springboot.app.properties")
public class AppApplication {

	private static final Logger log = LogManager.getLogger(AppApplication.class);
	
	public static void main(String[] args) {
		log.info("Starting application...");
		SpringApplication.run(AppApplication.class, args);
		log.info("Application started...");
	}

}
