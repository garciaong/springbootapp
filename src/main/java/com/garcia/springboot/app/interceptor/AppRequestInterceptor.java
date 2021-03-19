package com.garcia.springboot.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AppRequestInterceptor implements HandlerInterceptor {

	private static final Logger log = LogManager.getLogger(AppRequestInterceptor.class);
	/**
	 * This is used to perform operations before sending the request to the controller. 
	 * This method should return true to return the response to the client.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("preHandle()");
		return true;
	}

	/**
	 * This is used to perform operations before sending the response to the client.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("postHandle()");
	}

	/**
	 * This is used to perform operations after completing the request and response.
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {
		log.info("afterCompletion()");
	}

}
