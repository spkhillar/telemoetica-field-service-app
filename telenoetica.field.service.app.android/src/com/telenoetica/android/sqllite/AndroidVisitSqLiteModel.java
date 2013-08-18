/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.sqllite;

/**
 * The Class AndroidVisitSqLiteModel.
 */
public class AndroidVisitSqLiteModel {

  /** The id. */
  private Long id;

  /** The json. */
  private String json;

  /** The status. */
  private String status;

  /** The clazz name. */
  private String clazzName;

  /** The tries. */
  private Integer tries;



  /**
   * Instantiates a new android visit sq lite model.
   */
  public AndroidVisitSqLiteModel() {
    super();
  }

  /**
   * Instantiates a new android visit sq lite model.
   *
   * @param id the id
   * @param json the json
   * @param status the status
   * @param clazzName the clazz name
   * @param tries the tries
   */
  public AndroidVisitSqLiteModel(final Long id,final String json, final String status, final String clazzName, final Integer tries) {
    super();
    this.id=id;
    this.json = json;
    this.status = status;
    this.clazzName = clazzName;
    this.tries = tries;
  }



  /**
   * Gets the id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(final Long id) {
    this.id = id;
  }

  /**
   * Gets the json.
   *
   * @return the json
   */
  public String getJson() {
    return json;
  }

  /**
   * Sets the json.
   *
   * @param json the new json
   */
  public void setJson(final String json) {
    this.json = json;
  }

  /**
   * Gets the status.
   *
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /**
   * Sets the status.
   *
   * @param status the new status
   */
  public void setStatus(final String status) {
    this.status = status;
  }

  /**
   * Gets the clazz name.
   *
   * @return the clazz name
   */
  public String getClazzName() {
    return clazzName;
  }

  /**
   * Sets the clazz name.
   *
   * @param clazzName the new clazz name
   */
  public void setClazzName(final String clazzName) {
    this.clazzName = clazzName;
  }

  /**
   * Gets the tries.
   *
   * @return the tries
   */
  public Integer getTries() {
    return tries;
  }

  /**
   * Sets the tries.
   *
   * @param tries the new tries
   */
  public void setTries(final Integer tries) {
    this.tries = tries;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("AndroidVisitSqLiteModel [");
    if (id != null) {
      builder.append("id=");
      builder.append(id);
      builder.append(", ");
    }
    if (json != null) {
      builder.append("json=");
      builder.append(json);
      builder.append(", ");
    }
    if (status != null) {
      builder.append("status=");
      builder.append(status);
      builder.append(", ");
    }
    if (clazzName != null) {
      builder.append("clazzName=");
      builder.append(clazzName);
      builder.append(", ");
    }
    if (tries != null) {
      builder.append("tries=");
      builder.append(tries);
    }
    builder.append("]");
    return builder.toString();
  }
}
