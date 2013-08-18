/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.service.excel;

import com.telenoetica.service.util.ExcelRendererModel;

/**
 * The Interface ExcelLayoutService.
 *
 * @author  Shiv Prasad Khillar
 */
public interface ExcelLayoutService {

  /**
   * Builds the report.
   *
   * @param excelRendererModel the excel renderer model
   */
  void buildReport(ExcelRendererModel excelRendererModel);
  
}
