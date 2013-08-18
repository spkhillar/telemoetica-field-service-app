/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.RoutineVisit;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.jpa.repositories.GenericQueryExecutorDAO;
import com.telenoetica.jpa.repositories.RoutineVisitDAO;
import com.telenoetica.service.RoutineVisitService;
import com.telenoetica.service.SiteService;
import com.telenoetica.service.UserService;
import com.telenoetica.service.excel.ExcelFillerService;
import com.telenoetica.service.excel.ExcelLayoutService;
import com.telenoetica.service.util.ApplicationServiceException;
import com.telenoetica.service.util.ExcelRendererModel;
import com.telenoetica.service.util.ExcelWriter;
import com.telenoetica.service.util.ServiceUtil;

/**
 * The Class RoutineVisitServiceImpl.
 * 
 * @author Shiv Prasad Khillar
 */
@Service("routineVisitService")
@Transactional
public class RoutineVisitServiceImpl extends AbstractBaseService implements
RoutineVisitService {

  /** The routine visit dao. */
  @Autowired
  private RoutineVisitDAO routineVisitDAO;

  /** The site service. */
  @Autowired
  private SiteService siteService;

  /** The user service. */
  @Autowired
  private UserService userService;

  /** The excel layout service. */
  @Resource(name = "defaultExcelLayoutService")
  private ExcelLayoutService excelLayoutService;

  /** The excel filler service. */
  @Resource(name = "defaultExcelLayoutFillerService")
  private ExcelFillerService excelFillerService;

  /** The generic query executor dao. */
  @Autowired
  private GenericQueryExecutorDAO genericQueryExecutorDAO;

  /** The Constant PAGE_SIZE. */
  private static final int PAGE_SIZE = 50;

  /** The Constant ROUTINE_VIST_EXCEL_COLUMN_LIST. */
  private static final String[] ROUTINE_VIST_EXCEL_COLUMN_LIST = new String[] {
    "Access Code", "User Name", "Site", "Created At",
    "Diesel Level T1", "Diesel Level T2", "Run Hour Gen 1",
    "Run Hour Gen 2", "Voltage N-R volts", "Voltage N-Y volts",
    "Voltage N-B volts", "Load R phase Amps", "Load Y phase Amps",
    "Load B phase Amps", "Rectifier O/P voltage",
    "Rectifier O/P current Amp", "Battery capacity V",
    "Battery capacity AH", "Ats Functional", "Ats Syncronising",
    "DRN booked at site", "Diesel log book maintained",
    "Aircon of shelter1 cooling", "Aircon of shelter2 cooling",
    "Aircon of shelter3 cooling", "Aircon of shelter4 cooling" };

  /**
   * Retrieve.
   * 
   * @param id
   *            the id
   * @return the routine visit
   * @see com.telenoetica.service.BaseService#retrieve(java.lang.Long)
   */
  @Override
  public RoutineVisit retrieve(final Long id) {
    return routineVisitDAO.findOne(id);
  }

  /**
   * Save or update.
   *
   * @param visit the visit
   * @return the routine visit
   * @see com.telenoetica.service.BaseService#saveOrUpdate(com.telenoetica.jpa.entities.BaseEntity)
   */
  @Override
  public RoutineVisit saveOrUpdate(final RoutineVisit visit) {
    if (visit.getUser() == null) {
      User user = getUser(visit.getUserId());
      visit.setUser(user);
    }

    if (visit.getSite() == null) {
      Site site = getSite(visit.getSiteId());
      visit.setSite(site);
    } else {
      throw new ApplicationServiceException(
          "Site is required for creating a Routine Visit");
    }

    return routineVisitDAO.save(visit);
  }

  /**
   * Delete.
   * 
   * @param baseEntity
   *            the base entity
   * @see com.telenoetica.service.BaseService#delete(com.telenoetica.jpa.entities.BaseEntity)
   */
  @Override
  public void delete(final RoutineVisit baseEntity) {
    routineVisitDAO.delete(baseEntity);

  }

  /**
   * Gets the visits.
   * 
   * @param pageNumber
   *            the page number
   * @return the visits
   * @see com.telenoetica.service.RoutineVisitService#getVisits(java.lang.Integer)
   */
  @Override
  public Page<RoutineVisit> getVisits(final Integer pageNumber) {

    PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
      Sort.Direction.DESC, "createdAt");
    return routineVisitDAO.findAll(request);
  }

  /**
   * Gets the visits.
   * 
   * @return the visits
   * @see com.telenoetica.service.RoutineVisitService#getVisits()
   */
  @Override
  public List<RoutineVisit> getVisits() {
    return routineVisitDAO.findAll();
  }

  /**
   * Find all.
   * 
   * @param page
   *            the page
   * @param rows
   *            the rows
   * @param sortOrder
   *            the sort order
   * @param orderByField
   *            the order by field
   * @return the page
   * @see com.telenoetica.service.RoutineVisitService#findALL(int, int,
   *      java.lang.String, java.lang.String)
   */
  @Override
  public Page<RoutineVisit> findALL(final int page, final int rows,
    final String sortOrder, String orderByField) {
    if ("userId".equals(orderByField)) {
      orderByField = "user.userName";
    } else if ("siteId".equals(orderByField)) {
      orderByField = "site.name";
    }
    return routineVisitDAO.findAll(ServiceUtil.getPage(page, rows,
      sortOrder, orderByField));
  }

  /**
   * Find all.
   * 
   * @param page
   *            the page
   * @param rows
   *            the rows
   * @param predicate
   *            the predicate
   * @param params
   *            the params
   * @return the page
   * @see com.telenoetica.service.RoutineVisitService#findALL(int, int,
   *      java.lang.String, java.util.Map)
   */
  @Override
  public Page<RoutineVisit> findALL(final int page, final int rows,
    final String predicate, final Map<String, Object> params) {
    String ejbql = "from RoutineVisit where " + predicate;
    return genericQueryExecutorDAO.executeQuery(ejbql, RoutineVisit.class,
      params, page, rows);
  }

  /**
   * Export Routine Visit.
   * 
   * @param filterPredicate
   *            the filter predicate
   * @param paramObject
   *            the param object
   * @param httpServletResponse
   *            the http servlet response
   * @param attachmentFileName
   *            the attachment file name
   * @see com.telenoetica.service.RoutineVisitService#exportReport(java.lang.String,
   *      java.util.Map, javax.servlet.http.HttpServletResponse,
   *      java.lang.String)
   */
  @Override
  public void exportReport(final String filterPredicate,
      final Map<String, Object> paramObject,
      final HttpServletResponse httpServletResponse,
      final String attachmentFileName) {

    // 1. Create new workbook
    HSSFWorkbook workbook = new HSSFWorkbook();

    // 2. Create new worksheet
    HSSFSheet worksheet = workbook.createSheet("routine-visit");

    // 3.create coulmn headers
    List<String> excelColumns = new ArrayList<String>(
        Arrays.asList(ROUTINE_VIST_EXCEL_COLUMN_LIST));
    List<RoutineVisit> routineVisits = null;
    // step 5 get entities
    if (StringUtils.isBlank(filterPredicate)) {
      routineVisits = getVisits();
    } else {
      String ejbql = "from RoutineVisit where " + filterPredicate;
      routineVisits = genericQueryExecutorDAO.executeQuery(ejbql,
        RoutineVisit.class, paramObject);
    }

    // step 6 populate values as per the headings
    List<List<Object>> targetValues = new ArrayList<List<Object>>();
    for (RoutineVisit routineVisit : routineVisits) {
      List<Object> values = new ArrayList<Object>();
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getAccessCode()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit.getUserId()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit.getSiteId()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getCreatedAt()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getDieselLevelT1()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getDieselLevelT2()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getRunHourGen1()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getRunHourGen2()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getVoltageNrVolts()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getVoltageNyVolts()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getVoltageNbVolts()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getLoadRPhaseAmps()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getLoadYPhaseAmps()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getLoadBPhaseAmps()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getRectifierOpVoltage()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getRectifierOpCurrentAmp()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getBatteryCapcityV()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getBatteryCapcityAh()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getAtsFunctional()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getAtsSysncronising()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getDrnBookAtSite()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getDieselLogBookMaintained()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getAirconShelter1Cooling()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getAirconShelter2Cooling()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getAirconShelter3Cooling()));
      values.add(ServiceUtil.checkAndReturnValue(routineVisit
        .getAirconShelter4Cooling()));
      targetValues.add(values);
    }

    // step 7 initialize renderer model
    ExcelRendererModel excelRendererModel = new ExcelRendererModel(
      worksheet, 5000, excelColumns, "Routine Visit");

    // step 8 invoke layout service
    excelLayoutService.buildReport(excelRendererModel);

    // step 9 fill report content
    excelFillerService.fillReport(excelRendererModel, targetValues);

    // step 10 write report
    ExcelWriter.write(httpServletResponse, workbook, attachmentFileName);

  }

  /**
   * Find record count.
   *
   * @param params the params
   * @return the long
   * @see com.telenoetica.service.RoutineVisitService#findRecordCount(java.util.Map)
   */
  @Override
  public long findRecordCount(final Map<String, Object> params) {

    String ejbql = "select count(*) from RoutineVisit where createdAt >= :startDate AND createdAt < :endDate";
    return genericQueryExecutorDAO.findCount(ejbql, params);
  }

}
