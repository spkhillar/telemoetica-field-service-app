package com.telenoetica.web.event;

import org.springframework.context.ApplicationEvent;

public class LogoutSuccessEvent extends ApplicationEvent {

  public LogoutSuccessEvent(final Object source) {
    super(source);
  }

  /**
   * 
   */
  private static final long serialVersionUID = 3169048395799636911L;

}
