/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.MaintenanceVisit;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.jpa.repositories.GenericQueryExecutorDAO;
import com.telenoetica.jpa.repositories.MaintenanceVisitDAO;
import com.telenoetica.service.MaintenanceVisitService;
import com.telenoetica.service.excel.ExcelFillerService;
import com.telenoetica.service.excel.ExcelLayoutService;
import com.telenoetica.service.util.ApplicationServiceException;
import com.telenoetica.service.util.ExcelRendererModel;
import com.telenoetica.service.util.ExcelWriter;
import com.telenoetica.service.util.ServiceUtil;

/**
 * The Class MaintenanceVisitServiceImpl.
 */
@Service("maintenanceVisitService")
public class MaintenanceVisitServiceImpl extends AbstractBaseService implements MaintenanceVisitService {

  /** The maintenance visit dao. */
  @Autowired
  private MaintenanceVisitDAO maintenanceVisitDAO;

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

  /** The Constant MAINTENANCE_VIST_EXCEL_COLUMN_LIST. */
  private static final String[] MAINTENANCE_VIST_EXCEL_COLUMN_LIST = new String[] { "Access Code", "User Name", "Site",
    "Created At", "Category of maintenance", "Spares used/ Items replaced - 1", "Spares used/ Items replaced - 2",
    "Spares used/ Items replaced - 3", "Spares used/ Items replaced - 4", "Spares used/ Items replaced - 5",
    "Spares used/ Items replaced - 6", "Consumables used -1", "Consumables used -2", "Consumables used -3",
    "Consumables used -4", "Consumables used -5", "Consumables used -6", "Run-Hours after PM of DG1 ",
  "Run-Hours after PM of DG2" };

  /**
   * Retrieve.
   * 
   * @param id
   *          the id
   * @return the maintenance visit
   * @see com.telenoetica.service.BaseService#retrieve(java.lang.Long)
   */
  @Override
  @Transactional
  public MaintenanceVisit retrieve(final Long id) {
    return maintenanceVisitDAO.findOne(id);
  }

  /**
   * Save or update.
   * 
   * @param visit
   *          the visit
   * @return the maintenance visit
   * @see com.telenoetica.service.BaseService#saveOrUpdate(com.telenoetica.jpa.entities.BaseEntity)
   */
  @Override
  @Transactional
  public MaintenanceVisit saveOrUpdate(final MaintenanceVisit visit) {
    if (visit.getUser() == null) {
      User user = getUser(visit.getUserId());
      visit.setUser(user);
    }

    if (visit.getSite() == null) {
      Site site = getSite(visit.getSiteId());
      visit.setSite(site);
    } else {
      throw new ApplicationServiceException("Site is required for creating a Routine Visit");
    }
    return maintenanceVisitDAO.save(visit);
  }

  /**
   * Delete.
   * 
   * @param baseEntity
   *          the base entity
   * @see com.telenoetica.service.BaseService#delete(com.telenoetica.jpa.entities.BaseEntity)
   */
  @Override
  @Transactional
  public void delete(final MaintenanceVisit baseEntity) {
    maintenanceVisitDAO.delete(baseEntity);
  }

