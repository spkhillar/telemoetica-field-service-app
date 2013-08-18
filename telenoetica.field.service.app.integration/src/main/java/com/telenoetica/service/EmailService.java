/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service;

import com.telenoetica.service.mail.EmailTemplate;

/**
 * The Interface EmailService.
 */
public interface EmailService {

  /**
   * Send email.
   *
   * @param emailTemplate the email template
   */
  public void sendEmail(EmailTemplate emailTemplate);

}
