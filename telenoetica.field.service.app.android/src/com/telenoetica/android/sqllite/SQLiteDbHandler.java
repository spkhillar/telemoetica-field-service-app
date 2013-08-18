/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.sqllite;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.telenoetica.android.rest.AndroidConstants;
import com.telenoetica.android.rest.AppValuesHolder;

/**
 * The Class SQLiteDbHandler.
 */
public class SQLiteDbHandler extends AbstractSQLiteDbHandler {

  /**
   * Instantiates a new sQ lite db handler.
   *
   * @param context the context
   */
  public SQLiteDbHandler(final Context context) {
    super(context);
  }

  /**
   * Validate user.
   *
   * @param username the username
   * @param password the password
   * @return true, if successful
   */
  public boolean validateUser(final String username, final String password) {
    SQLiteDatabase db = getWritableDatabase();
    String selectQuery =
        "SELECT  * FROM " + getCredentialsTable() + " where username='" + username + "' and password='" + password
        + "'";
    LOGGER.debug("validateUser .. Checking " + username + " in Phone Db.");
    boolean found = false;
    try {
      Cursor cursor = db.rawQuery(selectQuery, null);
      // looping through all rows and adding to list
      if (cursor.moveToFirst()) {
        do {
          found = true;
        } while (cursor.moveToNext());
      }
      cursor.close();
      db.close();
    } catch (Exception e) {
      LOGGER.error("validateUser  Failed....", e);
    }
    return found;
  }

  /**
   * Validate user.
   *
   * @param username the username
   * @return true, if successful
   */
  public boolean validateUser(final String username) {
    SQLiteDatabase db = getReadableDatabase();
    String selectQuery = "SELECT  * FROM " + getCredentialsTable() + " where username='" + username + "'";
    LOGGER.debug("check if .. " + username + " exists in db in Phone Db.");
    boolean found = false;
    try {
      Cursor cursor = db.rawQuery(selectQuery, null);
      // looping through all rows and adding to list
      if (cursor.moveToFirst()) {
        do {
          found = true;
        } while (cursor.moveToNext());
      }
      cursor.close();
      db.close();
    } catch (Exception e) {
      LOGGER.error("validateUser  Failed....", e);
    }
    return found;
  }

  /**
   * Insert or update user.
   *
   * @param username the username
   * @param password the password
   */
  public void insertOrUpdateUser(final String username, final String password) {
    SQLiteDatabase db = null;
    ;
    LOGGER.debug("insertUser .. " + username + " in Phone Db.");
    try {
      ContentValues values = new ContentValues();
      boolean valid = validateUser(username);
      db = getWritableDatabase();
      if (valid) {
        values.put("password", password);
        db.update(getCredentialsTable(), values, "username='" + username + "'", null);
      } else {
        values.put("username", username);
        values.put("password", password);
        db.insert(getCredentialsTable(), null, values);
      }
    } catch (Exception e) {
      LOGGER.error("insertUser  Failed....", e);
    }
    db.close(); // Closing database connection
  }

  /**
   * Check base data in system.
   */
  public void checkBaseDataInSystem() {
    SQLiteDatabase db = getWritableDatabase();
    String baseQuery = "SELECT name FROM ";

    List<String> dataList = null;
    String selectQuery = baseQuery + getClientsTable();
    if (AppValuesHolder.getClients().size() == 1) {
      dataList = getNamesList(db, selectQuery);
      AppValuesHolder.setClients(dataList);
    }

    selectQuery = baseQuery + getFaultsTable();
    if (AppValuesHolder.getFaults().size() == 1) {
      dataList = getNamesList(db, selectQuery);
      AppValuesHolder.setFaults(dataList);
    }

    selectQuery = baseQuery + getMaintenanceTable();
    if (AppValuesHolder.getMaintenanceCategories().size() == 1) {
      dataList = getNamesList(db, selectQuery);
      AppValuesHolder.setMaintenanceCategories(dataList);
    }

    selectQuery = baseQuery + getSitesTable();
    if (AppValuesHolder.getSites().size() == 1) {
      dataList = getNamesList(db, selectQuery);
      AppValuesHolder.setSites(dataList);
    }

    selectQuery = baseQuery + getSparesTable();
    if (AppValuesHolder.getSpares().size() == 1) {
      dataList = getNamesList(db, selectQuery);
      AppValuesHolder.setSpares(dataList);
    }
    selectQuery = baseQuery + getDieselVendor();
    if (AppValuesHolder.getDieselVendors().size() == 1) {
      dataList = getNamesList(db, selectQuery);
      AppValuesHolder.setDieselVendors(dataList);
    }
    db.close();
  }

