/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.service.excel;

import java.util.List;

import com.telenoetica.service.util.ExcelRendererModel;

/**
 * The Interface ExcelFillerService.
 *
 * @author  Shiv Prasad Khillar
 */
public interface ExcelFillerService {
   
   /**
    * Fill report.
    *
    * @param excelRendererModel the excel renderer model
    * @param targetValues the target values
    */
   void fillReport(ExcelRendererModel excelRendererModel,List<List<Object>> targetValues );
  
  
}