  /**
   * Gets the maintenance visits.
   * 
   * @param pageNumber
   *          the page number
   * @return the maintenance visits
   * @see com.telenoetica.service.MaintenanceVisitService#getMaintenanceVisits(java.lang.Integer)
   */
  @Override
  @Transactional
  public Page<MaintenanceVisit> getMaintenanceVisits(final Integer pageNumber) {
    PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "createdAt");
    return maintenanceVisitDAO.findAll(request);
  }

  /**
   * Gets the visits.
   * 
   * @return the visits
   * @see com.telenoetica.service.MaintenanceVisitService#getVisits()
   */
  @Override
  public List<MaintenanceVisit> getVisits() {
    // TODO Auto-generated method stub
    return maintenanceVisitDAO.findAll();
  }

  /**
   * Find all.
   * 
   * @param page
   *          the page
   * @param rows
   *          the rows
   * @param sortOrder
   *          the sort order
   * @param orderByField
   *          the order by field
   * @return the page
   * @see com.telenoetica.service.MaintenanceVisitService#findALL(int, int,
   *      java.lang.String, java.lang.String)
   */
  @Override
  public Page<MaintenanceVisit> findALL(final int page, final int rows, final String sortOrder, String orderByField) {
    if ("userId".equals(orderByField)) {
      orderByField = "user.userName";
    } else if ("siteId".equals(orderByField)) {
      orderByField = "site.name";
    }
    return maintenanceVisitDAO.findAll(ServiceUtil.getPage(page, rows, sortOrder, orderByField));
  }

  /**
   * Find all.
   * 
   * @param page
   *          the page
   * @param rows
   *          the rows
   * @param predicate
   *          the predicate
   * @param params
   *          the params
   * @return the page
   * @see com.telenoetica.service.MaintenanceVisitService#findALL(int, int,
   *      java.lang.String, java.util.Map)
   */
  @Override
  public Page<MaintenanceVisit> findALL(final int page, final int rows, final String predicate,
    final Map<String, Object> params) {
    String ejbql = "from MaintenanceVisit where " + predicate;
    return genericQueryExecutorDAO.executeQuery(ejbql, MaintenanceVisit.class, params, page, rows);
  }

  /**
   * Export report.
   * 
   * @param filterPredicate
   *          the filter predicate
   * @param paramObject
   *          the param object
   * @param httpServletResponse
   *          the http servlet response
   * @param attachmentFileName
   *          the attachment file name
   * @see com.telenoetica.service.MaintenanceVisitService#exportReport(java.lang.String,
   *      java.util.Map, javax.servlet.http.HttpServletResponse,
   *      java.lang.String)
   */
  @Override
  public void exportReport(final String filterPredicate, final Map<String, Object> paramObject,
      final HttpServletResponse httpServletResponse, final String attachmentFileName) {
    // 1. Create new workbook
    HSSFWorkbook workbook = new HSSFWorkbook();

    // 2. Create new worksheet
    HSSFSheet worksheet = workbook.createSheet("routine-visit");

    // 3.create coulmn headers
    List<String> excelColumns = new ArrayList<String>(Arrays.asList(MAINTENANCE_VIST_EXCEL_COLUMN_LIST));
    List<MaintenanceVisit> maintenanceVisits = null;
    // step 5 get entities
    if (StringUtils.isBlank(filterPredicate)) {
      maintenanceVisits = getVisits();
    } else {
      String ejbql = "from MaintenanceVisit where " + filterPredicate;
      maintenanceVisits = genericQueryExecutorDAO.executeQuery(ejbql, MaintenanceVisit.class, paramObject);
    }

    // step 6 populate values as per the headings
    List<List<Object>> targetValues = new ArrayList<List<Object>>();
    for (MaintenanceVisit maintenanceVisit : maintenanceVisits) {
      List<Object> values = new ArrayList<Object>();
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getAccessCode()));
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getUserId()));
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getSiteId()));
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getCreatedAt()));
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getCategoryOfMaintenance()));
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getSparesUsedItemsReplaced1()));
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getSparesUsedItemsReplaced2()));
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getSparesUsedItemsReplaced3()));
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getSparesUsedItemsReplaced4()));
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getSparesUsedItemsReplaced5()));
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getSparesUsedItemsReplaced6()));
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getCosumablesUsed1()));
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getCosumablesUsed2()));
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getCosumablesUsed3()));
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getCosumablesUsed4()));
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getCosumablesUsed5()));
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getCosumablesUsed6()));
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getRunHoursAfterPmdG1()));
      values.add(ServiceUtil.checkAndReturnValue(maintenanceVisit.getRunHourAfterPmdG2()));
      targetValues.add(values);
    }

    // step 7 initialize renderer model
    ExcelRendererModel excelRendererModel = new ExcelRendererModel(worksheet, 10000, excelColumns, "Maintenance Visit");

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
   * @param params
   *          the params
   * @return the long
   * @see com.telenoetica.service.MaintenanceVisitService#findRecordCount(java.util.Map)
   */
  @Override
  public long findRecordCount(final Map<String, Object> params) {

    String ejbql = "select count(*) from MaintenanceVisit where createdAt >= :startDate AND createdAt < :endDate";
    return genericQueryExecutorDAO.findCount(ejbql, params);
  }

  /**
   * Find by site and created at between.
   * 
   * @param site
   *          the site
   * @return the list
   * @see com.telenoetica.service.MaintenanceVisitService#findBySiteAndCreatedAtBetween(com.telenoetica.jpa.entities.Site)
   */
  @Override
  public List<MaintenanceVisit> findBySiteAndCreatedAtBetween(final Site site,final Date forDate) {
    Date startDate;
    Date endDate;
    Calendar currentDate = Calendar.getInstance(); // Get the current date
    if(forDate == null){
      endDate = new Date();
      endDate = DateUtils.addDays(endDate, -1);
    }else{
      endDate = forDate;
    }
    endDate = DateUtils.truncate(endDate, Calendar.DATE);
    Date finalEndDate = DateUtils.addMinutes(endDate, 59);
    finalEndDate = DateUtils.addSeconds(finalEndDate, 59);
    finalEndDate = DateUtils.addHours(finalEndDate, 23);
    currentDate.setTime(endDate);
    currentDate.set(Calendar.DAY_OF_MONTH, Calendar.getInstance()
      .getActualMinimum(Calendar.DAY_OF_MONTH));
    startDate = currentDate.getTime();
    logger.debug(" Site =" + site.getName() + ". Finding Diesel Visit Data from " + startDate + " TO " + finalEndDate);
    return maintenanceVisitDAO.findBySiteAndCreatedAtBetween(site, startDate, finalEndDate);
  }

}