  /**
   * Check and insert base data.
   */
  public void checkAndInsertBaseData() {
    SQLiteDatabase db = getWritableDatabase();
    long count = DatabaseUtils.queryNumEntries(db, getMaintenanceTable());

    if (count <= 0) {
      try {
        LOGGER.debug("Inserting Base Data...");
        db.beginTransaction();
        insertAndroidBaseData(db);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
      } catch (Exception e) {
        LOGGER.error("Exception...on insert base data", e);
      }
    }
    checkBaseDataInSystem();
  }

  /**
   * Reset configuration.
   *
   * @param resetSystem the reset system
   */
  public void resetConfiguration(final boolean resetSystem) {
    SQLiteDatabase db = getWritableDatabase();
    for (String tableName : SPINNER_TABLE_NAMES) {
      db.delete(tableName, null, null);
    }
    if (resetSystem) {
      db.delete(getVistisTable(), null, null);
    }
    AppValuesHolder.resetConfigData();
    db.close();
  }

  /**
   * Insert visit.
   *
   * @param jsonString the json string
   * @param clazzName the clazz name
   * @return the long
   */
  public long insertVisit(final String jsonString, final String clazzName) {
    SQLiteDatabase db = getWritableDatabase();
    long insertedDbRowId = -1;
    LOGGER.debug("insertVisit .. " + clazzName + " in Phone Db." + jsonString);
    try {
      ContentValues values = new ContentValues();
      values.put("json", jsonString);
      values.put("class", clazzName);
      values.put("tries", 0);
      values.put("status", AndroidConstants.INITIAL_STATUS);
      insertedDbRowId = db.insert(getVistisTable(), null, values);
    } catch (Exception e) {
      LOGGER.error("insertVisit  Failed....", e);
    }
    db.close();
    return insertedDbRowId;
  }

  /**
   * Gets the visits in system.
   *
   * @return the visits in system
   */
  public List<AndroidVisitSqLiteModel> getVisitsInSystem() {
    SQLiteDatabase db = getWritableDatabase();
    List<AndroidVisitSqLiteModel> dataList = new ArrayList<AndroidVisitSqLiteModel>();
    String selectQuery =
        "SELECT  * FROM " + getVistisTable() + " where status in ('" + AndroidConstants.INITIAL_STATUS + "','"
            + AndroidConstants.FAILED_STATUS + "')";
    LOGGER.debug("getVisitsInSystem .. " + selectQuery + " in Phone Db.");
    AndroidVisitSqLiteModel androidVisitSqLiteModel = null;
    try {
      Cursor cursor = db.rawQuery(selectQuery, null);
      // looping through all rows and adding to list
      boolean hasRecords = cursor.moveToFirst();
      if (hasRecords) {
        do {
          androidVisitSqLiteModel =
              new AndroidVisitSqLiteModel(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getInt(4));
          LOGGER.debug("Found visit data.." + androidVisitSqLiteModel);
          dataList.add(androidVisitSqLiteModel);
        } while (cursor.moveToNext());
        cursor.close();
      }
    } catch (Exception e) {
      LOGGER.error("Error getVisitsInSystem..", e);
    }
    db.close();
    return dataList;
  }

  /**
   * Update visit.
   *
   * @param androidVisitSqLiteModel the android visit sq lite model
   */
  public void updateVisit(final AndroidVisitSqLiteModel androidVisitSqLiteModel) {
    SQLiteDatabase db = getWritableDatabase();
    LOGGER.debug("updateVisit .. " + androidVisitSqLiteModel.getId() + " in Phone Db.");
    try {
      ContentValues values = new ContentValues();
      values.put("tries", androidVisitSqLiteModel.getTries());
      values.put("status", androidVisitSqLiteModel.getStatus());
      int updateCount = db.update(getVistisTable(), values, "id=" + androidVisitSqLiteModel.getId(), null);
      LOGGER.debug("..Rows Affected..." + updateCount);
    } catch (Exception e) {
      LOGGER.error("updateVisit  Failed....", e);
    }
    db.close();
  }

