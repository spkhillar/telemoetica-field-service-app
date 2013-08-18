/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.sqllite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * The Class AbstractSQLiteDbHandler.
 */
public abstract class AbstractSQLiteDbHandler extends SQLiteOpenHelper {


  /** The Constant LOGGER. */
  protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractSQLiteDbHandler.class);

  /** The Constant DATABASE_NAME. */
  private static final String DATABASE_NAME = "field_service_db";

  /** The Constant DB_APP_VERSION. */
  private static final int DB_APP_VERSION = 1;

  /** The context. */
  @SuppressWarnings("unused")
  private Context context;

  /** The Constant SPINNER_TABLE_NAMES. */
  protected static final String[] SPINNER_TABLE_NAMES =
      new String[] { "spare", "client", "fault", "site", "maintenance","diesel_vendor" };

  /** The Constant FINAL_TABLE_LIST. */
  protected static final List<String> FINAL_TABLE_LIST = new ArrayList<String>(Arrays.asList(SPINNER_TABLE_NAMES));

  /**
   * Instantiates a new abstract sq lite db handler.
   *
   * @param context the context
   * @param name the name
   * @param factory the factory
   * @param version the version
   */
  public AbstractSQLiteDbHandler(final Context context, final String name, final CursorFactory factory, final int version) {
    super(context, name, null, version);
    this.context = context;
    FINAL_TABLE_LIST.add("configuration");
    FINAL_TABLE_LIST.add("credential");
    FINAL_TABLE_LIST.add("visit");
  }

  /**
   * Instantiates a new abstract sq lite db handler.
   *
   * @param context the context
   */
  public AbstractSQLiteDbHandler(final Context context) {
    this(context, DATABASE_NAME, null, DB_APP_VERSION);
  }

  /**
   * On create.
   *
   * @param db the db
   * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
   */
  @Override
  public void onCreate(final SQLiteDatabase db) {
    LOGGER.debug("creating table started");
    try {
      String finalCreateSqlQuery;
      for (String spinnerTableName : SPINNER_TABLE_NAMES) {
        finalCreateSqlQuery = getSpinnerTableSqlScript(spinnerTableName);
        db.execSQL(finalCreateSqlQuery);
      }
      db.execSQL(getConfigurationTableSqlScript());
      db.execSQL(getCredentialTableSqlScript());
      db.execSQL(getVisitTableSqlScript());
    } catch (SQLException e) {
      LOGGER.error("SQL Exception while creating tables..",e);
    }
    LOGGER.debug("creating table ends");
  }

  /**
   * On upgrade.
   *
   * @param db the db
   * @param oldVersion the old version
   * @param newVersion the new version
   * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
   */
  @Override
  public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
    LOGGER.debug("...onUpgrade...starts");
    String baseQuery = "DROP TABLE IF EXISTS ";
    String finalDropSqlQuery = null;
    try {
      for (String spinnerTableName : SPINNER_TABLE_NAMES) {
        finalDropSqlQuery = baseQuery + spinnerTableName;
        db.execSQL(finalDropSqlQuery);
      }
      finalDropSqlQuery = baseQuery + "configuration";
      db.execSQL(finalDropSqlQuery);
      finalDropSqlQuery = baseQuery + "credential";
      db.execSQL(finalDropSqlQuery);
      finalDropSqlQuery = baseQuery + "visit";
      db.execSQL(finalDropSqlQuery);
    } catch (SQLException e) {
      LOGGER.error("SQL Exception while droping tables tables..",e);
    }
    onCreate(db);
    LOGGER.debug("...onUpgrade...ends");
  }

  /**
   * Gets the spinner table sql script.
   *
   * @param tableName the table name
   * @return the spinner table sql script
   */
  private String getSpinnerTableSqlScript(final String tableName) {
    String sqlCreateQuery =
        "CREATE TABLE if not exists " + tableName + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name" + " TEXT)";
    return sqlCreateQuery;
  }

  /**
   * Gets the credential table sql script.
   *
   * @return the credential table sql script
   */
  private String getCredentialTableSqlScript() {
    String sqlCreateQuery =
        "CREATE TABLE if not exists " + "credential" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "username" + " TEXT,"
            + "password" + " TEXT)";
    return sqlCreateQuery;
  }

  /**
   * Gets the configuration table sql script.
   *
   * @return the configuration table sql script
   */
  private String getConfigurationTableSqlScript() {
    String sqlCreateQuery =
        "CREATE TABLE if not exists " + "configuration" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name" + " TEXT,"
            + "value" + " TEXT)";
    return sqlCreateQuery;
  }

  /**
   * Gets the visit table sql script.
   *
   * @return the visit table sql script
   */
  private String getVisitTableSqlScript() {
    String sqlCreateQuery =
        "CREATE TABLE if not exists " + "visit" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "json" + " TEXT,"
            + "status" + " TEXT, class TEXT, tries INTEGER)";
    return sqlCreateQuery;
  }

  /**
   * Gets the spares table.
   *
   * @return the spares table
   */
  public String getSparesTable(){
    return FINAL_TABLE_LIST.get(0);
  }

  /**
   * Gets the clients table.
   *
   * @return the clients table
   */
  public String getClientsTable(){
    return FINAL_TABLE_LIST.get(1);
  }

  /**
   * Gets the faults table.
   *
   * @return the faults table
   */
  public String getFaultsTable(){
    return FINAL_TABLE_LIST.get(2);
  }

  /**
   * Gets the sites table.
   *
   * @return the sites table
   */
  public String getSitesTable(){
    return FINAL_TABLE_LIST.get(3);
  }

  /**
   * Gets the maintenance table.
   *
   * @return the maintenance table
   */
  public String getMaintenanceTable(){
    return FINAL_TABLE_LIST.get(4);
  }

  /**
   * Gets the diesel vendor.
   *
   * @return the diesel vendor
   */
  public String getDieselVendor(){
    return FINAL_TABLE_LIST.get(5);
  }

  /**
   * Gets the configurations table.
   *
   * @return the configurations table
   */
  public String getConfigurationsTable(){
    return FINAL_TABLE_LIST.get(6);
  }

  /**
   * Gets the credentials table.
   *
   * @return the credentials table
   */
  public String getCredentialsTable(){
    return FINAL_TABLE_LIST.get(7);
  }

  /**
   * Gets the vistis table.
   *
   * @return the vistis table
   */
  public String getVistisTable(){
    return FINAL_TABLE_LIST.get(8);
  }
}
