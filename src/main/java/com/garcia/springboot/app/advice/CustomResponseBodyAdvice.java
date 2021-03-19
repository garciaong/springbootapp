package com.garcia.springboot.app.advice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.garcia.springboot.app.model.ErrorResponse;
import com.garcia.springboot.app.model.Response;

@ControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice {

	private static final Logger log = LogManager.getLogger(CustomResponseBodyAdvice.class);

	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass,
			ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
		if (methodParameter.getContainingClass().isAnnotationPresent(RestController.class)) {
			if ((!(o instanceof ErrorResponse)) && (!(o instanceof Response))) {
				Response<Object> responseBody = new Response<>(o);
				return responseBody;
			}
		}
		return o;
	}

}
