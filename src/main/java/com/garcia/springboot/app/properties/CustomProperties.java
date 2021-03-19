package com.garcia.springboot.app.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
//@ConfigurationProperties(prefix="custom")
@PropertySource("file:secondary.properties")
//@Data
public class CustomProperties {

	@Autowired
	Environment env;
	
	private String jwtTokenValidity;
	
	public String getJwtTokenValidity() {
		return env.getProperty("custom.jwtTokenValidity");
	}
	
}
