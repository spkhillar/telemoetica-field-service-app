/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.activity;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.android.rest.AppValuesPopulator;
import com.telenoetica.android.rest.RestClient;
import com.telenoetica.android.rest.RestResponse;

/**
 * The Class LoginActivity.
 */
public class LoginActivity extends ApplicationBaseActivity {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(LoginActivity.class);

  /** The button login. */
  private Button buttonLogin;

  /** The user name. */
  private EditText userName;

  /** The password. */
  private EditText password;

  /** The user exists in local. */
  private boolean userExistsInLocal;

  /** The uid. */
  private TextView uid;

  /** The pwd. */
  private TextView pwd;

  /**
   * Initialize activity.
   *
   * @param savedInstanceState the saved instance state
   * @see com.telenoetica.android.activity.ApplicationBaseActivity#initializeActivity(android.os.Bundle)
   */
  @Override
  protected void initializeActivity(final Bundle savedInstanceState) {
    setContentView(R.layout.login);
    addListenerOnButtonLogin();
    uid = (TextView) findViewById(R.id.textViewUserId);
    pwd = (TextView) findViewById(R.id.textViewPassword);
    uid.setTextSize(20);
    pwd.setTextSize(20);
  }

  /**
   * (non-Javadoc).
   *
   * @see android.app.Activity#onBackPressed()
   */
  @Override
  public void onBackPressed() {
    System.exit(0);
  }

  /**
   * Adds the listener on button login.
   */
  public void addListenerOnButtonLogin() {

    buttonLogin = (Button) findViewById(R.id.btn1_main);
    buttonLogin.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View arg0) {
        userName = (EditText) findViewById(R.id.et1_main_uid);
        password = (EditText) findViewById(R.id.et2_main_password);
        String[] array = new String[] { userName.getText().toString(), password.getText().toString() };
        LOGGER.info("Logging to the system...");
        if (StringUtils.isBlank(array[0]) || StringUtils.isBlank(array[1])) {
          RestResponse response = new RestResponse(500, "Username or password is blank.");
          doWithResponse(response);
        } else {
          userExistsInLocal = sqLiteDbHandler.validateUser(array[0], array[1]);
          if (userExistsInLocal) {
            LOGGER.info("User verified from local");
            sqLiteDbHandler.checkBaseDataInSystem();
            RestResponse response = new RestResponse(0, "Logged In");
            doWithResponse(response);
          } else {
            LoginAsyncTask task = new LoginAsyncTask();
            task.execute(array);
          }
        }
      }
    });
  }

  /**
   * Do with response.
   *
   * @param result the result
   */
  private void doWithResponse(final RestResponse result) {
    LOGGER.info("Logging to the system. done.." + result);
    int statusCode = result.getStatusCode();
    String uname = userName.getText().toString();
    String pwd = password.getText().toString();
    if (statusCode == 0) {
      if (!userExistsInLocal) {
        sqLiteDbHandler.insertOrUpdateUser(uname, pwd);
        sqLiteDbHandler.checkAndInsertBaseData();
      }
      Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
      Intent intent = new Intent(this, MainMenu.class);
      startActivity(intent);
    } else {
      Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
    }
    AppValuesHolder.setCurrentUser(uname);
    AppValuesHolder.setCurrentUserPassword(pwd);
  }

  /**
   * The Class LoginAsyncTask.
   */
  private class LoginAsyncTask extends AsyncTask<String, Void, RestResponse> {

    /** The pd. */
    private ProgressDialog pd;

    /**
     * On pre execute.
     *
     * @see android.os.AsyncTask#onPreExecute()
     */
    @Override
    protected void onPreExecute() {
      pd = new ProgressDialog(context);
      pd.setTitle("Processing...");
      pd.setMessage("Please wait.");
      pd.setCancelable(false);
      pd.setIndeterminate(true);
      pd.show();
    }

    /**
     * Do in background.
     *
     * @param params the params
     * @return the rest response
     * @see android.os.AsyncTask#doInBackground(Params[])
     */
    @Override
    protected RestResponse doInBackground(final String... params) {
      Date start = new Date();
      TelephonyManager   telephonyManager  =  ( TelephonyManager
          )getSystemService( Context.TELEPHONY_SERVICE );

      /*
       * getDeviceId() function Returns the unique device ID.
       * for example,the IMEI for GSM and the MEID or ESN for CDMA phones.
       */
      String imeistring = telephonyManager.getDeviceId();
      String url = AppValuesHolder.getHost() + "/rest/auth/"+imeistring;
      RestResponse response = null;
      try {
        LOGGER.debug("invoking..." + url);
        response =
            RestClient.INSTANCE.executeRest(url, params[0], params[1], HttpMethod.GET, null, RestResponse.class, null);
        if (AppValuesHolder.getClients().size() == 1) {
          AppValuesPopulator.populateValues(params[0], params[1]);
        }
      } catch (Exception e) {
        LOGGER.error("Exception...", e);
        if (e.getCause() instanceof HttpClientErrorException) {
          HttpStatus status = ((HttpClientErrorException) e.getCause()).getStatusCode();
          if (HttpStatus.UNAUTHORIZED.equals(status)) {
            response = new RestResponse(500, "Invalid Credentials. Check username and password");
          } else if (HttpStatus.FORBIDDEN.equals(status)) {
            response = new RestResponse(403, "Invalid Credentials. Check username and password");
          }
        }
      }
      if (response == null) {
        response = new RestResponse(500, "System Exception...");
      }
      Date end = new Date();
      long total = end.getTime() - start.getTime();
      LOGGER.debug("...Total Time..." + total);
      return response;
    }

    /**
     * On post execute.
     *
     * @param restResponse the rest response
     * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
     */
    @Override
    protected void onPostExecute(final RestResponse restResponse) {
      pd.dismiss();
      findViewById(R.id.btn1_main).setEnabled(true);
      doWithResponse(restResponse);
    }
  }
}
