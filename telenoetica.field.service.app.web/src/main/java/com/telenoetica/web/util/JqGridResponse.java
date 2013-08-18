/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.web.util;

import java.io.Serializable;
import java.util.List;

/**
 * A POJO representing a jQgrid's jsonReader property.
 *
 * @param <T> the generic type
 */
public class JqGridResponse<T extends Serializable> {

  /** Current page. */
  private String page;

  /** Total pages. */
  private String total;

  /** Total number of records. */
  private String records;

  /** Contains the actual data. */
  private List<Object> rows;

  /**
   * Instantiates a new jq grid response.
   */
  public JqGridResponse() {
  }

  /**
   * Instantiates a new jq grid response.
   *
   * @param page the page
   * @param total the total
   * @param records the records
   * @param rows the rows
   */
  public JqGridResponse(final String page, final String total, final String records,
      final List<Object> rows) {
    super();
    this.page = page;
    this.total = total;
    this.records = records;
    this.rows = rows;
  }

  /**
   * Gets the page.
   *
   * @return the page
   */
  public String getPage() {
    return page;
  }

  /**
   * Sets the page.
   *
   * @param page the new page
   */
  public void setPage(final String page) {
    this.page = page;
  }

  /**
   * Gets the total.
   *
   * @return the total
   */
  public String getTotal() {
    return total;
  }

  /**
   * Sets the total.
   *
   * @param total the new total
   */
  public void setTotal(final String total) {
    this.total = total;
  }

  /**
   * Gets the records.
   *
   * @return the records
   */
  public String getRecords() {
    return records;
  }

  /**
   * Sets the records.
   *
   * @param records the new records
   */
  public void setRecords(final String records) {
    this.records = records;
  }

  /**
   * Gets the rows.
   *
   * @return the rows
   */
  public List<Object> getRows() {
    return rows;
  }

  /**
   * Sets the rows.
   *
   * @param rows the new rows
   */
  public void setRows(final List<Object> rows) {
    this.rows = rows;
  }

  /**
   * To string.
   *
   * @return the string
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "JqgridResponse [page=" + page + ", total=" + total
        + ", records=" + records + "]";
  }
}
