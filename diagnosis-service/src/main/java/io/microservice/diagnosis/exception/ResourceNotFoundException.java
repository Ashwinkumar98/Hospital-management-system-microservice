package io.microservice.diagnosis.exception;

import java.io.Serializable;

public class ResourceNotFoundException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = -826985674233394796L;
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
	
}
