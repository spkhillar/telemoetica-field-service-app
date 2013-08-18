/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

import com.telenoetica.jpa.entities.CallOutVisit;
import com.telenoetica.jpa.entities.DieselVisit;
import com.telenoetica.jpa.entities.MaintenanceVisit;
import com.telenoetica.jpa.entities.RoutineVisit;

/**
 * The Class WebHomeData.
 * @author Shiv Prasad Khillar
 */
@JsonAutoDetect(JsonMethod.NONE)
public class WebHomeData {

  /** The chart data. */
  @JsonProperty
  private List<Integer> chartData = new ArrayList<Integer>();

  /** The maintenance visits. */
  @JsonProperty
  private List<MaintenanceVisit> maintenanceVisits = new ArrayList<MaintenanceVisit>();

  /** The call out visits. */
  @JsonProperty
  private List<CallOutVisit> callOutVisits = new ArrayList<CallOutVisit>();

  /** The routine visits. */
  @JsonProperty
  private List<RoutineVisit> routineVisits = new ArrayList<RoutineVisit>();

  /** The diesel visits. */
  @JsonProperty
  private List<DieselVisit> dieselVisits = new ArrayList<DieselVisit>();



  /**
   * Instantiates a new web home data.
   */
  public WebHomeData() {
    super();
  }

  /**
   * Instantiates a new web home data.
   *
   * @param chartData the chart data
   * @param maintenanceVisits the maintenance visits
   * @param callOutVisits the call out visits
   * @param routineVisits the routine visits
   * @param dieselVisits the diesel visits
   */
  public WebHomeData(final List<Integer> chartData, final List<MaintenanceVisit> maintenanceVisits,
      final List<CallOutVisit> callOutVisits, final List<RoutineVisit> routineVisits, final List<DieselVisit> dieselVisits) {
    super();
    this.chartData = chartData;
    this.maintenanceVisits = maintenanceVisits;
    this.callOutVisits = callOutVisits;
    this.routineVisits = routineVisits;
    this.dieselVisits = dieselVisits;
  }

  /**
   * Gets the chart data.
   *
   * @return the chart data
   */
  public List<Integer> getChartData() {
    return chartData;
  }

  /**
   * Sets the chart data.
   *
   * @param chartData the new chart data
   */
  public void setChartData(final List<Integer> chartData) {
    this.chartData = chartData;
  }

  /**
   * Gets the maintenance visits.
   *
   * @return the maintenance visits
   */
  public List<MaintenanceVisit> getMaintenanceVisits() {
    return maintenanceVisits;
  }

  /**
   * Sets the maintenance visits.
   *
   * @param maintenanceVisits the new maintenance visits
   */
  public void setMaintenanceVisits(final List<MaintenanceVisit> maintenanceVisits) {
    this.maintenanceVisits = maintenanceVisits;
  }

  /**
   * Gets the call out visits.
   *
   * @return the call out visits
   */
  public List<CallOutVisit> getCallOutVisits() {
    return callOutVisits;
  }

  /**
   * Sets the call out visits.
   *
   * @param callOutVisits the new call out visits
   */
  public void setCallOutVisits(final List<CallOutVisit> callOutVisits) {
    this.callOutVisits = callOutVisits;
  }

  /**
   * Gets the routine visits.
   *
   * @return the routine visits
   */
  public List<RoutineVisit> getRoutineVisits() {
    return routineVisits;
  }

  /**
   * Sets the routine visits.
   *
   * @param routineVisits the new routine visits
   */
  public void setRoutineVisits(final List<RoutineVisit> routineVisits) {
    this.routineVisits = routineVisits;
  }

  /**
   * Gets the diesel visits.
   *
   * @return the diesel visits
   */
  public List<DieselVisit> getDieselVisits() {
    return dieselVisits;
  }

  /**
   * Sets the diesel visits.
   *
   * @param dieselVisits the new diesel visits
   */
  public void setDieselVisits(final List<DieselVisit> dieselVisits) {
    this.dieselVisits = dieselVisits;
  }



}
