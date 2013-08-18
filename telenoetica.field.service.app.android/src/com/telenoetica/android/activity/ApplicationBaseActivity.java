/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.activity;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.telenoetica.android.rest.AndroidConstants;
import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.android.sqllite.SQLiteDbHandler;
import com.telenoetica.jpa.entities.CallOutVisit;
import com.telenoetica.jpa.entities.DieselVisit;
import com.telenoetica.jpa.entities.MaintenanceVisit;
import com.telenoetica.jpa.entities.RoutineVisit;

/**
 * The Class ApplicationBaseActivity.
 */
public abstract class ApplicationBaseActivity extends Activity {

  /** The Constant LOGGER. */
  protected static final Logger LOGGER = LoggerFactory.getLogger(MainMenu.class);

  /** The sq lite db handler. */
  protected SQLiteDbHandler sqLiteDbHandler;

  /** The context. */
  protected Context context;

  /**
   * On create.
   *
   * @param savedInstanceState the saved instance state
   * @see android.app.Activity#onCreate(android.os.Bundle)
   */
  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    context = this;
    sqLiteDbHandler = new SQLiteDbHandler(this);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    initializeActivity(savedInstanceState);
  }

  /**
   * Check for user idand password.
   */
  protected void checkForUserIdandPassword() {
    if (StringUtils.isBlank(AppValuesHolder.getCurrentUser())
        || StringUtils.isBlank(AppValuesHolder.getCurrentUserPassword())) {
      Intent intent = new Intent(context, LoginActivity.class);
      startActivity(intent);
    }
  }

  /**
   * Determine path.
   *
   * @param currentClazz the current clazz
   * @return the string
   */
  public String determinePath(final Class<?> currentClazz) {

    if (RoutineVisit.class.isAssignableFrom(currentClazz)) {
      return AndroidConstants.ROUTINE_VISIT_SAVE_REST_URL;
    } else if (CallOutVisit.class.isAssignableFrom(currentClazz)) {
      return AndroidConstants.CALLOUT_VISIT_SAVE_REST_URL;
    } else if (DieselVisit.class.isAssignableFrom(currentClazz)) {
      return AndroidConstants.DIESEL_VISIT_SAVE_REST_URL;
    } else if (MaintenanceVisit.class.isAssignableFrom(currentClazz)) {
      return AndroidConstants.MAINTENANCE_VISIT_SAVE_REST_URL;
    }
    throw new RuntimeException("clazzName not configured in system");
  }

  /**
   * Initialize activity.
   *
   * @param savedInstanceState the saved instance state
   */
  protected abstract void initializeActivity(Bundle savedInstanceState);

}
