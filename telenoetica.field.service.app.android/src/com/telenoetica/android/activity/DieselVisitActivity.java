/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.activity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.jpa.entities.DieselVisit;

/**
 * The Class DieselVisitActivity.
 */
public class DieselVisitActivity extends AbstractVisitActivity {

  /** The button submit. */
  private Button buttonSubmit;

  /**
   * Adds the listener on button submit.
   */
  public void addListenerOnButtonSubmit() {
    buttonSubmit = (Button) findViewById(R.id.btn_dv_submit);
    buttonSubmit.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        // intent = new Intent(context, MaintainenceVisit.class);
        // startActivity(intent);
        renderConfirmationDialog();
      }
    });
  }

  /**
   * Initialize activity.
   *
   * @param savedInstanceState the saved instance state
   * @see com.telenoetica.android.activity.ApplicationBaseActivity#initializeActivity(android.os.Bundle)
   */
  @Override
  protected void initializeActivity(final Bundle savedInstanceState) {
    // checkForUserIdandPassword();
    setContentView(R.layout.diesel_visit);
    addListenerOnButtonSubmit();
    registerListenerForDieselTransferOrBulk();
    registerListenerForPhcn();
    registerListenerForHybridPiu();
    addItemsOnSpinner(R.id.etBulk, AppValuesHolder.getDieselVendors());
    setupAutoCompleteSite();
    addValuesOnSpinnerDieselDensity();
  }

  /**
   * Adds the values on spinner diesel density.
   */
  private void addValuesOnSpinnerDieselDensity() {
    Spinner spinnerDieselDensity = (Spinner) findViewById(R.id.spinnerDieselDensity);
    String dieselDensityArray[] =
        new String[] { "0.75", "0.76", "0.77", "0.78", "0.79", "0.80", "0.81", "0.82", "0.83", "0.84", "0.85", "0.86",
        "0.87", "0.88", "0.89", "0.90", "0.91", "0.92", "0.93", "0.94", "0.95" };

    ArrayAdapter<String> dataAdapter =
        new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dieselDensityArray);
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerDieselDensity.setAdapter(dataAdapter);

  }

  /**
   * Save current activity.
   *
   * @see com.telenoetica.android.activity.AbstractVisitActivity#saveCurrentActivity()
   */
  @Override
  public void saveCurrentActivity() {
    // TODO Auto-generated method stub
    final Map<String, Object> valueMap = new LinkedHashMap<String, Object>();
    ViewGroup group = (ViewGroup) findViewById(R.id.ll2_dv);
    List<String> errorList = new ArrayList<String>();
    getTargetObject(group, valueMap, errorList);
    validateDieselSupply(errorList);
    if (CollectionUtils.isEmpty(errorList)) {
      DieselVisit dieselVisit = new DieselVisit();
      dieselVisit.setUserId(AppValuesHolder.getCurrentUser());
      saveVisit(dieselVisit, valueMap);
    } else {
      LOGGER.error("Validation failed");
    }
  }

  /**
   * Validate diesel supply.
   *
   * @param errorList the error list
   */
  private void validateDieselSupply(final List<String> errorList) {
    RadioGroup rg = (RadioGroup) findViewById(R.id.radioTransferBulk);
    int checkedRadioButtonId = rg.getCheckedRadioButtonId();
    if (R.id.radioBulk == checkedRadioButtonId) {
      Spinner spinnerDieselVendor = (Spinner) findViewById(R.id.etBulk);
      if (spinnerDieselVendor.getSelectedItem() != null
          && "Browse & Select".equals(spinnerDieselVendor.getSelectedItem())) {
        // show toast diesel vendor is required
        errorList.add("Diesel Vendor is required");
        Toast.makeText(this, "Diesel Vendor is required", Toast.LENGTH_LONG).show();
      }
    } else {
      AutoCompleteTextView autoCompleteTextViewTransfer = (AutoCompleteTextView) findViewById(R.id.etTransfer);
      if (autoCompleteTextViewTransfer != null
          && StringUtils.isNotBlank(autoCompleteTextViewTransfer.getText().toString())) {
        // show toast transferred site id is required
        errorList.add("Transferred Site Id is required");
        Toast.makeText(this, "Transferred Site Id is required", Toast.LENGTH_LONG).show();
      }

    }
  }

  /**
   * Register listener for diesel transfer or bulk.
   */
  public void registerListenerForDieselTransferOrBulk() {
    final RadioGroup rg = (RadioGroup) findViewById(R.id.radioTransferBulk);
    rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(final RadioGroup group, final int checkedId) {
        // checkedId is the RadioButton selected
        if (R.id.radioBulk == checkedId) {
          Spinner spinnerDieselVendor = (Spinner) findViewById(R.id.etBulk);
          if (spinnerDieselVendor != null) {
            LOGGER.error("Mandatory Field");
          }
          spinnerDieselVendor.setEnabled(true);
          spinnerDieselVendor.setFocusableInTouchMode(true);
          spinnerDieselVendor.requestFocus();
          AutoCompleteTextView autoTransfer = (AutoCompleteTextView) findViewById(R.id.etTransfer);
          autoTransfer.setEnabled(false);
          autoTransfer.setFocusableInTouchMode(false);
          autoTransfer.clearFocus();
          autoTransfer.setText("");

        }
        if (R.id.radioTransfer == checkedId) {
          AutoCompleteTextView autoTransfer = (AutoCompleteTextView) findViewById(R.id.etTransfer);
          autoTransfer.setEnabled(true);
          autoTransfer.setFocusableInTouchMode(true);
          autoTransfer.requestFocus();
          Spinner spinnerDieselVendor = (Spinner) findViewById(R.id.etBulk);
          spinnerDieselVendor.setEnabled(false);
          spinnerDieselVendor.setFocusableInTouchMode(false);
          spinnerDieselVendor.clearFocus();
          spinnerDieselVendor.setSelection(0);
        }

      }
    });
  }

  /**
   * Register listener for phcn.
   */
  public void registerListenerForPhcn() {

    final RadioGroup rg1 = (RadioGroup) findViewById(R.id.radioPhcnInstalled);
    rg1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(final RadioGroup group, final int checkedId) {
        // checkedId is the RadioButton selected
        if (R.id.radioPhcnInstalledYes == checkedId) {
          EditText edt5 = (EditText) findViewById(R.id.etPHCNHoursPerDay);
          edt5.setEnabled(true);
          edt5.setInputType(InputType.TYPE_CLASS_NUMBER);
          edt5.setFocusableInTouchMode(true);
          edt5.requestFocus();
        } else if (R.id.radioPhcnInstalledNo == checkedId) {

          EditText edt = (EditText) findViewById(R.id.etPHCNHoursPerDay);
          edt.setEnabled(false);
          edt.setInputType(InputType.TYPE_NULL);
          edt.setFocusableInTouchMode(false);
          edt.clearFocus();

        }
      }
    });
  }

  /**
   * Register listener for hybrid piu.
   */
  public void registerListenerForHybridPiu() {

    final RadioGroup rg = (RadioGroup) findViewById(R.id.radioHybridPiuInstalled);
    rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(final RadioGroup group, final int checkedId) {
        if (R.id.radioHybridPiuInstalledYes == checkedId) {
          EditText edt = (EditText) findViewById(R.id.etHybridPiuHoursPerDay);
          edt.setEnabled(true);
          edt.setInputType(InputType.TYPE_CLASS_NUMBER);
          edt.setFocusableInTouchMode(true);
          edt.requestFocus();
        } else if (R.id.radioHybridPiuInstalledNo == checkedId) {
          EditText edt = (EditText) findViewById(R.id.etHybridPiuHoursPerDay);
          edt.setEnabled(false);
          edt.setInputType(InputType.TYPE_NULL);
          edt.setFocusableInTouchMode(false);
          edt.clearFocus();
        }

      }
    });

  }

}
