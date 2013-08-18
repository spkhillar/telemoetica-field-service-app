/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Class AndroidPageController.
 * @author Satyam
 */
@Controller
@RequestMapping(value = "/androidDownload")
public class AndroidPageController extends BaseController {

  /**
   * Creates the.
   * 
   * @return the string
   */
  @RequestMapping(value = "/appexe")
  public String create() {
    return "androidDownload.appexe";
  }
}
