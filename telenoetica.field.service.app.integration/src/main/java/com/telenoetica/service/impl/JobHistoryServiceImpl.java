/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.JobHistory;
import com.telenoetica.jpa.repositories.JobHistoryDAO;
import com.telenoetica.service.JobHistoryService;
import com.telenoetica.service.util.ExcelWriter;

/**
 * The Class JobHistoryServiceImpl.
 */
@Service("jobHistoryService")
@Transactional
public class JobHistoryServiceImpl implements JobHistoryService {

  /** The job history dao. */
  @Autowired
  private JobHistoryDAO jobHistoryDAO;

  /**
   * Retrieve.
   *
   * @param id the id
   * @return the job history
   * @see com.telenoetica.service.BaseService#retrieve(java.lang.Long)
   */
  @Override
  public JobHistory retrieve(final Long id) {
    return jobHistoryDAO.findOne(id);
  }

  /**
   * Save or update.
   *
   * @param jobHistory the job history
   * @return the job history
   * @see com.telenoetica.service.BaseService#saveOrUpdate(com.telenoetica.jpa.entities.BaseEntity)
   */
  @Override
  public JobHistory saveOrUpdate(final JobHistory jobHistory) {
    return jobHistoryDAO.saveAndFlush(jobHistory);
  }

  /**
   * Delete.
   *
   * @param jobHistory the job history
   * @see com.telenoetica.service.BaseService#delete(com.telenoetica.jpa.entities.BaseEntity)
   */
  @Override
  public void delete(final JobHistory jobHistory) {
    jobHistoryDAO.delete(jobHistory);
  }

  /**
   * Find one year job list.
   *
   * @param jobName the job name
   * @return the list
   * @see com.telenoetica.service.JobHistoryService#findOneYearJobList(java.lang.String)
   */
  @Override
  public List<JobHistory> findOneYearJobList(final String jobName) {
    Date startDate;
    Date endDate;
    Calendar currentDate = Calendar.getInstance(); // Get the current date
    endDate = currentDate.getTime();
    startDate = new DateTime(endDate).minusMonths(12).toDate();
    List<JobHistory> jobHistoryList = jobHistoryDAO
        .findByJobNameAndStartTimeBetween(jobName, startDate, endDate);
    return jobHistoryList;
  }

  /**
   * Gets the path.
   *
   * @param jobId the job id
   * @return the path
   * @see com.telenoetica.service.JobHistoryService#getPath(long)
   */
  @Override
  public String getPath(final long jobId) {
    JobHistory jobHistory = jobHistoryDAO.findById(jobId);
    String path = jobHistory.getPath();
    return path;
  }

  /**
   * Export report.
   *
   * @param reportPath the report path
   * @param httpServletResponse the http servlet response
   * @see com.telenoetica.service.JobHistoryService#exportReport(java.lang.String, javax.servlet.http.HttpServletResponse)
   */
  @Override
  public void exportReport(final String reportPath,
      final HttpServletResponse httpServletResponse) {

    String reportFileName = reportPath;
    ExcelWriter.write(httpServletResponse, reportFileName);
  }
}
