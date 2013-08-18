/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.web.http.session.management;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.access.event.AuthorizationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.session.SessionDestroyedEvent;

/**
 * The Class HttpSessionManagement.
 * @author shiv prasad khillar
 */
public class HttpSessionManagement implements ApplicationListener<ApplicationEvent> {

  /**
   * On application event.
   *
   * @param event the event
   * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
   */
  @Override
  public void onApplicationEvent(final ApplicationEvent event) {

    if (event instanceof AuthorizationFailureEvent) {
      AuthorizationFailureEvent authorizationFailureEvent = (AuthorizationFailureEvent) event;
      System.err.println("not authorized:" + authorizationFailureEvent);
    } else if (event instanceof AuthenticationFailureBadCredentialsEvent) {
      AuthenticationFailureBadCredentialsEvent badCredentialsEvent = (AuthenticationFailureBadCredentialsEvent) event;
      System.err.println("badCredentials:" + badCredentialsEvent);
    }
    // login success event
    else if (event instanceof AuthenticationSuccessEvent) {
      AuthenticationSuccessEvent authenticationSuccessEvent = (AuthenticationSuccessEvent) event;
      // this will provide user id and password but no session, get source has
      // all the user information in security context
      System.err.println("AuthenticationSuccessEvent:" + authenticationSuccessEvent.getSource());
    }
    // this event will published if you add the HttpSessionEventPublisher to
    // web.xml
    else if (event instanceof SessionDestroyedEvent) {
      SessionDestroyedEvent sessinEvent = (SessionDestroyedEvent) event;
      System.err.println("SessionDestroyedEvent:" + sessinEvent.getId());
      // load session if it is not empty
      if (CollectionUtils.isNotEmpty(sessinEvent.getSecurityContexts())) {
        System.err.println("SessionDestroyedEvent:"
            + sessinEvent.getSecurityContexts().get(0).getAuthentication().getName());
        // update the session with endTime
      }
    } else {
      System.out.println ( "undefined: " + event.getClass ().getName () );
    }

  }

}
