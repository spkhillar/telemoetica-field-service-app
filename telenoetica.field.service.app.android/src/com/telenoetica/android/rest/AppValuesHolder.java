/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class AppValuesHolder.
 */
public class AppValuesHolder {

  /** The Constant DEFAULT_ITEM. */
  private static final String[] DEFAULT_ITEM = new String[] { "Browse & Select" };

  /** The sites. */
  private static List<String> sites = new ArrayList<String>(Arrays.asList(DEFAULT_ITEM));

  /** The spares. */
  private static List<String> spares = new ArrayList<String>(Arrays.asList(DEFAULT_ITEM));;

  /** The faults. */
  private static List<String> faults = new ArrayList<String>(Arrays.asList(DEFAULT_ITEM));;

  /** The clients. */
  private static List<String> clients = new ArrayList<String>(Arrays.asList(DEFAULT_ITEM));;

  /** The maintenance categories. */
  private static List<String> maintenanceCategories = new ArrayList<String>(Arrays.asList(DEFAULT_ITEM));

  /** The diesel vendors. */
  private static List<String> dieselVendors = new ArrayList<String>(Arrays.asList(DEFAULT_ITEM));

  /** The current user. */
  private static String currentUser;

  /** The current user password. */
  private static String currentUserPassword;

  /** The host. */
  private static String host = "http://197.242.243.7:8080/fieldapp";

  /** The send to server count map. */
  private static Map<String, Long> sendToServerCountMap = new HashMap<String, Long>();

  /**
   * Gets the sites.
   *
   * @return the sites
   */
  public static List<String> getSites() {
    return sites;
  }

  /**
   * Sets the sites.
   *
   * @param sites the new sites
   */
  public static void setSites(final List<String> sites) {
    AppValuesHolder.sites.addAll(sites);
  }

  /**
   * Gets the spares.
   *
   * @return the spares
   */
  public static List<String> getSpares() {
    return spares;
  }

  /**
   * Sets the spares.
   *
   * @param spares the new spares
   */
  public static void setSpares(final List<String> spares) {
    AppValuesHolder.spares.addAll(spares);
  }

  /**
   * Gets the faults.
   *
   * @return the faults
   */
  public static List<String> getFaults() {
    return faults;
  }

  /**
   * Sets the faults.
   *
   * @param faults the new faults
   */
  public static void setFaults(final List<String> faults) {
    AppValuesHolder.faults.addAll(faults);
  }

  /**
   * Gets the clients.
   *
   * @return the clients
   */
  public static List<String> getClients() {
    return clients;
  }

  /**
   * Sets the clients.
   *
   * @param clients the new clients
   */
  public static void setClients(final List<String> clients) {
    AppValuesHolder.clients.addAll(clients);
  }

  /**
   * Gets the maintenance categories.
   *
   * @return the maintenance categories
   */
  public static List<String> getMaintenanceCategories() {
    return maintenanceCategories;
  }

  /**
   * Sets the maintenance categories.
   *
   * @param maintenanceCategories the new maintenance categories
   */
  public static void setMaintenanceCategories(final List<String> maintenanceCategories) {
    AppValuesHolder.maintenanceCategories.addAll(maintenanceCategories);
  }

  /**
   * Gets the current user.
   *
   * @return the current user
   */
  public static String getCurrentUser() {
    return currentUser;
  }

  /**
   * Sets the current user.
   *
   * @param currentUser the new current user
   */
  public static void setCurrentUser(final String currentUser) {
    AppValuesHolder.currentUser = currentUser;
  }

  /**
   * Gets the current user password.
   *
   * @return the current user password
   */
  public static String getCurrentUserPassword() {
    return currentUserPassword;
  }

  /**
   * Sets the current user password.
   *
   * @param currentUserPassword the new current user password
   */
  public static void setCurrentUserPassword(final String currentUserPassword) {
    AppValuesHolder.currentUserPassword = currentUserPassword;
  }

  /**
   * Gets the diesel vendors.
   *
   * @return the dieselVendors
   */
  public static List<String> getDieselVendors() {
    return dieselVendors;
  }

  /**
   * Sets the diesel vendors.
   *
   * @param dieselVendors the dieselVendors to set
   */
  public static void setDieselVendors(final List<String> dieselVendors) {
    AppValuesHolder.dieselVendors.addAll(dieselVendors);
  }

  /**
   * Gets the host.
   *
   * @return the host
   */
  public static String getHost() {
    return host;
  }

  /**
   * Sets the host.
   *
   * @param host the new host
   */
  public static void setHost(final String host) {
    AppValuesHolder.host = host;
  }

  /**
   * Adds the sent record.
   *
   * @param clazz the clazz
   */
  public static void addSentRecord(final String clazz) {
    Long currentCount = AppValuesHolder.sendToServerCountMap.get(clazz);
    if (currentCount == null) {
      AppValuesHolder.sendToServerCountMap.put(clazz, 1l);
    } else {
      AppValuesHolder.sendToServerCountMap.put(clazz, currentCount.longValue() + 1l);
    }
  }

  /**
   * Clear sent record count.
   */
  public static void clearSentRecordCount() {
    AppValuesHolder.sendToServerCountMap.clear();
  }

  /**
   * Gets the sent record count map.
   *
   * @return the sent record count map
   */
  public static Map<String, Long> getSentRecordCountMap() {
    return AppValuesHolder.sendToServerCountMap;
  }

  /**
   * Reset config data.
   */
  public static void resetConfigData() {
    AppValuesHolder.clients.clear();
    AppValuesHolder.clients.add(DEFAULT_ITEM[0]);
    AppValuesHolder.faults.clear();
    AppValuesHolder.faults.add(DEFAULT_ITEM[0]);
    AppValuesHolder.maintenanceCategories.clear();
    AppValuesHolder.maintenanceCategories.add(DEFAULT_ITEM[0]);
    AppValuesHolder.sites.clear();
    AppValuesHolder.sites.add(DEFAULT_ITEM[0]);
    AppValuesHolder.spares.clear();
    AppValuesHolder.spares.add(DEFAULT_ITEM[0]);
    AppValuesHolder.dieselVendors.clear();
    AppValuesHolder.dieselVendors.add(DEFAULT_ITEM[0]);
  }

}
