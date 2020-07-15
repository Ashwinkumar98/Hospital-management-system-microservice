package io.microservice.consultation.exception;

import java.io.Serializable;

public class ResourceAlreadyFoundException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = 2885792203286190743L;
	
	public ResourceAlreadyFoundException(String msg) {
		super(msg);
	}
}
