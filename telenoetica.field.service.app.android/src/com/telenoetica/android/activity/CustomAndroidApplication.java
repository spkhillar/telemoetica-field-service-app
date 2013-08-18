/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.activity;

import android.app.Application;

/**
 * The Class CustomAndroidApplication.
 */
public class CustomAndroidApplication extends Application {

  /** The http url. */
  private String httpUrl;

  /**
   * On create.
   *
   * @see android.app.Application#onCreate()
   */
  @Override
  public void onCreate() {
    // TODO Auto-generated method stub
    super.onCreate();
  }

  /**
   * Gets the http url.
   *
   * @return the http url
   */
  public String getHttpUrl() {
    return httpUrl;
  }

  /**
   * Sets the http url.
   *
   * @param httpUrl the new http url
   */
  public void setHttpUrl(final String httpUrl) {
    this.httpUrl = httpUrl;
  }

}