  /**
   * Delete visit.
   *
   * @param id the id
   */
  public void deleteVisit(final Long id) {
    SQLiteDatabase db = getWritableDatabase();
    int deleteCount = db.delete(getVistisTable(), "id=" + id, null);
    LOGGER.debug(id + "..Visit deleting Rows Affected..." + deleteCount);
    db.close();
  }

  /**
   * Find visit.
   *
   * @param id the id
   * @return the android visit sq lite model
   */
  public AndroidVisitSqLiteModel findVisit(final Long id) {
    SQLiteDatabase db = getWritableDatabase();
    String selectQuery = "SELECT  * FROM " + getVistisTable() + " where status id =" + id;
    LOGGER.debug("getVisit .. " + id + "--" + selectQuery + " in Phone Db.");
    AndroidVisitSqLiteModel androidVisitSqLiteModel = null;
    try {
      Cursor cursor = db.rawQuery(selectQuery, null);
      // looping through all rows and adding to list
      cursor.moveToFirst();
      do {
        androidVisitSqLiteModel =
            new AndroidVisitSqLiteModel(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
              cursor.getString(3), cursor.getInt(4));
        LOGGER.debug("Found visit data.." + androidVisitSqLiteModel);
      } while (cursor.moveToNext());
      cursor.close();
    } catch (Exception e) {
      LOGGER.error("Error getVisitsInSystem..", e);
    }
    db.close();
    return androidVisitSqLiteModel;
  }

  /**
   * Gets the names list.
   *
   * @param db the db
   * @param selectQuery the select query
   * @return the names list
   */
  private List<String> getNamesList(final SQLiteDatabase db, final String selectQuery) {
    List<String> dataList = new ArrayList<String>();
    try {
      Cursor cursor = db.rawQuery(selectQuery, null);
      // looping through all rows and adding to list
      cursor.moveToFirst();
      do {
        dataList.add(cursor.getString(0));
      } while (cursor.moveToNext());

      cursor.close();
    } catch (Exception e) {
      LOGGER.error("Error populating..", selectQuery);
    }
    return dataList;
  }

  /**
   * Insert android base data.
   *
   * @param db the db
   */
  private void insertAndroidBaseData(final SQLiteDatabase db) {
    String sql = null;
    if (!CollectionUtils.isEmpty(AppValuesHolder.getSpares())) {
      sql = getBaseDataInsertSql(getSparesTable());
      insertBaseData(db, sql, AppValuesHolder.getSpares());
    }
    if (!CollectionUtils.isEmpty(AppValuesHolder.getClients())) {
      sql = getBaseDataInsertSql(getClientsTable());
      insertBaseData(db, sql, AppValuesHolder.getClients());
    }

    if (!CollectionUtils.isEmpty(AppValuesHolder.getFaults())) {
      sql = getBaseDataInsertSql(getFaultsTable());
      insertBaseData(db, sql, AppValuesHolder.getFaults());
    }

    if (!CollectionUtils.isEmpty(AppValuesHolder.getMaintenanceCategories())) {
      sql = getBaseDataInsertSql(getMaintenanceTable());
      insertBaseData(db, sql, AppValuesHolder.getMaintenanceCategories());
    }
    if (!CollectionUtils.isEmpty(AppValuesHolder.getSites())) {
      sql = getBaseDataInsertSql(getSitesTable());
      insertBaseData(db, sql, AppValuesHolder.getSites());
    }
    if (!CollectionUtils.isEmpty(AppValuesHolder.getDieselVendors())) {
      sql = getBaseDataInsertSql(getDieselVendor());
      insertBaseData(db, sql, AppValuesHolder.getDieselVendors());
    }
  }

  /**
   * Insert base data.
   *
   * @param db the db
   * @param sql the sql
   * @param data the data
   */
  private void insertBaseData(final SQLiteDatabase db, final String sql, final List<String> data) {
    SQLiteStatement stmt = db.compileStatement(sql);
    for (int i = 1; i < data.size(); i++) {
      stmt.bindString(1, data.get(i));
      stmt.execute();
      stmt.clearBindings();

    }
  }

  /**
   * Gets the base data insert sql.
   *
   * @param tableName the table name
   * @return the base data insert sql
   */
  private String getBaseDataInsertSql(final String tableName) {
    String sql = "INSERT INTO " + tableName + " (name) VALUES (?)";
    return sql;
  }

}
