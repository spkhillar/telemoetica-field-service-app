/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

/**
 * The Class Reset.
 */
public class Reset {

  /**
   * Clear form.
   *
   * @param group the group
   */
  public void clearForm(final ViewGroup group) {
    for (int i = 0, count = group.getChildCount(); i < count; ++i) {
      View view = group.getChildAt(i);
      if (view instanceof EditText) {
        ((EditText) view).setText("");
      } else if (view instanceof Spinner) {
        ((Spinner) view).setSelection(0);
      } else if (view instanceof RadioButton) {
        ((RadioButton) view).setChecked(false);
      }
      if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0)) {
        clearForm((ViewGroup) view);
      }
    }
  }

}
