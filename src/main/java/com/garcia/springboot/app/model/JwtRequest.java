package com.garcia.springboot.app.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtRequest implements Serializable {
	
	private static final long serialVersionUID = -880298621433337245L;
	private String username;
	private String password;
	
}
