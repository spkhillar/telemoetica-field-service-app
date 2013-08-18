/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.telenoetica.service.util.ServiceUtil;
import com.telenoetica.web.util.JqGridFilter;
import com.telenoetica.web.util.JqGridFilter.Rule;
import com.telenoetica.web.util.JqGridFilterQueryBuilder;
import com.telenoetica.web.util.JqGridObjectMapper;

/**
 * The Class AbstractJqGridFilterController.
 *
 * @author  Shiv Prasad Khillar
 */
public abstract class AbstractJqGridFilterController extends BaseController{

	/**
	 * Gets the filter exclusion properties.
	 *
	 * @return the filter exclusion properties
	 */
	public abstract String[] getFilterExclusionProperties();

	/**
	 * Gets the filter excluded property query mapping.
	 *
	 * @return the filter excluded property query mapping
	 */
	public abstract Map<String, String> getFilterExcludedPropertyQueryMapping();

	/**
	 * Gets the filter excluded property order mapping.
	 *
	 * @return the filter excluded property order mapping
	 */
	public abstract Map<String, String> getFilterExcludedPropertyOrderMapping();

	/**
	 * Gets the filtered records.
	 *
	 * @param filters the filters
	 * @param sord the sord
	 * @param sidx the sidx
	 * @param paramObject the param object
	 * @param targetClass the target class
	 * @return the filtered records
	 */
	@SuppressWarnings("rawtypes")
	public String getFilteredRecords(String filters, String sord, String sidx,
			Map<String, Object> paramObject,Class targetClass) {
		JqGridFilter jqgridFilter = JqGridObjectMapper.map(filters);
		String groupOperator = " " + jqgridFilter.getGroupOp() + " ";
		List<String> predicateString = new ArrayList<String>();
		String[] execludedFilters = getFilterExclusionProperties();
		List<Rule> excludedRules = JqGridFilterQueryBuilder.getJpqlPredicate(
				jqgridFilter, execludedFilters, paramObject, predicateString,
				targetClass, groupOperator);
		List<String> excludedPredicates = getExcludedPredicates(excludedRules,
				paramObject);

		String predicateFirstPart = predicateString.get(0);
		String finalPredicate = predicateFirstPart;
		String predicateSecondPart = "";
		if (CollectionUtils.isNotEmpty(excludedPredicates)) {
			predicateSecondPart = StringUtils.join(excludedPredicates,
					groupOperator);
			if(StringUtils.isNotBlank(predicateFirstPart)){
				finalPredicate = finalPredicate + groupOperator
						+ predicateSecondPart;
			}else{
				finalPredicate = predicateSecondPart;
			}
		}
		String orderByField = getOrderByfield(sidx);
		if (orderByField != null) {
			finalPredicate = finalPredicate + " order by " + orderByField + " "
					+ sord;
		}
		return finalPredicate;
		// return null;
	}

	/**
	 * Gets the excluded predicates.
	 *
	 * @param excludedRules the excluded rules
	 * @param paramObject the param object
	 * @return the excluded predicates
	 */
	public List<String> getExcludedPredicates(List<Rule> excludedRules,
			Map<String, Object> paramObject) {
		List<String> excludedPredicates = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(excludedRules)) {
			int paramSize = paramObject.size();
			String exeRule = null;
			for (Rule rule : excludedRules) {
				String value = getFilterExcludedPropertyQueryMapping().get(
						rule.getField());
				if (StringUtils.isBlank(value)) {
					continue;
				}
				Rule newRule = new Rule(null, value, rule.getOp(),
						rule.getData());
				exeRule = JqGridFilterQueryBuilder.createRuleString(newRule,
						paramObject, paramSize, ServiceUtil.checkValueType(newRule.getData()));
				excludedPredicates.add(exeRule);
				paramSize++;
			}
		}
		return excludedPredicates;
	}

	/**
	 * Gets the order byfield.
	 *
	 * @param filter the filter
	 * @return the order byfield
	 */
	public String getOrderByfield(String filter) {
		String orderByField = filter;
		String excludedPropMapping = this.getFilterExcludedPropertyOrderMapping()
				.get(filter);
		if (StringUtils.isNotBlank(excludedPropMapping)) {
			return excludedPropMapping;
		}
		return orderByField;
	}

}
