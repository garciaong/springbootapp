package com.garcia.springboot.app.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtResponse implements Serializable {
	
	public JwtResponse(String token) {
		this.token = token;
	}
	private static final long serialVersionUID = 2337719909166230624L;
	private String token;

}
