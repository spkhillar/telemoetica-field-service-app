/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.rest;

/**
 * The Interface AndroidConstants.
 */
public interface AndroidConstants {

  /** The initial status. */
  String INITIAL_STATUS = "INITIAL";

  /** The failed status. */
  String FAILED_STATUS = "FAILED";

  /** The success status. */
  String SUCCESS_STATUS = "SUCCESS";

  /** The required message. */
  String REQUIRED_MESSAGE = "Field is required";

  /** The site id message. */
  String SITE_ID_MESSAGE = "{0} does not exist in system";

  /** The range message. */
  String RANGE_MESSAGE = "{0} must be in between {1} and {2}";

  /** The routine visit save rest url. */
  String ROUTINE_VISIT_SAVE_REST_URL = "/routine/rest";

  /** The callout visit save rest url. */
  String CALLOUT_VISIT_SAVE_REST_URL = "/callout/rest";

  /** The diesel visit save rest url. */
  String DIESEL_VISIT_SAVE_REST_URL = "/diesel/rest";

  /** The maintenance visit save rest url. */
  String MAINTENANCE_VISIT_SAVE_REST_URL = "/maintenance/rest";

}
