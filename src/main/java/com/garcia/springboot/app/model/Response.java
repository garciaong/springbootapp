package com.garcia.springboot.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Response<T> {
	private Object respose;
	
	public Response(T object) {
		this.respose = object;
	}
	
}
