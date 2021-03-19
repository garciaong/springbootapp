package com.garcia.springboot.app.util;

public class StringUtil {

	public static boolean isEmpty(String value) {
		return (value == null || value.trim().length()<1);
	}
	
	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}
	
}
