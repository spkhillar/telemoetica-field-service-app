package com.telenoetica.android.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;


public enum RestClient {

	INSTANCE;

	public JSONObject executeGet(String url,String userName,String password) {
		String finalJsonString = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(url);
			getRequest.addHeader("accept", "application/json");
			getUserCredentials(getRequest, userName, password);
			HttpResponse response = httpClient.execute(getRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}
			finalJsonString = getResponseJsonString(response.getEntity()
					.getContent());
			httpClient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getJsonObject(finalJsonString);
	}

	public RestResponse executePost(String url, JSONObject jsonObject,String userName,String password) {
		String finalJsonString = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(url);
			postRequest.addHeader("accept", "application/json");
			//postRequest.addHeader("Content-Type", "application/json");
			if(jsonObject != null){
			StringEntity stringEntity = new StringEntity(jsonObject.toString(),
					"UTF-8");
			postRequest.setEntity(stringEntity);
			}
			getUserCredentials(postRequest, userName, password);
			
			HttpResponse response = httpClient.execute(postRequest);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				return new RestResponse(500, "Internal Server Error");
				// throw new RuntimeException("Failed : HTTP error code : " +
				// response.getStatusLine().getStatusCode());
			}
			finalJsonString = getResponseJsonString(response.getEntity()
					.getContent());
			httpClient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getRestResponse(finalJsonString);
	}

	private RestResponse getRestResponse(String jsonString) {
		JSONObject returnedResponseJsonObject = getJsonObject(jsonString);

		RestResponse restResponse=null;
		try {
			restResponse = new RestResponse(
					returnedResponseJsonObject.getInt("statusCode"),
					returnedResponseJsonObject.getString("message"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return restResponse;
	}

	private JSONObject getJsonObject(String jsonString) {
		JSONObject jsonObject = null;
		if (!isBlank(jsonString)) {
			System.out.println("..jsonString.." + jsonString);
			try {
				jsonObject = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return jsonObject;
	}

	private String getResponseJsonString(InputStream is) throws IOException {
		StringBuilder jsonString = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,
				"UTF-8"));
		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			jsonString.append(output);
		}
		if (br != null) {
			br.close();
		}
		return jsonString.toString();
	}

	private boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}
	
	private void getUserCredentials(HttpRequest httpRequest,String userName,String password){
		String creds = userName+":"+password;
		httpRequest.addHeader("Authorization", "Basic " + Base64.encodeBase64String(creds.getBytes()));
	}
}
