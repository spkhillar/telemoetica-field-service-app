/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.repositories.GenericQueryExecutorDAO;
import com.telenoetica.service.AndroidHomeService;
import com.telenoetica.service.CallOutVisitService;
import com.telenoetica.service.DieselVisitService;
import com.telenoetica.service.MaintenanceVisitService;
import com.telenoetica.service.RoutineVisitService;
import com.telenoetica.util.model.HomeAndroidObject;

/**
 * The Class AndroidHomeServiceImpl.
 */
@Service("androidHomeService")
public class AndroidHomeServiceImpl implements AndroidHomeService {

  private static final Logger LOGGER = Logger
      .getLogger(AndroidHomeServiceImpl.class);

  /** The generic query executor dao. */
  @Autowired
  private GenericQueryExecutorDAO genericQueryExecutorDAO;

  /** The call out visit service. */
  @Autowired
  private CallOutVisitService callOutVisitService;

  /** The diesel visit service. */
  @Autowired
  private DieselVisitService dieselVisitService;

  /** The routine visit service. */
  @Autowired
  private RoutineVisitService routineVisitService;

  /** The maintenance visit service. */
  @Autowired
  private MaintenanceVisitService maintenanceVisitService;

  /** The system configuration. */
  @Autowired
  private SystemConfiguration systemConfiguration;

  /**
   * Gets the android home object.
   * 
   * @return the android home object
   * @see com.telenoetica.service.AndroidHomeService#getAndroidHomeObject()
   */
  @Override
  @Transactional(readOnly = true)
  public HomeAndroidObject getAndroidHomeObject() {
    List<String> sites = genericQueryExecutorDAO.executeQuery(
      "select name from Site order by name", String.class);
    List<String> faults = genericQueryExecutorDAO.executeQuery(
      "select name from Fault order by name", String.class);
    List<String> clients = genericQueryExecutorDAO.executeQuery(
      "select name from Client order by name", String.class);
    List<String> maintenanceCategories = genericQueryExecutorDAO
        .executeQuery("select name from MaintenanceVisitCategory order by name",
          String.class);
    List<String> spares = genericQueryExecutorDAO.executeQuery(
      "select name from Spare order by name", String.class);
    List<String> dieselVendors = genericQueryExecutorDAO.executeQuery(
      "select name from DieselVendor order by name", String.class);
    HomeAndroidObject homeAndroidObject = new HomeAndroidObject(sites,
      spares, clients, faults, maintenanceCategories, dieselVendors);
    return homeAndroidObject;
  }

  /**
   * Gets the chart data.
   * 
   * @return the chart data
   * @see com.telenoetica.service.AndroidHomeService#getchartData()
   */
  @Override
  @Transactional(readOnly = true)
  public List<Integer> getchartData() {
    Date startDate;
    Date endDate;
    Calendar currentDate = Calendar.getInstance(); // Get the current date
    endDate = currentDate.getTime();
    startDate = new DateTime(endDate).minusDays(30).toDate();
    Map<String, Object> param = new HashMap<String, Object>(1);
    param.put("endDate", endDate);
    param.put("startDate", startDate);
    List<Integer> chartData = new ArrayList<Integer>(4);

    long rvCount = routineVisitService.findRecordCount(param);
    long cvCount = callOutVisitService.findRecordCount(param);
    long dvCount = dieselVisitService.findRecordCount(param);
    long mvCount = maintenanceVisitService.findRecordCount(param);
    chartData.add((int) rvCount);
    chartData.add((int) cvCount);
    chartData.add((int) dvCount);
    chartData.add((int) mvCount);
    return chartData;
  }

  /**
   * Export android app.
   * 
   * @param httpServletResponse
   *            the http servlet response
   * @see com.telenoetica.service.AndroidHomeService#exportAndroidApp(javax.servlet.http.HttpServletResponse)
   */
  @Override
  public boolean exportAndroidApp(
      final HttpServletResponse httpServletResponse) {
    boolean downloaded = true;
    String appFilePath = systemConfiguration.getAndroidAppFilePath();
    LOGGER.debug("Downalod starts from source.." + appFilePath);
    File file = new File(appFilePath);
    FileInputStream fileIn;
    try {
      httpServletResponse.setContentType("application/octet-stream");
      httpServletResponse.setHeader("Content-Disposition",
        "Attachment;filename= " + "fieldapp.apk");
      fileIn = new FileInputStream(file);
      ServletOutputStream out = httpServletResponse.getOutputStream();

      byte[] outputByte = new byte[4096];
      // copy binary contect to output stream
      while (fileIn.read(outputByte, 0, 4096) != -1) {
        out.write(outputByte, 0, 4096);
      }
      fileIn.close();
      out.flush();
      out.close();
      LOGGER.debug("Downalod ends from source.." + appFilePath);
    } catch (Exception e) {
      LOGGER.error("Exception while downloading...", e);
      downloaded = false;
    }
    return downloaded;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.telenoetica.service.AndroidHomeService#exportAndroidAppHelp(javax
   * .servlet.http.HttpServletResponse)
   */
  @Override
  public void exportAndroidAppHelp(
      final HttpServletResponse httpServletResponse) {
    // TODO Auto-generated method stub

    String appFilePath = systemConfiguration.getAndroidAppHelpFilePath();
    LOGGER.debug("Downalod starts from source.." + appFilePath);
    File file = new File(appFilePath);
    FileInputStream fileIn;
    try {
      httpServletResponse.setContentType("application/vnd.ms-word");
      httpServletResponse
      .setHeader("Content-Disposition", "Attachment;filename= "
          + "HTN_FIELD_APP_ANDROID_HELP.docx");
      fileIn = new FileInputStream(file);
      ServletOutputStream out = httpServletResponse.getOutputStream();

      byte[] outputByte = new byte[4096];
      // copy binary contect to output stream
      while (fileIn.read(outputByte, 0, 4096) != -1) {
        out.write(outputByte, 0, 4096);
      }
      fileIn.close();
      out.flush();
      out.close();
      LOGGER.debug("Downalod ends from source.." + appFilePath);
    } catch (Exception e) {
      LOGGER.error("Exception while downloading...", e);
    }
  }
}
