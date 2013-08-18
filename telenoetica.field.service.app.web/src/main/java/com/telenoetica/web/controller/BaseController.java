/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telenoetica.web.rest.RestResponse;

/**
 * The Class BaseController.
 * 
 * @author Shiv Prasad Khillar
 */
public class BaseController {

  @InitBinder
  public void initBinder(final WebDataBinder binder) {
    binder.registerCustomEditor(Date.class, new CustomDateEditor(
      new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"), true));
  }

  /** The Constant logger. */
  protected static final Logger logger = LoggerFactory
      .getLogger(BaseController.class);

  /**
   * Handle internal service exception.
   * 
   * @param ex
   *            the ex
   * @param request
   *            the request
   * @return the rest response
   */
  @ExceptionHandler(Throwable.class)
  @ResponseBody
  public RestResponse handleInternalServiceException(final Exception ex,
      final HttpServletRequest request) {
    logger.error("handleInternalServiceException-User-", ex);
    RestResponse restResponse = new RestResponse(500, ex.getMessage());
    return restResponse;
  }

  public String getCurrentLoggedinUserName() {
    Authentication auth = SecurityContextHolder.getContext()
        .getAuthentication();
    org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) auth
        .getPrincipal();
    String username = principal.getUsername();
    return username;
  }

}
