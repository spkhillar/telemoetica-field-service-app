/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.scheduler.job;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.telenoetica.jpa.entities.JobHistory;
import com.telenoetica.jpa.entities.JobStatus;
import com.telenoetica.service.JobHistoryService;
import com.telenoetica.service.SpareUtilizationReportService;

/**
 * The Class SparesUtilizationReportJob.
 */
public class SpareUtilizationReportJob extends QuartzJobBean {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = Logger
      .getLogger(SpareUtilizationReportJob.class);

  /** The job history service. */
  private JobHistoryService jobHistoryService;

  /** The Spare Utilization report service. */
  private SpareUtilizationReportService spareUtilizationReportService;

  /**
   * Sets the Spare Utilization report service.
   * 
   * @param spareUtilizationReportService
   *            the new Spare Utilization report service
   */
  public void setSpareUtilizationReportService(
      final SpareUtilizationReportService spareUtilizationReportService) {
    this.spareUtilizationReportService = spareUtilizationReportService;
  }

  /**
   * Sets the job history service.
   * 
   * @param jobHistoryService
   *            the new job history service
   */
  public void setJobHistoryService(final JobHistoryService jobHistoryService) {
    this.jobHistoryService = jobHistoryService;
  }

  /**
   * Execute internal.
   * 
   * @param context
   *            the context
   * @throws JobExecutionException
   *             the job execution exception
   * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
   */
  @Override
  protected void executeInternal(final JobExecutionContext context)
      throws JobExecutionException {
    LOGGER.debug("Job Started");
    String reportPath = "";
    JobHistory jobHistory = new JobHistory("SparesUtilizationReportJob",
      "SparesUtilizationReport", new Date(), null, JobStatus.RUNNING);
    createJobStatus(jobHistory);
    // setup the
    // Do your work
    try {
      reportPath = spareUtilizationReportService.createNewReport(null);
      jobHistory.setPath(reportPath);
      jobHistory.setEndTime(new Date());
      jobHistory.setJobStatus(JobStatus.COMPLETED);
    } catch (Exception e) {
      LOGGER.debug("Error while creating Report");
      e.printStackTrace();
      jobHistory.setEndTime(new Date());
      jobHistory.setJobStatus(JobStatus.FAILED);
    }
    updateJobStatus(jobHistory);

  }

  /**
   * Creates the job status.
   * 
   * @param jobHistory
   *            the job history
   */
  public void createJobStatus(final JobHistory jobHistory) {
    jobHistoryService.saveOrUpdate(jobHistory);
  }

  /**
   * Update job status.
   * 
   * @param jobHistory
   *            the job history
   */
  public void updateJobStatus(final JobHistory jobHistory) {
    jobHistoryService.saveOrUpdate(jobHistory);
  }
}
