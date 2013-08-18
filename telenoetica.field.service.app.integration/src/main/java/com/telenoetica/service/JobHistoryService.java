/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.telenoetica.jpa.entities.JobHistory;

/**
 * The Interface JobHistoryService.
 */
public interface JobHistoryService extends BaseService<JobHistory> {

  /**
   * Find one year job list.
   *
   * @param jobName the job name
   * @return the list
   */
  List<JobHistory> findOneYearJobList(String jobName);

  /**
   * Export report.
   *
   * @param reportPath the report path
   * @param httpServletResponse the http servlet response
   */
  void exportReport(String reportPath, HttpServletResponse httpServletResponse);

  /**
   * Gets the path.
   *
   * @param jobId the job id
   * @return the path
   */
  String getPath(long jobId);

}
