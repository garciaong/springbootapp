package com.garcia.springboot.app.advice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.garcia.springboot.app.api.PlayerController;
import com.garcia.springboot.app.model.Player;

@ControllerAdvice
public class CustomRequestBodyAdvice implements RequestBodyAdvice {

	private static final Logger log = LogManager.getLogger(CustomRequestBodyAdvice.class);
			
	/**
	 * Decides whether this implementation should run for the current request
	 */
	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		log.info("In supports() method of {}", getClass().getSimpleName());
        return methodParameter.getContainingClass() == PlayerController.class 
        		&& targetType.getTypeName() == Player.class.getTypeName();
	}

	/**
	 * Runs before Spring MVC reads the request body
	 */
	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
		log.info("In beforeBodyRead() method of {}", getClass().getSimpleName());
        return inputMessage;
	}

	/**
	 * Runs after the body is read and converted into an Object
	 */
	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		log.info("In afterBodyRead() method of {}", getClass().getSimpleName());
        if (body instanceof Player) {
        	Player player = (Player) body;
        	player.setTransactionDate(new Date());
            return player;
        }

        return body;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		log.info("In handleEmptyBody() method of {}", getClass().getSimpleName());
        return body;
	}

}
