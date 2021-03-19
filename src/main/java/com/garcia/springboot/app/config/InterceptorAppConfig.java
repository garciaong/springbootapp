package com.garcia.springboot.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.garcia.springboot.app.interceptor.AppRequestInterceptor;

/**
 * Registering interceptors
 */
@Component
public class InterceptorAppConfig extends WebMvcConfigurerAdapter {
	@Autowired
	AppRequestInterceptor appRequestInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(appRequestInterceptor);
	}
}
