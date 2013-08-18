/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.rest;

/**
 * The Class RestResponse.
 */
public class RestResponse {

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
  public RestResponse(final int statusCode, final String message) {
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
  public void setStatusCode(final int statusCode) {
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
  public void setMessage(final String message) {
    this.message = message;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("RestResponse [statusCode=");
    builder.append(statusCode);
    builder.append(", ");
    if (message != null) {
      builder.append("message=");
      builder.append(message);
    }
    builder.append("]");
    return builder.toString();
  }



}
