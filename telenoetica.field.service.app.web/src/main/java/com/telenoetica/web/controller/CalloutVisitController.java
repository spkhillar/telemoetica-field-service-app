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

import com.telenoetica.jpa.entities.CallOutVisit;
import com.telenoetica.service.CallOutVisitService;
import com.telenoetica.web.rest.RestResponse;
import com.telenoetica.web.util.DomainObjectMapper;
import com.telenoetica.web.util.JqGridResponse;

/**
 * The Class CalloutVisitController.
 * @author Satyam
 */
@Controller
@RequestMapping(value = "/callout")
@SessionAttributes("calloutForm")
public class CalloutVisitController extends AbstractJqGridFilterController {

  /** The callout visit service. */
  @Autowired
  private CallOutVisitService callOutVisitService;

  /** The excluded props in filter. */
  private final String[] excludedPropsInFilter = new String[] { "userId",
      "siteId", "createdAt", "timeComplainReceived", "timeReachedToSite",
  "timeFaultResolved" };

  /** The Constant excludedPropQueryMapping. */
  private static final Map<String, String> excludedPropQueryMapping = new HashMap<String, String>();

  /** The Constant excludedPropOrderMapping. */
  private static final Map<String, String> excludedPropOrderMapping = new HashMap<String, String>();
  static {
    excludedPropQueryMapping.put("userId", "user.userName");
    excludedPropQueryMapping.put("siteId", "site.name");
    excludedPropQueryMapping.put("createdAt", "date(createdAt)");
    excludedPropQueryMapping.put("timeComplainReceived", "date(createdAt)");
    excludedPropQueryMapping.put("timeReachedToSite", "date(createdAt)");
    excludedPropQueryMapping.put("timeFaultResolved", "date(createdAt)");
    excludedPropOrderMapping.put("userId", "user.userName");
    excludedPropOrderMapping.put("siteId", "site.name");
  }

  /**
   * Creates the form bean.
   *
   * @return the call out visit
   */
  @ModelAttribute("calloutForm")
  public CallOutVisit createFormBean() {
    return new CallOutVisit();
  }

  /**
   * Save.
   *
   * @param callOutVisit the call out visit
   * @return the string
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public String save(final CallOutVisit callOutVisit) {
    callOutVisit.setUserId(getCurrentLoggedinUserName());
    CallOutVisit savedCallOutVisit = callOutVisitService
        .saveOrUpdate(callOutVisit);
    return "Saved Successfuly with id:" + savedCallOutVisit.getId();
  }

  /**
   * Creates the.
   *
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = "/new")
  public String create(final Model model) {
    model.addAttribute("currentViewTitle", "Call-Out Visit");
    return "callout.new";
  }

  /**
   * Save api.
   *
   * @param callOutVisit the call out visit
   * @return the rest response
   */
  @RequestMapping(value = "/rest", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
  @ResponseBody
  public RestResponse saveApi(@RequestBody final CallOutVisit callOutVisit) {
    CallOutVisit savedCallOutVisit = callOutVisitService
        .saveOrUpdate(callOutVisit);
    RestResponse response = new RestResponse(0,
      "Saved Successfuly with id:" + savedCallOutVisit.getId());
    return response;
  }

  /**
   * Save api.
   *
   * @param id the id
   * @return the call out visit
   */
  @RequestMapping(value = "/rest/{id}", method = RequestMethod.GET, produces = { "application/json" })
  @ResponseBody
  public CallOutVisit saveApi(@PathVariable final Long id) {
    CallOutVisit callOutVisit = callOutVisitService.retrieve(id);
    return callOutVisit;
  }

  /**
   * Gets the users page.
   *
   * @param model the model
   * @return the users page
   */
  @RequestMapping(value = "/list")
  public String getDieselPage(final Model model) {
    return "callout.list";
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
  JqGridResponse<CallOutVisit> records(
    @RequestParam("_search") final Boolean search,
    @RequestParam(value = "filters", required = false) final String filters,
    @RequestParam(value = "page", required = false) final Integer page,
    @RequestParam(value = "rows", required = false) final Integer rows,
    @RequestParam(value = "sidx", required = false) final String sidx,
    @RequestParam(value = "sord", required = false) final String sord) {
    Page<CallOutVisit> callOutVisits = null;

    if (search) {

      Map<String, Object> paramObject = new LinkedHashMap<String, Object>();
      String filterPredicate = getFilteredRecords(filters, sord, sidx,
        paramObject, CallOutVisit.class);
      callOutVisits = callOutVisitService.findALL(page, rows,
        filterPredicate, paramObject);
    } else {
      callOutVisits = callOutVisitService.findALL(page, rows, sord, sidx);
    }
    List<Object> list = DomainObjectMapper.listEntities(callOutVisits);
    JqGridResponse<CallOutVisit> response = new JqGridResponse<CallOutVisit>();
    response.setRows(list);
    response.setRecords(Long.valueOf(callOutVisits.getTotalElements())
      .toString());
    response.setTotal(Integer.valueOf(callOutVisits.getTotalPages())
      .toString());
    response.setPage(Integer.valueOf(callOutVisits.getNumber() + 1)
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
        paramObject, CallOutVisit.class);
    }
    callOutVisitService.exportReport(filterPredicate, paramObject,
      httpServletResponse, "callOut-visit.xls");
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.telenoetica.web.controller.AbstractJqGridFilterController#
   * getFilterExclusionProperties()
   */
  @Override
  public String[] getFilterExclusionProperties() {
    // TODO Auto-generated method stub
    return excludedPropsInFilter;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.telenoetica.web.controller.AbstractJqGridFilterController#
   * getFilterExcludedPropertyQueryMapping()
   */
  @Override
  public Map<String, String> getFilterExcludedPropertyQueryMapping() {
    return excludedPropQueryMapping;

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.telenoetica.web.controller.AbstractJqGridFilterController#
   * getFilterExcludedPropertyOrderMapping()
   */
  @Override
  public Map<String, String> getFilterExcludedPropertyOrderMapping() {
    return excludedPropOrderMapping;
  }

}
