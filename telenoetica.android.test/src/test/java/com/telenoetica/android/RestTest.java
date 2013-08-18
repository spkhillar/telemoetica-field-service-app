package com.telenoetica.android;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.telenoetica.android.rest.AppValuesHolder;
import com.telenoetica.android.rest.AppValuesPopulator;
import com.telenoetica.android.rest.RestClient;
import com.telenoetica.android.rest.RestResponse;

public class RestTest {

	//@Test
	public void test1() {
		try {
			AppValuesPopulator.populateValues("root", "root");
			List<String> list = AppValuesHolder.getSites();
			System.out.println("....sites..." + list);
			list = AppValuesHolder.getClients();
			System.out.println("....clients..." + list);
			list = AppValuesHolder.getFaults();
			System.out.println("....faults..." + list);
			list = AppValuesHolder.getMaintenanceCategories();
			System.out.println("....MaintenanceCategories..." + list);
			list = AppValuesHolder.getSpares();
			System.out.println("....Spares..." + list);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//@Test
	public void test2() {
		Map<String, Object> jsonValues = new HashMap<String, Object>();
		jsonValues.put("accessCode", "sunny");
		jsonValues.put("dieselLevelT1", 21);
		jsonValues.put("dieselLevelT2", 56);
		jsonValues.put("runHourGen1", 23);
		jsonValues.put("runHourGen2", 12);
		jsonValues.put("voltageNrVolts", 13);
		jsonValues.put("voltageNyVolts", 14);
		jsonValues.put("voltageNbVolts", 15);
		jsonValues.put("loadRPhaseAmps", 17);
		jsonValues.put("loadYPhaseAmps", 18);
		jsonValues.put("loadRPhaseAmps", 19);
		jsonValues.put("rectifierOpVoltage", 31);
		jsonValues.put("rectifierOpCurrentAmp", 32);
		jsonValues.put("batteryCapcityV", 33);
		jsonValues.put("batteryCapcityAh", 34);
		jsonValues.put("atsFunctional", Boolean.TRUE);
		jsonValues.put("atsSysncronising", Boolean.FALSE);
		jsonValues.put("drnBookAtSite", Boolean.TRUE);
		jsonValues.put("dieselLogBookMaintained", Boolean.TRUE);
		jsonValues.put("airconShelter1Cooling", "true");
		jsonValues.put("airconShelter2Cooling", "false");
		jsonValues.put("airconShelter3Cooling", "Not Applicable");
		jsonValues.put("airconShelter4Cooling", "Not Applicable");
		jsonValues.put("siteId", "HT/SE/EB/108");
		JSONObject obj = new JSONObject(jsonValues);
		RestResponse restResponse = RestClient.INSTANCE.executePost(
				"http://localhost:8080/fieldapp/routine/rest", obj, "sunny",
				"sunny");
		if (restResponse == null) {
			System.out.println("...Execption in android system");
		} else if (restResponse.getStatusCode() == 0) {
			System.out.println("...Rest Success.." + restResponse.getMessage());
		} else {
			System.out.println("...Rest Errored.." + restResponse.getMessage());
		}
	}

@Test
	public void testlogin() {
		RestResponse restResponse = RestClient.INSTANCE.executePost(
				"http://localhost:8080/fieldapp/rest/auth", null, "sunny",
				"sunny");
		
		if(restResponse != null){
			System.err.println("....Response ..."+restResponse);
		}
	}
}
