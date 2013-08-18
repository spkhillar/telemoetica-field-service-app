/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service.util;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.telenoetica.jpa.entities.CallOutVisit;
import com.telenoetica.jpa.entities.DieselVisit;
import com.telenoetica.jpa.entities.MaintenanceVisit;
import com.telenoetica.jpa.entities.RoutineVisit;
import com.telenoetica.jpa.entities.User;

/**
 * The Class ServiceUtil.
 * 
 * @author Shiv Prasad Khillar
 */
public class ServiceUtil {

  /** The field mapping. */
  private static Map<String, Field[]> fieldMapping = new LinkedHashMap<String, Field[]>();

  static {
    String clazzName = User.class.getName();
    Field[] field = getAllFields(User.class);
    fieldMapping.put(clazzName, field);

    clazzName = CallOutVisit.class.getName();
    field = getAllFields(CallOutVisit.class);
    fieldMapping.put(clazzName, field);

    clazzName = RoutineVisit.class.getName();
    field = getAllFields(RoutineVisit.class);
    fieldMapping.put(clazzName, field);

    clazzName = DieselVisit.class.getName();
    field = getAllFields(DieselVisit.class);
    fieldMapping.put(clazzName, field);

    clazzName = MaintenanceVisit.class.getName();
    field = getAllFields(MaintenanceVisit.class);
    fieldMapping.put(clazzName, field);
  }

  /**
   * Instantiates a new service util.
   */
  private ServiceUtil() {

  }

  /**
   * Find field.
   * 
   * @param className
   *            the class name
   * @param fieldName
   *            the field name
   * @return the field
   */
  public static Field findField(final String className, final String fieldName) {
    Field[] fields = fieldMapping.get(className);
    for (Field field : fields) {
      if (field.getName().equals(fieldName)) {
        return field;
      }
    }
    return null;
  }

  /**
   * Gets the all fields.
   * 
   * @param klass
   *            the klass
   * @return the all fields
   */
  @SuppressWarnings("rawtypes")
  public static Field[] getAllFields(final Class klass) {
    List<Field> fields = new ArrayList<Field>();
    fields.addAll(Arrays.asList(klass.getDeclaredFields()));
    if (klass.getSuperclass() != null) {
      fields.addAll(Arrays.asList(getAllFields(klass.getSuperclass())));
    }
    return fields.toArray(new Field[] {});
  }

  /**
   * Check and return value.
   * 
   * @param value
   *            the value
   * @return the string
   */
  public static String checkAndReturnValue(final String value) {
    if (StringUtils.isBlank(value)) {
      return "";
    }
    return value;
  }

  /**
   * Check and return value.
   * 
   * @param value
   *            the value
   * @return the string
   */
  public static String checkAndReturnValue(final Long value) {
    if (value != null) {
      return value.toString();
    }
    return "";
  }

  /**
   * Check and return value.
   * 
   * @param date
   *            the date
   * @return the string
   */
  public static String checkAndReturnValue(final Date date) {
    if (date == null) {
      return "";
    }
    return getDateInFormat(date, "MM/dd/yyyy HH:mm:ss");
  }

  /**
   * Gets the date in format.
   * 
   * @param date
   *            the date
   * @param format
   *            the format
   * @return the date in format
   */
  public static String getDateInFormat(final Date date, final String format) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(date);

  }

  /**
   * Gets the date in format.
   * 
   * @param date
   *            the date
   * @param format
   *            the format
   * @return the date in format
   */
  public static Date getDateInFormat(final String date, final String format) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    try {
      return sdf.parse(date);
    } catch (ParseException e) {
    }

    return null;

  }

  /**
   * Check and return value.
   * 
   * @param enabled
   *            the enabled
   * @return the string
   */
  public static String checkAndReturnValue(final Boolean enabled) {

    if (enabled == null) {
      return "false";
    }

    return enabled.toString();
  }

  /**
   * Gets the page.
   * 
   * @param page
   *            the page
   * @param rows
   *            the rows
   * @param sortOrder
   *            the sort order
   * @param orderByField
   *            the order by field
   * @return the page
   */
  public static Pageable getPage(final int page, final int rows, final String sortOrder,
      final String orderByField) {
    Sort.Direction sortDirection = Sort.Direction.ASC;
    if ("desc".equalsIgnoreCase(sortOrder)) {
      sortDirection = Sort.Direction.DESC;
    }
    Pageable pagebale = new PageRequest(page - 1, rows, sortDirection,
      orderByField);
    return pagebale;
  }

  /**
   * Gets the page.
   * 
   * @param page
   *            the page
   * @param rows
   *            the rows
   * @return the page
   */
  public static Pageable getPage(final int page, final int rows) {
    Pageable pagebale = new PageRequest(page - 1, rows);
    return pagebale;
  }

  /**
   * Gets the typed value.
   * 
   * @param clazz
   *            the clazz
   * @param value
   *            the value
   * @param ruleOperator
   *            the rule operator
   * @return the typed value
   */
  public static Object getTypedValue(final Class clazz, final String value,
      final String ruleOperator) {
    if (ClassUtils.isAssignable(clazz, Long.class)) {
      return Long.parseLong(value);
    } else if (ClassUtils.isAssignable(clazz, Boolean.class)) {
      return Boolean.valueOf(value);
    } else if (ClassUtils.isAssignable(clazz, Integer.class)) {
      return Integer.parseInt(value);
    } else if (ClassUtils.isAssignable(clazz, Date.class)) {
      return getDateInFormat(value,
        ApplicationConstants.JQGRID_DATE_FORMAT);
    } else {
      String retunValue = new String(value);
      if ("cn".equals(ruleOperator)) {
        retunValue = "%" + retunValue + "%";
      } else if ("bw".equals(ruleOperator)) {
        retunValue = retunValue + "%";
      } else if ("ew".equals(ruleOperator)) {
        retunValue = "%" + retunValue;
      }
      return retunValue;
    }
  }

  /**
   * Check value type.
   * 
   * @param value
   *            the value
   * @return the class
   */
  public static Class checkValueType(final String value) {
    if (StringUtils.isNumeric(value)) {
      return Long.class;
    } else if ("true".equalsIgnoreCase(value)
        || "false".equalsIgnoreCase(value)) {
      return Boolean.class;
    } else {
      Date d = getDateInFormat(value,
        ApplicationConstants.JQGRID_DATE_FORMAT);
      if (d != null) {
        return Date.class;
      }
    }
    return String.class;
  }

  /**
   * Gets the string in format.
   *
   * @param formatString the format string
   * @param arguments the arguments
   * @return the string in format
   */
  public static String getStringInFormat(final String formatString,final Object arguments[]){
    if(ArrayUtils.isEmpty(arguments)){
      return formatString;
    }
    return MessageFormat.format(formatString, arguments);
  }

  /**
   * Gets the parent folder.
   *
   * @param directory the directory
   * @param date the date
   * @return the parent folder
   */
  public static String getParentFolder(final String directory,final Date date){

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    // date.get
    //String folderPath = directory + File.pathSeparator + cal.get
    //File file = new File(pathname)
    return null;
  }
}
