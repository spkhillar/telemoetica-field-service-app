/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.service.util;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * The Class ExcelRendererModel.
 *
 * @author  Shiv Prasad Khillar
 */
public class ExcelRendererModel {
  
  /** The worksheet. */
  private HSSFSheet worksheet;
  
  /** The start row index. */
  private int startRowIndex=0;
  
  /** The start col index. */
  private int startColIndex=0;
  
  /** The column width. */
  private int columnWidth;
  
  /** The columns. */
  private List<String> columns;
  
  /** The report title. */
  private String reportTitle;
  

  /**
   * Instantiates a new excel renderer model.
   *
   * @param worksheet the worksheet
   * @param columnWidth the column width
   * @param columns the columns
   * @param reportTitle the report title
   */
  public ExcelRendererModel(HSSFSheet worksheet, int columnWidth, List<String> columns,String reportTitle) {
    super();
    this.worksheet = worksheet;
    this.columnWidth = columnWidth;
    this.columns = columns;
    this.reportTitle = reportTitle;
  }

  /**
   * Instantiates a new excel renderer model.
   *
   * @param worksheet the worksheet
   * @param startRowIndex the start row index
   * @param startColIndex the start col index
   * @param columnWidth the column width
   * @param columns the columns
   * @param reportTitle the report title
   */
  public ExcelRendererModel(HSSFSheet worksheet, int startRowIndex, int startColIndex, int columnWidth,
      List<String> columns,String reportTitle) {
    super();
    this.worksheet = worksheet;
    this.startRowIndex = startRowIndex;
    this.startColIndex = startColIndex;
    this.columnWidth = columnWidth;
    this.columns = columns;
    this.reportTitle = reportTitle;
  }

  /**
   * Gets the worksheet.
   *
   * @return the worksheet
   */
  public HSSFSheet getWorksheet() {
    return worksheet;
  }

  /**
   * Gets the start row index.
   *
   * @return the start row index
   */
  public int getStartRowIndex() {
    return startRowIndex;
  }

  /**
   * Gets the start col index.
   *
   * @return the start col index
   */
  public int getStartColIndex() {
    return startColIndex;
  }

  /**
   * Gets the column width.
   *
   * @return the column width
   */
  public int getColumnWidth() {
    return columnWidth;
  }

  /**
   * Gets the columns.
   *
   * @return the columns
   */
  public List<String> getColumns() {
    return columns;
  }

  /**
   * Gets the report title.
   *
   * @return the report title
   */
  public String getReportTitle() {
    return reportTitle;
  }

}
