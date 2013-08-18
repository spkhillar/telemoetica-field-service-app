/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import com.telenoetica.jpa.entities.CallOutVisit;

/**
 * The Interface CallOutVisitService.
 */
public interface CallOutVisitService extends BaseService<CallOutVisit> {

  /**
   * Gets the visits.
   * 
   * @param pageNumber
   *            the page number
   * @return the visits
   */
  public Page<CallOutVisit> getCallOutVisits(Integer pageNumber);

  /**
   * Gets the visits.
   * 
   * @return the visits
   */
  List<CallOutVisit> getVisits();

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
  Page<CallOutVisit> findALL(int page, int rows, String sortOrder,
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
  Page<CallOutVisit> findALL(int page, int rows, String predicate,
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
   * Find record count.
   *
   * @param params the params
   * @return the long
   */
  long findRecordCount(Map<String, Object> params);
}
