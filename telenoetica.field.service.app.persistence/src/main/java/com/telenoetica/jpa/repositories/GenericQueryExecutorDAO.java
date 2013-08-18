/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.jpa.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

/**
 * The Interface GenericQueryExecutorDAO.
 * 
 * @author Shiv Prasad Khillar
 */
public interface GenericQueryExecutorDAO {

	/**
	 * Execute query.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param ejbql
	 *            the ejbql
	 * @param clazz
	 *            the clazz
	 * @return the list
	 */
	<T> List<T> executeQuery(String ejbql, Class<T> clazz);

	/**
	 * Execute query.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param ejbql
	 *            the ejbql
	 * @param clazz
	 *            the clazz
	 * @param page
	 *            the page
	 * @param pageSize
	 *            the page size
	 * @return the page
	 */
	<T> Page<T> executeQuery(String ejbql, Class<T> clazz, int page,
			int pageSize);

	/**
	 * Execute query.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param ejbql
	 *            the ejbql
	 * @param clazz
	 *            the clazz
	 * @param params
	 *            the params
	 * @param page
	 *            the page
	 * @param pageSize
	 *            the page size
	 * @return the page
	 */
	<T> Page<T> executeQuery(String ejbql, Class<T> clazz,
			Map<String, Object> params, int page, int pageSize);

	/**
	 * Execute query.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param ejbql
	 *            the ejbql
	 * @param clazz
	 *            the clazz
	 * @param params
	 *            the params
	 * @return the list
	 */
	<T> List<T> executeQuery(String ejbql, Class<T> clazz,
			Map<String, Object> params);

	/**
	 * Execute projected query.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param ejbql
	 *            the ejbql
	 * @return the list
	 */
	<T> List<T> executeProjectedQuery(String ejbql);

	/**
	 * Execute projected query.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param ejbql
	 *            the ejbql
	 * @param params
	 *            the params
	 * @return the list
	 */
	<T> List<T> executeProjectedQuery(String ejbql, Map<String, Object> params);

	long findCount(String ejbql, Map<String, Object> params);

}
