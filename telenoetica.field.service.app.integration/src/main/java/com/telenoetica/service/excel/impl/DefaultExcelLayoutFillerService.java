/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service.excel.impl;

import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.stereotype.Service;

import com.telenoetica.service.excel.ExcelFillerService;
import com.telenoetica.service.util.ExcelRendererModel;

/**
 * The Class DefaultExcelLayoutFillerService.
 *
 * @author  Shiv Prasad Khillar
 */
@Service("defaultExcelLayoutFillerService")
public class DefaultExcelLayoutFillerService implements ExcelFillerService {

  /**
   * Fill report.
   *
   * @param excelRendererModel the excel renderer model
   * @param targetValues the target values
   * @see com.telenoetica.service.excel.ExcelFillerService#fillReport(com.telenoetica.service.util.ExcelRendererModel, java.util.List)
   */
  @Override
  public void fillReport(final ExcelRendererModel excelRendererModel, final List<List<Object>> targetValues) {
    int startRowIndex = excelRendererModel.getStartRowIndex() + 2;
    int startColIndex = excelRendererModel.getStartColIndex();
    HSSFSheet worksheet = excelRendererModel.getWorksheet();
    // Create cell style for the body
    HSSFCellStyle bodyCellStyle = worksheet.getWorkbook().createCellStyle();
    bodyCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
    bodyCellStyle.setWrapText(true);

    for (int i = startRowIndex; i + startRowIndex - 2 < targetValues.size() + 2; i++) {
      // Create a new row
      List<Object> values = targetValues.get(i - 2);
      HSSFRow row = worksheet.createRow((short) i + 1);

      for (int j = 0; j < values.size(); j++) {
        // Retrieve the id value
        HSSFCell cell = row.createCell(startColIndex + j);
        if (values.get(j) instanceof Date) {
          cell.setCellValue((Date) values.get(j));
        } else {
          cell.setCellValue((String) values.get(j));
        }
        cell.setCellStyle(bodyCellStyle);
      }
    }
  }

}
