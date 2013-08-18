/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * The Class ExcelWriter.
 * 
 * @author Shiv Prasad Khillar
 */
public class ExcelWriter {

	/** The logger. */
	private static Logger logger = Logger.getLogger(ExcelWriter.class);

	/**
	 * Writes the report to the http servlet response output stream.
	 * 
	 * @param response
	 *            the response
	 * @param workbook
	 *            the workbook
	 * @param attachmentFileName
	 *            the attachment file name
	 */
	public static void write(final HttpServletResponse response,
			final HSSFWorkbook workbook, final String attachmentFileName) {

		logger.debug("Writing report to the stream");
		try {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ attachmentFileName);
			// Retrieve the output stream
			ServletOutputStream outputStream = response.getOutputStream();
			// Write to the output stream
			workbook.write(outputStream);
			// Flush the stream
			outputStream.flush();

		} catch (Exception e) {
			logger.error("Unable to write report to the output stream");
		}
	}

	/**
	 * Writes the report to the http servlet response output stream.
	 * 
	 * @param excelFileName
	 *            the excel file name
	 * @param workbook
	 *            the workbook
	 */
	public static void write(final String excelFileName,
			final HSSFWorkbook workbook) {

		logger.debug("Writing report to the filename:" + excelFileName);
		try {
			File file = new File(excelFileName);
			logger.debug("Writing report to the actual file path :"
					+ file.getAbsolutePath());
			FileOutputStream fileOut = new FileOutputStream(excelFileName);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			logger.error("Unable to write report to the output stream");
		}
	}

	/**
	 * Write.
	 * 
	 * @param response
	 *            the response
	 * @param reportFileName
	 *            the report file name
	 */
	public static void write(final HttpServletResponse response,
			final String reportFileName) {

		logger.debug("exporting report from the filename:" + reportFileName);
		try {
			response.setContentType("application/x-ms-excel");
			if (reportFileName.contains("Diesel")) {
				response.setHeader("Content-Disposition",
						"Attachment;filename= " + "DieselDetailReport.xls");
			} else {
				response.setHeader("Content-Disposition",
						"Attachment;filename= " + "sparesUtilizationReport.xls");
			}
			ServletOutputStream out = response.getOutputStream();
			FileInputStream fileInputStream = new FileInputStream(
					reportFileName);
			// Write to the output stream
			int i;
			while ((i = fileInputStream.read()) != -1) {
				out.write(i);
			}

			// Always close files.
			fileInputStream.close();
			out.flush();
		} catch (Exception e) {
			logger.error("Unable to write report to the output stream");
		}
	}

}
