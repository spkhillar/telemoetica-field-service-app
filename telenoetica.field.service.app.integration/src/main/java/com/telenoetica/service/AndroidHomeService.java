/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.telenoetica.util.model.HomeAndroidObject;

/**
 * The Interface AndroidHomeService.
 * 
 * @author Shiv Prasad Khillar
 */
public interface AndroidHomeService {

	/**
	 * Gets the android home object.
	 * 
	 * @return the android home object
	 */
	public HomeAndroidObject getAndroidHomeObject();

	/**
	 * Gets the chart data.
	 * 
	 * @return the chart data
	 */
	public List<Integer> getchartData();

	/**
	 * Export android app.
	 * 
	 * @param httpServletResponse
	 *            the http servlet response
	 * @return
	 */
	public boolean exportAndroidApp(HttpServletResponse httpServletResponse);

	/**
	 * @param httpServletResponse
	 */
	public void exportAndroidAppHelp(HttpServletResponse httpServletResponse);

}
