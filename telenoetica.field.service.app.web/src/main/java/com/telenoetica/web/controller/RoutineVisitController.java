/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.web.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.telenoetica.jpa.entities.RoutineVisit;
import com.telenoetica.service.RoutineVisitService;
import com.telenoetica.web.rest.RestResponse;
import com.telenoetica.web.util.DomainObjectMapper;
import com.telenoetica.web.util.JqGridResponse;

/**
 * The Class RoutineVisitController.
 * 
 * @author Shiv Prasad Khillar
 */
@Controller
@RequestMapping(value = "/routine")
@SessionAttributes("routineForm")
public class RoutineVisitController extends AbstractJqGridFilterController {

  /** The routine visit service. */
  @Autowired
  private RoutineVisitService routineVisitService;

  /** The excluded props in filter. */
  private final String[] excludedPropsInFilter = new String[] { "userId",
      "siteId", "createdAt" };

  /** The Constant excludedPropQueryMapping. */
  private static final Map<String, String> excludedPropQueryMapping = new HashMap<String, String>();

  /** The Constant excludedPropOrderMapping. */
  private static final Map<String, String> excludedPropOrderMapping = new HashMap<String, String>();
  static {
    excludedPropQueryMapping.put("userId", "user.userName");
    excludedPropQueryMapping.put("siteId", "site.name");
    excludedPropQueryMapping.put("createdAt", "date(createdAt)");

    excludedPropOrderMapping.put("userId", "user.userName");
    excludedPropOrderMapping.put("siteId", "site.name");
  }

  /**
   * Creates the form bean.
   * 
   * @return the routine visit
   */
  @ModelAttribute("routineForm")
  public RoutineVisit createFormBean() {
    return new RoutineVisit();
  }

  /**
   * Save.
   * 
   * @param routineVisit
   *            the routine visit
   * @return the string
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public String save(final RoutineVisit routineVisit) {
    routineVisit.setUserId(getCurrentLoggedinUserName());
    RoutineVisit savedRoutineVisit = routineVisitService
        .saveOrUpdate(routineVisit);
    return "Saved Successfuly with id:" + savedRoutineVisit.getId();
  }

  /**
   * Creates the.
   * 
   * @return the string
   */
  @RequestMapping(value = "/new")
  public String create(final Model model) {
    model.addAttribute("currentViewTitle", "Routine Visit");
    return "routine.new";
  }

  /**
   * Save api.
   * 
   * @param routineVisit
   *            the routine visit
   * @return the rest response
   */
  @RequestMapping(value = "/rest", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
  @ResponseBody
  public RestResponse saveApi(@RequestBody final RoutineVisit routineVisit) {
    RoutineVisit savedRoutineVisit = routineVisitService
        .saveOrUpdate(routineVisit);
    RestResponse response = new RestResponse(0,
      "Saved Successfuly with id:" + savedRoutineVisit.getId());
    return response;
  }

  /**
   * Save api.
   * 
   * @param id
   *            the id
   * @return the routine visit
   */
  @RequestMapping(value = "/rest/{id}", method = RequestMethod.GET, produces = { "application/json" })
  @ResponseBody
  public RoutineVisit saveApi(@PathVariable final Long id) {
    RoutineVisit routineVisit = routineVisitService.retrieve(id);
    return routineVisit;
  }

  /**
   * Gets the users page.
   * 
   * @return the users page
   */
  @RequestMapping(value = "/list")
  public String getRoutinePage(final Model model) {
    return "routine.list";
  }

  /**
   * Records.
   * 
   * @param search
   *            the search
   * @param filters
   *            the filters
   * @param page
   *            the page
   * @param rows
   *            the rows
   * @param sidx
   *            the sidx
   * @param sord
   *            the sord
   * @return the jq grid response
   */
  @RequestMapping(value = "/records", produces = "application/json")
  public @ResponseBody
  JqGridResponse<RoutineVisit> records(
    @RequestParam("_search") final Boolean search,
    @RequestParam(value = "filters", required = false) final String filters,
    @RequestParam(value = "page", required = false) final Integer page,
    @RequestParam(value = "rows", required = false) final Integer rows,
    @RequestParam(value = "sidx", required = false) final String sidx,
    @RequestParam(value = "sord", required = false) final String sord) {
    Page<RoutineVisit> routineVisits = null;

    if (search) {

      Map<String, Object> paramObject = new LinkedHashMap<String, Object>();
      String filterPredicate = getFilteredRecords(filters, sord, sidx,
        paramObject, RoutineVisit.class);
      routineVisits = routineVisitService.findALL(page, rows,
        filterPredicate, paramObject);
    } else {
      routineVisits = routineVisitService.findALL(page, rows, sord, sidx);
    }
    List<Object> list = DomainObjectMapper.listEntities(routineVisits);
    JqGridResponse<RoutineVisit> response = new JqGridResponse<RoutineVisit>();
    response.setRows(list);
    response.setRecords(Long.valueOf(routineVisits.getTotalElements())
      .toString());
    response.setTotal(Integer.valueOf(routineVisits.getTotalPages())
      .toString());
    response.setPage(Integer.valueOf(routineVisits.getNumber() + 1)
      .toString());
    return response;
  }

  /**
   * Export.
   * 
   * @param search
   *            the search
   * @param filters
   *            the filters
   * @param page
   *            the page
   * @param rows
   *            the rows
   * @param sidx
   *            the sidx
   * @param sord
   *            the sord
   * @param httpServletResponse
   *            the http servlet response
   */
  @RequestMapping(value = "/export")
  public void export(
      @RequestParam("_search") final Boolean search,
      @RequestParam(value = "filters", required = false) final String filters,
      @RequestParam(value = "page", required = false) final Integer page,
      @RequestParam(value = "rows", required = false) final Integer rows,
      @RequestParam(value = "sidx", required = false) final String sidx,
      @RequestParam(value = "sord", required = false) final String sord,
      final HttpServletResponse httpServletResponse) {
    String filterPredicate = null;
    Map<String, Object> paramObject = null;
    if (search) {
      paramObject = new LinkedHashMap<String, Object>();
      filterPredicate = getFilteredRecords(filters, sord, sidx,
        paramObject, RoutineVisit.class);
    }
    routineVisitService.exportReport(filterPredicate, paramObject,
      httpServletResponse, "routine-visit.xls");
  }

  /**
   * Gets the filter exclusion properties.
   * 
   * @return the filter exclusion properties
   * @see com.telenoetica.web.controller.AbstractJqGridFilterController#getFilterExclusionProperties()
   */
  @Override
  public String[] getFilterExclusionProperties() {
    return excludedPropsInFilter;
  }

  /**
   * Gets the filter excluded property query mapping.
   * 
   * @return the filter excluded property query mapping
   * @see com.telenoetica.web.controller.AbstractJqGridFilterController#getFilterExcludedPropertyQueryMapping()
   */
  @Override
  public Map<String, String> getFilterExcludedPropertyQueryMapping() {
    return excludedPropQueryMapping;
  }

  /**
   * Gets the filter excluded property order mapping.
   * 
   * @return the filter excluded property order mapping
   * @see com.telenoetica.web.controller.AbstractJqGridFilterController#getFilterExcludedPropertyOrderMapping()
   */
  @Override
  public Map<String, String> getFilterExcludedPropertyOrderMapping() {
    return excludedPropOrderMapping;
  }
}
