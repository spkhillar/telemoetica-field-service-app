package com.telenoetica.android.rest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AppValuesPopulator {
  
  private static final String SITE_JSON_KEY = "sites";
  
  private static final String SPARE_JSON_KEY = "spares";
  
  private static final String CLIENT_JSON_KEY = "clients";
  
  private static final String FAULT_JSON_KEY = "faults";
  
  private static final String MAINTENANCE_CATEGORY_JSON_KEY = "maintenanceCategories";
  
  private static final String NAME_KEY = "name";
  
  private AppValuesPopulator(){
    
  }
  
  public static void populateValues(String userName,String password) throws JSONException{
    JSONObject jsonObject = RestClient.INSTANCE.executeGet("http://localhost:8080/fieldapp/home",userName,password);
    
    if(jsonObject != null){
    
    JSONArray jsonArray =jsonObject.getJSONArray(SITE_JSON_KEY);
    List<String> valueList = getJsonList(jsonArray);
    AppValuesHolder.setSites(valueList);
    
    jsonArray =jsonObject.getJSONArray(SPARE_JSON_KEY);
    valueList = getJsonList(jsonArray);
    AppValuesHolder.setSpares(valueList);
    
    jsonArray =jsonObject.getJSONArray(CLIENT_JSON_KEY);
    valueList = getJsonList(jsonArray);
    AppValuesHolder.setClients(valueList);
    
    jsonArray =jsonObject.getJSONArray(FAULT_JSON_KEY);
    valueList = getJsonList(jsonArray);
    AppValuesHolder.setFaults(valueList);
    
    jsonArray =jsonObject.getJSONArray(MAINTENANCE_CATEGORY_JSON_KEY);
    valueList = getJsonList(jsonArray);
    AppValuesHolder.setMaintenanceCategories(valueList);
    
    }
  }
  
  private static List<String> getJsonList(final JSONArray jsonArray) throws JSONException{
    List<String> valueList = new ArrayList<String>();
    int jsonArrayLen =0;
    JSONObject jsonObject = null;
    if(jsonArray == null || jsonArray.length() == 0){
      return valueList;
    }
    jsonArrayLen = jsonArray.length();
    
    for (int index = 0; index < jsonArrayLen; index++) {
      jsonObject = jsonArray.getJSONObject(index);
      valueList.add(jsonObject.getString(NAME_KEY));
    }
    return valueList;
    
    
  }

}
