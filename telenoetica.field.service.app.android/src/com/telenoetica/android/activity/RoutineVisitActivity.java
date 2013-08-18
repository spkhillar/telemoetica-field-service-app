/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.activity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.jpa.entities.RoutineVisit;

/**
 * The Class RoutineVisitActivity.
 */
public class RoutineVisitActivity extends AbstractVisitActivity {

  /** The button1. */
  private Button button1;

  /**
   * Initialize activity.
   *
   * @param savedInstanceState the saved instance state
   * @see com.telenoetica.android.activity.ApplicationBaseActivity#initializeActivity(android.os.Bundle)
   */
  @Override
  protected void initializeActivity(final Bundle savedInstanceState) {
    // checkForUserIdandPassword();
    setContentView(R.layout.routine_visit);
    addListenerOnButtonSubmit();
    setupAutoCompleteSite();
  }

  /**
   * Adds the listener on button submit.
   */
  public void addListenerOnButtonSubmit() {
    button1 = (Button) findViewById(R.id.btn_rv_submit);
    button1.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        // Intent intent = new Intent(context, MainMenu.class);
        // startActivity(intent);
        // Write your code here to invoke YES event
        // Toast.makeText(getApplicationContext(), "You clicked on YES",
        // Toast.LENGTH_SHORT).show();
        renderConfirmationDialog();
      }
    });
  }

  /**
   * Save current activity.
   *
   * @see com.telenoetica.android.activity.AbstractVisitActivity#saveCurrentActivity()
   */
  @Override
  public void saveCurrentActivity() {
    final Map<String, Object> valueMap = new LinkedHashMap<String, Object>();
    ViewGroup group = (ViewGroup) findViewById(R.id.ll1_rv);
    List<String> errorList = new ArrayList<String>();
    getTargetObject(group, valueMap, errorList);
    if (CollectionUtils.isEmpty(errorList)) {
      RoutineVisit routineVisit = new RoutineVisit();
      routineVisit.setUserId(AppValuesHolder.getCurrentUser());
      saveVisit(routineVisit, valueMap);
    } else {
      LOGGER.error("Validation failed");
    }
  }

}
