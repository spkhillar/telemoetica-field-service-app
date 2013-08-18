/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service;

import java.util.Date;

/**
 * The Interface DieselDetailReportService.
 */
public interface DieselDetailReportService {

  /**
   * Creates the new report.
   *
   * @return the string
   * @throws Exception the exception
   */
  public String createNewReport(final Date forDate) throws Exception;

}
