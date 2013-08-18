/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import com.telenoetica.jpa.entities.DieselVisit;
import com.telenoetica.jpa.entities.Site;


/**
 * @author satyam.srivastava
 * 
 */
public interface DieselVisitService extends BaseService<DieselVisit> {

  /**
   * Gets the visits.
   * 
   * @return the visits
   */
  List<DieselVisit> getVisits();

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
   */
  Page<DieselVisit> findALL(int page, int rows, String sortOrder,
    String orderByField);

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
   */
  Page<DieselVisit> findALL(int page, int rows, String predicate,
    Map<String, Object> params);

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
   */
  void exportReport(String filterPredicate, Map<String, Object> paramObject,
      HttpServletResponse httpServletResponse, String attachmentFileName);

  /**
   * @param site
   * @return
   */
  public List<DieselVisit> findBySiteAndCreatedAtBetween(final Site site,Date forDate);

  /**
   * @param params
   * @return
   */
  public long findRecordCount(Map<String, Object> params);

}
