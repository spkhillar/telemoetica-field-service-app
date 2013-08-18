/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.util.model;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class HomeAndroidObject.
 * @author Shiv Prasad Khillar
 */
@JsonAutoDetect(JsonMethod.NONE)
public class HomeAndroidObject {

  /** The sites. */
  @JsonProperty
  private List<String> sites = new ArrayList<String>(1150);

  /** The spares. */
  @JsonProperty
  private List<String> spares = new ArrayList<String>(34);

  /** The clients. */
  @JsonProperty
  private List<String> clients = new ArrayList<String>(50);

  /** The faults. */
  @JsonProperty
  private List<String> faults = new ArrayList<String>(10);

  /** The maintenance categories. */
  @JsonProperty
  private List<String> maintenanceCategories = new ArrayList<String>(10);

  /** The maintenance categories. */
  @JsonProperty
  private List<String> dieselVendors = new ArrayList<String>(10);

  /**
   * Instantiates a new home android object.
   */
  public HomeAndroidObject() {
    super();
  }

  /**
   * Instantiates a new home android object.
   *
   * @param sites the sites
   * @param spares the spares
   * @param clients the clients
   * @param faults the faults
   * @param maintenanceCategories the maintenance categories
   */
  public HomeAndroidObject(final List<String> sites, final List<String> spares, final List<String> clients, final List<String> faults,
      final List<String> maintenanceCategories,final List<String> dieselVendors ) {
    super();
    this.sites = sites;
    this.spares = spares;
    this.clients = clients;
    this.faults = faults;
    this.maintenanceCategories = maintenanceCategories;
    this.dieselVendors = dieselVendors;
  }



  /**
   * Gets the sites.
   *
   * @return the sites
   */
  public List<String> getSites() {
    return sites;
  }

  /**
   * Sets the sites.
   *
   * @param sites the new sites
   */
  public void setSites(final List<String> sites) {
    this.sites = sites;
  }

  /**
   * Gets the spares.
   *
   * @return the spares
   */
  public List<String> getSpares() {
    return spares;
  }

  /**
   * Sets the spares.
   *
   * @param spares the new spares
   */
  public void setSpares(final List<String> spares) {
    this.spares = spares;
  }

  /**
   * Gets the clients.
   *
   * @return the clients
   */
  public List<String> getClients() {
    return clients;
  }

  /**
   * Sets the clients.
   *
   * @param clients the new clients
   */
  public void setClients(final List<String> clients) {
    this.clients = clients;
  }

  /**
   * Gets the faults.
   *
   * @return the faults
   */
  public List<String> getFaults() {
    return faults;
  }

  /**
   * Sets the faults.
   *
   * @param faults the new faults
   */
  public void setFaults(final List<String> faults) {
    this.faults = faults;
  }

  /**
   * Gets the maintenance categories.
   *
   * @return the maintenance categories
   */
  public List<String> getMaintenanceCategories() {
    return maintenanceCategories;
  }

  /**
   * Sets the maintenance categories.
   *
   * @param maintenanceCategories the new maintenance categories
   */
  public void setMaintenanceCategories(final List<String> maintenanceCategories) {
    this.maintenanceCategories = maintenanceCategories;
  }



  /**
   * @return the dieselVendors
   */
  public List<String> getDieselVendors() {
    return dieselVendors;
  }

  /**
   * @param dieselVendors the dieselVendors to set
   */
  public void setDieselVendors(final List<String> dieselVendors) {
    this.dieselVendors = dieselVendors;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("HomeAndroidObject [");
    if (sites != null) {
      builder.append("sites=");
      builder.append(sites.size());
      builder.append(", ");
    }
    if (spares != null) {
      builder.append("spares=");
      builder.append(spares.size());
      builder.append(", ");
    }
    if (clients != null) {
      builder.append("clients=");
      builder.append(clients.size());
      builder.append(", ");
    }
    if (faults != null) {
      builder.append("faults=");
      builder.append(faults.size());
      builder.append(", ");
    }
    if (maintenanceCategories != null) {
      builder.append("maintenanceCategories=");
      builder.append(maintenanceCategories.size());
      builder.append(", ");
    }
    if (dieselVendors != null) {
      builder.append("dieselVendors=");
      builder.append(dieselVendors.size());
    }
    builder.append("]");
    return builder.toString();
  }


}
