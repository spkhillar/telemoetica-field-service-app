/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.telenoetica.service.UserService;
import com.telenoetica.web.http.session.management.LogoutEventPublisher;

/**
 * The Class AccessController.
 * 
 * @author Shiv Prasad Khillar
 */
@Controller
public class AccessController extends BaseController {

  @Autowired
  private LogoutEventPublisher logoutEventPublisher;

  @Autowired
  private UserService userService;

  /**
   * Login.
   * 
   * @param model
   *            the model
   * @param message
   *            the message
   * @return the string
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(final Model model,
      @RequestParam(required = false) final String message) {
    model.addAttribute("message", message);
    return "login";
  }

  /**
   * Denied.
   * 
   * @return the string
   */
  @RequestMapping(value = "/denied", method = RequestMethod.GET)
  public String denied() {
    return "denied";
  }

  /**
   * Login failure.
   * 
   * @param model
   *            the model
   * @return the string
   */
  @RequestMapping(value = "/login/failure", method = RequestMethod.GET)
  public String loginFailure(final Model model) {
    String message = "Login Failure! Invalid User Id or Password.";
    model.addAttribute("message", message);
    return "redirect:/login";
  }

  /**
   * Logout success.
   * 
   * @param model
   *            the model
   * @return the string
   */
  @RequestMapping(value = "/logout/success", method = RequestMethod.GET)
  public String logoutSuccess(final Model model, final HttpServletRequest request) {
    String message = "Loggedout Successfully.";
    model.addAttribute("message", message);
    logoutEventPublisher.publish(request,userService.findByUserName(getCurrentLoggedinUserName()));
    return "redirect:/login";
  }

  /**
   * Logout session time out.
   * 
   * @param model
   *            the model
   * @return the string
   */
  @RequestMapping(value = "/logout/session", method = RequestMethod.GET)
  public String logoutSessionTimeOut(final Model model) {
    String message = "";
    model.addAttribute("message", message);
    return "redirect:/login";
  }

  /**
   * Logout session time out.
   * 
   * @param model
   *            the model
   * @return the string
   */
  @RequestMapping(value = "/logout/maxSession", method = RequestMethod.GET)
  public String logoutMaxSession(final Model model) {
    String message = "User already logged into the system.";
    model.addAttribute("message", message);
    return "redirect:/login";
  }

  /**
   * Session time out.
   * 
   * @return the string
   */
  @RequestMapping(value = "/sessiontimeout", method = RequestMethod.GET)
  public String sessionTimeOut() {
    return "sessiontimeout";
  }
}