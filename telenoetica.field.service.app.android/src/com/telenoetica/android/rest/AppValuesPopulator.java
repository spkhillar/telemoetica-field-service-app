/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.rest;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.telenoetica.util.model.HomeAndroidObject;

/**
 * The Class AppValuesPopulator.
 */
public final class AppValuesPopulator {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(AppValuesPopulator.class);

  /**
   * Instantiates a new app values populator.
   */
  private AppValuesPopulator() {

  }

  /**
   * Populate values.
   *
   * @param userName the user name
   * @param password the password
   * @throws JSONException the jSON exception
   */
  public static void populateValues(final String userName, final String password) throws JSONException {
    String url = AppValuesHolder.getHost() + "/rest/home";
    HomeAndroidObject homeAndroidObject =
        RestClient.INSTANCE.executeRest(url, userName, password, HttpMethod.GET, null, HomeAndroidObject.class,
          MediaType.APPLICATION_JSON);
    LOGGER.debug("...Home Object..." + homeAndroidObject);
    if (homeAndroidObject != null) {
      AppValuesHolder.setClients(homeAndroidObject.getClients());
      AppValuesHolder.setFaults(homeAndroidObject.getFaults());
      AppValuesHolder.setMaintenanceCategories(homeAndroidObject.getMaintenanceCategories());
      AppValuesHolder.setSites(homeAndroidObject.getSites());
      AppValuesHolder.setSpares(homeAndroidObject.getSpares());
      AppValuesHolder.setDieselVendors(homeAndroidObject.getDieselVendors());
    }
  }
}
