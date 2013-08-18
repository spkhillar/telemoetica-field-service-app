/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.web.rest;

import java.io.Serializable;

/**
 * The Class RestResponse.
 *
 * @author  Shiv Prasad Khillar
 */
public class RestResponse implements Serializable{
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -1058263063993594276L;

  /** The status code. */
  private int statusCode;
  
  /** The message. */
  private String message;
  
  

  /**
   * Instantiates a new rest response.
   */
  public RestResponse() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Instantiates a new rest response.
   *
   * @param statusCode the status code
   * @param message the message
   */
  public RestResponse(int statusCode, String message) {
    super();
    this.statusCode = statusCode;
    this.message = message;
  }

  /**
   * Gets the status code.
   *
   * @return the status code
   */
  public int getStatusCode() {
    return statusCode;
  }

  /**
   * Sets the status code.
   *
   * @param statusCode the new status code
   */
  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  /**
   * Gets the message.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message.
   *
   * @param message the new message
   */
  public void setMessage(String message) {
    this.message = message;
  }
  
  
  

}
