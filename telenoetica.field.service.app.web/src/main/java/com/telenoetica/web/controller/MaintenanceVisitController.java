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

import com.telenoetica.jpa.entities.MaintenanceVisit;
import com.telenoetica.service.MaintenanceVisitService;
import com.telenoetica.web.rest.RestResponse;
import com.telenoetica.web.util.DomainObjectMapper;
import com.telenoetica.web.util.JqGridResponse;

/**
 * The Class MaintenanceVisitController.
 * @author Satyam
 */
@Controller
@RequestMapping(value = "/maintenance")
@SessionAttributes("maintenanceForm")
public class MaintenanceVisitController extends AbstractJqGridFilterController {

  /** The maintenance visit service. */
  @Autowired
  private MaintenanceVisitService maintenanceVisitService;

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
   * @return the maintenance visit
   */
  @ModelAttribute("maintenanceForm")
  public MaintenanceVisit createFormBean() {
    return new MaintenanceVisit();
  }

  /**
   * Save.
   * 
   * @param maintenanceVisit
   *            the maintenance visit
   * @return the string
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public String save(final MaintenanceVisit maintenanceVisit) {
    maintenanceVisit.setUserId(getCurrentLoggedinUserName());
    MaintenanceVisit savedMaintenanceVisit = maintenanceVisitService
        .saveOrUpdate(maintenanceVisit);
    return "Saved Successfuly with id:" + savedMaintenanceVisit.getId();
  }

  /**
   * Creates the.
   *
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = "/new")
  public String create(final Model model) {
    model.addAttribute("currentViewTitle", "Maintenance Visit");
    return "maintenance.new";
  }

  /**
   * Save api.
   * 
   * @param maintenanceVisit
   *            the maintenance visit
   * @return the rest response
   */
  @RequestMapping(value = "/rest", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
  @ResponseBody
  public RestResponse saveApi(
      @RequestBody final MaintenanceVisit maintenanceVisit) {
    MaintenanceVisit savedMaintenanceVisit = maintenanceVisitService
        .saveOrUpdate(maintenanceVisit);
    RestResponse response = new RestResponse(0,
      "Saved Successfuly with id:" + savedMaintenanceVisit.getId());
    return response;
  }

  /**
   * Save api.
   * 
   * @param id
   *            the id
   * @return the maintenance visit
   */
  @RequestMapping(value = "/rest/{id}", method = RequestMethod.GET, produces = { "application/json" })
  @ResponseBody
  public MaintenanceVisit saveApi(@PathVariable final Long id) {
    MaintenanceVisit maintenanceVisit = maintenanceVisitService
        .retrieve(id);
    return maintenanceVisit;
  }

  /**
   * Gets the users page.
   *
   * @param model the model
   * @return the users page
   */
  @RequestMapping(value = "/list")
  public String getMaintenancePage(final Model model) {
    return "maintenance.list";
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
  JqGridResponse<MaintenanceVisit> records(
    @RequestParam("_search") final Boolean search,
    @RequestParam(value = "filters", required = false) final String filters,
    @RequestParam(value = "page", required = false) final Integer page,
    @RequestParam(value = "rows", required = false) final Integer rows,
    @RequestParam(value = "sidx", required = false) final String sidx,
    @RequestParam(value = "sord", required = false) final String sord) {
    Page<MaintenanceVisit> maintenanceVisits = null;

    if (search) {

      Map<String, Object> paramObject = new LinkedHashMap<String, Object>();
      String filterPredicate = getFilteredRecords(filters, sord, sidx,
        paramObject, MaintenanceVisit.class);
      maintenanceVisits = maintenanceVisitService.findALL(page, rows,
        filterPredicate, paramObject);
    } else {
      maintenanceVisits = maintenanceVisitService.findALL(page, rows,
        sord, sidx);
    }
    List<Object> list = DomainObjectMapper.listEntities(maintenanceVisits);
    JqGridResponse<MaintenanceVisit> response = new JqGridResponse<MaintenanceVisit>();
    response.setRows(list);
    response.setRecords(Long.valueOf(maintenanceVisits.getTotalElements())
      .toString());
    response.setTotal(Integer.valueOf(maintenanceVisits.getTotalPages())
      .toString());
    response.setPage(Integer.valueOf(maintenanceVisits.getNumber() + 1)
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
        paramObject, MaintenanceVisit.class);
    }
    maintenanceVisitService.exportReport(filterPredicate, paramObject,
      httpServletResponse, "maintenance-visit.xls");
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
