/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.web.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.telenoetica.service.util.ServiceUtil;
import com.telenoetica.web.util.JqGridFilter.Rule;
import com.telenoetica.web.util.JqGridFilter.RuleOperator;

/**
 * The Class JqGridFilterQueryBuilder.
 * 
 * @author Shiv Prasad Khillar
 */
public class JqGridFilterQueryBuilder {

  /**
   * Instantiates a new jq grid filter query builder.
   */
  private JqGridFilterQueryBuilder() {

  }

  /**
   * Gets the jpql predicate.
   * 
   * @param jqgridFilter
   *          the jqgrid filter
   * @param excludedProperties
   *          the excluded properties
   * @param paramObject
   *          the param object
   * @param predicateString
   *          the predicate string
   * @param clazz
   *          the clazz
   * @param groupOperator
   *          the group operator
   * @return the jpql predicate
   */
  public static List<Rule> getJpqlPredicate(final JqGridFilter jqgridFilter, final String[] excludedProperties,
    final Map<String, Object> paramObject, final List<String> predicateString, final Class<?> clazz,
    final String groupOperator) {

    List<String> rules = new ArrayList<String>();
    String predicateRules = null;
    List<Rule> excludedFilterList = new ArrayList<Rule>();
    List<Rule> filterRuleList = jqgridFilter.getRules();
    int paramIndex = 0;
    for (Rule rule : filterRuleList) {
      if (ArrayUtils.contains(excludedProperties, rule.getField())) {
        excludedFilterList.add(rule);
        continue;
      }
      predicateRules = getRuleString(rule, paramObject, paramIndex, clazz);
      if (predicateRules == null) {
        continue;
      }
      rules.add(predicateRules);
      paramIndex++;
    }

    String predicate = StringUtils.join(rules, groupOperator);
    predicateString.add(predicate);
    System.err.println("..predicate..." + predicate);
    // LOGGER.debug("...predicateString.." + predicateString);
    return excludedFilterList;
  }

  /**
   * Gets the rule string.
   * 
   * @param rule
   *          the rule
   * @param paramObject
   *          the param object
   * @param paramIndex
   *          the param index
   * @param clazz
   *          the clazz
   * @return the rule string
   */
  private static String getRuleString(final Rule rule, final Map<String, Object> paramObject, final int paramIndex,
      final Class<?> clazz) {
    Field field = ServiceUtil.findField(clazz.getName(), rule.getField());
    if (field == null) {
      return null;
    }
    String predicateRule = createRuleString(rule, paramObject, paramIndex, field.getType());
    return predicateRule;
  }

  /**
   * Creates the rule string.
   * 
   * @param rule
   *          the rule
   * @param paramObject
   *          the param object
   * @param paramIndex
   *          the param index
   * @param fieldValueType
   *          the field value type
   * @return the string
   */
  public static String createRuleString(final Rule rule, final Map<String, Object> paramObject, final int paramIndex,
      final Class<?> fieldValueType) {

    String predicateRule = new String();

    String operator = null;
    if (RuleOperator.cn.name().equals(rule.getOp())) {
      operator = "like";
    } else if (RuleOperator.eq.name().equals(rule.getOp())) {
      operator = "=";
    } else if (RuleOperator.ne.name().equals(rule.getOp())) {
      operator = "<>";
    } else if (RuleOperator.gt.name().equals(rule.getOp())) {
      operator = ">";
    } else if (RuleOperator.lt.name().equals(rule.getOp())) {
      operator = "<";
    } else if (RuleOperator.bw.name().equals(rule.getOp())) {
      operator = "like";
    } else if (RuleOperator.ew.name().equals(rule.getOp())) {
      operator = " like ";
    }
    predicateRule = rule.getField() + " " + operator + " :param" + paramIndex;

    paramObject.put("param" + paramIndex, ServiceUtil.getTypedValue(fieldValueType, rule.getData(), rule.getOp()));
    return predicateRule;
  }

}
