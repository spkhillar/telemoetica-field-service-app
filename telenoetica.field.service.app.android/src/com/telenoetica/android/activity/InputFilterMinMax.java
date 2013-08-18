/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.activity;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * The Class InputFilterMinMax.
 */
public class InputFilterMinMax implements InputFilter {

  /** The max. */
  private int min, max;

  /**
   * Instantiates a new input filter min max.
   *
   * @param min the min
   * @param max the max
   */
  public InputFilterMinMax(final int min, final int max) {
    this.min = min;
    this.max = max;
  }

  /**
   * Instantiates a new input filter min max.
   *
   * @param min the min
   * @param max the max
   */
  public InputFilterMinMax(final String min, final String max) {
    this.min = Integer.parseInt(min);
    this.max = Integer.parseInt(max);
  }

  /**
   * Filter.
   *
   * @param source the source
   * @param start the start
   * @param end the end
   * @param dest the dest
   * @param dstart the dstart
   * @param dend the dend
   * @return the char sequence
   * @see android.text.InputFilter#filter(java.lang.CharSequence, int, int, android.text.Spanned, int, int)
   */
  @Override
  public CharSequence filter(final CharSequence source, final int start, final int end, final Spanned dest, final int dstart, final int dend) {
    try {
      int input = Integer.parseInt(dest.toString() + source.toString());
      if (isInRange(min, max, input)) {
        return null;
      }
    } catch (NumberFormatException nfe) { }
    return "";
  }



  /**
   * Checks if is in range.
   *
   * @param a the a
   * @param b the b
   * @param c the c
   * @return true, if is in range
   */
  private boolean isInRange(final int a, final int b, final int c) {
    return b > a ? c >= a && c <= b : c >= b && c <= a;
  }
}
