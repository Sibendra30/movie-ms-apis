package com.moviestar.error;

public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = 1346623747455009025L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
