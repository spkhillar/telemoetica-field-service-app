/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.web.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.telenoetica.jpa.entities.JobHistory;
import com.telenoetica.jpa.entities.JobStatus;
import com.telenoetica.service.DieselDetailReportService;
import com.telenoetica.service.JobHistoryService;
import com.telenoetica.service.SpareUtilizationReportService;
import com.telenoetica.service.util.ServiceUtil;
import com.telenoetica.web.rest.RestResponse;

/**
 * The Class JobServiceController.
 * @author Satyam
 */
@Controller
@RequestMapping(value = "/reportDownload")
@SessionAttributes("jobHistoryForm")
public class JobServiceController extends BaseController {

  /** The job history service. */
  @Autowired
  private JobHistoryService jobHistoryService;


  @Autowired
  private DieselDetailReportService dieselDetailReportService;

  @Autowired
  private SpareUtilizationReportService spareUtilizationReportService;

  /**
   * Creates the.
   *
   * @param model the model
   * @param type the type
   * @return the string
   */
  @RequestMapping(value = "/monthlyReport/{type}")
  public String create(final Model model, @PathVariable final String type) {
    model.addAttribute("type", type);
    return "reportDownload.monthlyReport";
  }

  /**
   * Find by start time between.
   *
   * @param type the type
   * @return the list
   */
  @RequestMapping(value = "/yearlyReportList/{type}", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public List<JobHistory> findByStartTimeBetween(
    @PathVariable final String type) {
    String jobName = "";
    if (type.equals("diesel")) {
      jobName = "DieselDetailReportJob";
    } else {
      jobName = "SparesUtilizationReportJob";
    }
    List<JobHistory> listReport = jobHistoryService
        .findOneYearJobList(jobName);
    return listReport;
  }

  /**
   * Export diesel details report.
   *
   * @param jobId the job id
   * @param httpServletResponse the http servlet response
   */
  @RequestMapping(value = "/monthlyReport/export/{jobId}")
  @ResponseBody
  public void exportDieselDetailsReport(@PathVariable final long jobId,
      final HttpServletResponse httpServletResponse) {
    String reportPath = jobHistoryService.getPath(jobId);
    jobHistoryService.exportReport(reportPath, httpServletResponse);
  }

  /**
   * Export diesel details report.
   *
   * @param jobId the job id
   * @param httpServletResponse the http servlet response
   */
  @RequestMapping(value = "/manualmonthlyreport/{type}/{forDate}")
  @ResponseBody
  public RestResponse exportDieselDetailsReportManually(@PathVariable final String type,@PathVariable final String forDate,
      final HttpServletResponse httpServletResponse) {
    RestResponse restResponse = new RestResponse();
    restResponse.setMessage("Failed");
    Date date = ServiceUtil.getDateInFormat(forDate, "dd-MM-yyyy");
    /*  if(date == null){
      restResponse.setMessage("in valid date input. date cannot be null and should be in format(dd/MM/yyyy).");
      return restResponse;
    }*/
    String reportName=null;
    try {
      if("diesel".equals(type)) {
        reportName = invokeDieselReport(date);
      }else{
        reportName =invokeSpareReport(date);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if(reportName != null){
      restResponse.setMessage(reportName);
    }
    return restResponse;
  }

  private String invokeDieselReport(final Date date){

    String reportPath = null;
    Calendar cal = Calendar.getInstance();
    String description = "DieselDetailReport"
        + (new SimpleDateFormat("MMM").format(cal.getTime()));
    JobHistory jobHistory = new JobHistory("DieselDetailReportJob",
      description, new Date(), null, JobStatus.RUNNING);
    createJobStatus(jobHistory);
    // setup the
    // Do your work
    try {
      reportPath = dieselDetailReportService.createNewReport(date);
      jobHistory.setPath(reportPath);
      jobHistory.setEndTime(new Date());
      jobHistory.setJobStatus(JobStatus.COMPLETED);
    } catch (Exception e) {
      logger.error("Error while creating Report", e);
      jobHistory.setEndTime(new Date());
      jobHistory.setJobStatus(JobStatus.FAILED);
    }
    updateJobStatus(jobHistory);
    return reportPath;
  }

  private String invokeSpareReport(final Date forDate){
    String reportPath = null;
    JobHistory jobHistory = new JobHistory("SparesUtilizationReportJob",
      "SparesUtilizationReport", new Date(), null, JobStatus.RUNNING);
    createJobStatus(jobHistory);
    // setup the
    // Do your work
    try {
      reportPath = spareUtilizationReportService.createNewReport(forDate);
      jobHistory.setPath(reportPath);
      jobHistory.setEndTime(new Date());
      jobHistory.setJobStatus(JobStatus.COMPLETED);
    } catch (Exception e) {
      logger.debug("Error while creating Report",e);
      jobHistory.setEndTime(new Date());
      jobHistory.setJobStatus(JobStatus.FAILED);
    }
    updateJobStatus(jobHistory);
    return reportPath;
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
