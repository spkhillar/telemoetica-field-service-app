/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.telenoetica.util.model.CustomObjectMapper;

/**
 * The Class RestJsonUtils.
 */
public abstract class RestJsonUtils {

  /**
   * From json string.
   *
   * @param <T> the generic type
   * @param jsonString the json string
   * @param objectType the object type
   * @return the t
   * @throws JsonParseException the json parse exception
   * @throws JsonMappingException the json mapping exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static <T> T fromJSONString(final String jsonString, final Class<T> objectType) throws JsonParseException,
  JsonMappingException, IOException {
    CustomObjectMapper om = new CustomObjectMapper();
    T valueObject = om.readValue(jsonString, objectType);
    return valueObject;
  }

  /**
   * To json string.
   *
   * @param object the object
   * @return the string
   * @throws JsonGenerationException the json generation exception
   * @throws JsonMappingException the json mapping exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static String toJSONString(final Object object) throws JsonGenerationException, JsonMappingException, IOException {
    StringWriter sw = new StringWriter();
    CustomObjectMapper om = new CustomObjectMapper();
    PrintWriter pw = new PrintWriter(sw);
    om.writeValue(pw, object);
    return sw.toString();
  }
}
