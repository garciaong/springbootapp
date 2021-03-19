package com.garcia.springboot.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ErrorResponse<T> {

	private Object respose;
	
	public ErrorResponse(T object) {
		this.respose = object;
	}
	
}
