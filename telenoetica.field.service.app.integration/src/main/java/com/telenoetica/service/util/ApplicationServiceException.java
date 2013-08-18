/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.service.util;

/**
 * The Class ApplicationServiceException.
 *
 * @author  Shiv Prasad Khillar
 */
public class ApplicationServiceException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4700794202871578171L;

	/**
	 * Instantiates a new application service exception.
	 */
	public ApplicationServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new application service exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public ApplicationServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new application service exception.
	 *
	 * @param message the message
	 */
	public ApplicationServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new application service exception.
	 *
	 * @param cause the cause
	 */
	public ApplicationServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
