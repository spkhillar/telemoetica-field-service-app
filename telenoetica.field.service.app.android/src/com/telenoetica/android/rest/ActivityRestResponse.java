/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.rest;

/**
 * The Class ActivityRestResponse.
 */
public class ActivityRestResponse extends RestResponse {

  /** The visit object. */
  private Object visitObject;

  /** The visit class type. */
  private Class<?> visitClassType;

  /**
   * Gets the visit object.
   *
   * @return the visitObject
   */
  public Object getVisitObject() {
    return visitObject;
  }

  /**
   * Sets the visit object.
   *
   * @param visitObject the visitObject to set
   */
  public void setVisitObject(final Object visitObject) {
    this.visitObject = visitObject;
  }

  /**
   * Gets the visit class type.
   *
   * @return the visitClassType
   */
  public Class<?> getVisitClassType() {
    return visitClassType;
  }

  /**
   * Sets the visit class type.
   *
   * @param visitClassType the visitClassType to set
   */
  public void setVisitClassType(final Class<?> visitClassType) {
    this.visitClassType = visitClassType;
  }

}
