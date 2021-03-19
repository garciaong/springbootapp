package com.garcia.springboot.app.model;

import java.util.Date;

import lombok.Data;

@Data
public class Player {

	private int id;
	private String name;
	private String club;
	private Date transactionDate;
	
}
