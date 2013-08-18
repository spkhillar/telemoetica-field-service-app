/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.rest;

import java.text.MessageFormat;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class JsonValidator.
 */
@JsonAutoDetect(JsonMethod.NONE)
public class JsonValidator {

  /** The required. */
  @JsonProperty
  private boolean required;

  /** The site id check. */
  @JsonProperty
  private boolean siteIdCheck;

  /** The min. */
  @JsonProperty
  private int min;

  /** The max. */
  @JsonProperty
  private int max;

  /** The message. */
  @JsonProperty
  private String message;

  /**
   * Checks if is required.
   *
   * @return true, if is required
   */
  public boolean isRequired() {
    return required;
  }

  /**
   * Sets the required.
   *
   * @param required the new required
   */
  public void setRequired(final boolean required) {
    this.required = required;
  }

  /**
   * Gets the min.
   *
   * @return the min
   */
  public int getMin() {
    return min;
  }

  /**
   * Sets the min.
   *
   * @param min the new min
   */
  public void setMin(final int min) {
    this.min = min;
  }

  /**
   * Checks if is site id check.
   *
   * @return true, if is site id check
   */
  public boolean isSiteIdCheck() {
    return siteIdCheck;
  }

  /**
   * Sets the site id check.
   *
   * @param siteIdCheck the new site id check
   */
  public void setSiteIdCheck(final boolean siteIdCheck) {
    this.siteIdCheck = siteIdCheck;
  }

  /**
   * Gets the max.
   *
   * @return the max
   */
  public int getMax() {
    return max;
  }

  /**
   * Sets the max.
   *
   * @param max the new max
   */
  public void setMax(final int max) {
    this.max = max;
  }

  /**
   * Gets the message.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message.
   *
   * @param message the new message
   */
  public void setMessage(final String message) {
    this.message = message;
  }

  /**
   * Validate.
   *
   * @param value the value
   * @return true, if successful
   */
  public boolean validate(final String value) {
    boolean valid = true;
    if (required) {
      valid = StringUtils.isNotBlank(value);
      if (!valid) {
        message = AndroidConstants.REQUIRED_MESSAGE;
      }
    }
    if (valid && siteIdCheck) {
      valid = AppValuesHolder.getSites().contains(value);
      if (!valid) {
        message = "Site ID does not exist in the system";
      }
      return valid;
    }

    if (valid && min >= 0 && max > 0) {
      if(StringUtils.isNotBlank(value)){
        int intValue = Integer.parseInt(value);
        valid = (intValue >= min && intValue <= max);
        if (!valid) {
          message = getStringInFormat(AndroidConstants.RANGE_MESSAGE, new Object[] { value, min, max });
        }
      }
    }
    return valid;
  }

  /**
   * Gets the string in format.
   *
   * @param formatString the format string
   * @param arguments the arguments
   * @return the string in format
   */
  private String getStringInFormat(final String formatString, final Object arguments[]) {
    if (ArrayUtils.isEmpty(arguments)) {
      return formatString;
    }
    return MessageFormat.format(formatString, arguments);
  }

}
