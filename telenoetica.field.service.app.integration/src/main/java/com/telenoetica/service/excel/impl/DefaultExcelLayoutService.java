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
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import com.telenoetica.service.util.ExcelRendererModel;
import com.telenoetica.service.util.ServiceUtil;

/**
 * The Class DefaultExcelLayoutService.
 * 
 * @author Shiv Prasad Khillar
 */
@Service("defaultExcelLayoutService")
public class DefaultExcelLayoutService extends AbstractExcelLayoutService {

  /**
   * Builds the headers.
   *
   * @param excelRendererModel the excel renderer model
   * @see
   * com.telenoetica.service.excel.impl.AbstractExcelLayoutService#buildHeaders
   * (com.telenoetica.service.util.ExcelRendererModel)
   */
  @Override
  public void buildHeaders(final ExcelRendererModel excelRendererModel) {
    HSSFCellStyle headerCellStyle = getCellStyle(excelRendererModel
      .getWorksheet());
    int startRowIndex = excelRendererModel.getStartRowIndex();
    int startColIndex = excelRendererModel.getStartColIndex();
    // Create the column headers
    HSSFRow rowHeader = excelRendererModel.getWorksheet().createRow(
      (short) startRowIndex + 2);
    rowHeader.setHeight((short) 500);

    List<String> columns = excelRendererModel.getColumns();

    HSSFCell cell1 = null;
    for (int i = 0; i < columns.size(); i++) {
      cell1 = rowHeader.createCell(startColIndex + i);
      cell1.setCellValue(columns.get(i));
      cell1.setCellStyle(headerCellStyle);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.telenoetica.service.excel.impl.AbstractExcelLayoutService#buildTitle
   * (com.telenoetica.service.util.ExcelRendererModel)
   */
  @Override
  public void buildTitle(final ExcelRendererModel excelRendererModel) {
    HSSFSheet worksheet = excelRendererModel.getWorksheet();
    int startRowIndex = excelRendererModel.getStartRowIndex();
    int startColIndex = excelRendererModel.getStartColIndex();
    // Create font style for the report title
    Font fontTitle = worksheet.getWorkbook().createFont();
    fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
    fontTitle.setFontHeight((short) 280);

    // Create cell style for the report title
    HSSFCellStyle cellStyleTitle = worksheet.getWorkbook()
        .createCellStyle();
    cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
    cellStyleTitle.setWrapText(true);
    cellStyleTitle.setFont(fontTitle);

    // Create report title
    HSSFRow rowTitle = worksheet.createRow((short) startRowIndex);
    rowTitle.setHeight((short) 500);
    HSSFCell cellTitle = rowTitle.createCell(startColIndex);
    cellTitle.setCellValue(excelRendererModel.getReportTitle());
    cellTitle.setCellStyle(cellStyleTitle);

    // Create merged region for the report title
    worksheet.addMergedRegion(new CellRangeAddress(0, 0, 0,
      excelRendererModel.getColumns().size() - 1));

    // Create date header
    HSSFRow dateTitle = worksheet.createRow((short) startRowIndex + 1);
    HSSFCell cellDate = dateTitle.createCell(startColIndex);
    cellDate.setCellValue("This report was generated at "
        + ServiceUtil.checkAndReturnValue(new Date()));
    worksheet.addMergedRegion(new CellRangeAddress(1, 1, 0,
      excelRendererModel.getColumns().size() - 1));
  }

  /**
   * Gets the cell style.
   * 
   * @param worksheet
   *            the worksheet
   * @return the cell style
   */
  private HSSFCellStyle getCellStyle(final HSSFSheet worksheet) {
    Font font = worksheet.getWorkbook().createFont();
    font.setBoldweight(Font.BOLDWEIGHT_BOLD);

    // Create cell style for the headers
    HSSFCellStyle headerCellStyle = worksheet.getWorkbook()
        .createCellStyle();
    headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
    headerCellStyle.setFillPattern(CellStyle.FINE_DOTS);
    headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
    headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    headerCellStyle.setWrapText(true);
    headerCellStyle.setFont(font);
    headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
    return headerCellStyle;
  }

}
