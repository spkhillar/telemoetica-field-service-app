/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.web.util;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * The Class JqGridObjectMapper.
 *
 * @author  Shiv Prasad Khillar
 */
public class JqGridObjectMapper {
	
	/**
	 * Map.
	 *
	 * @param jsonString the json string
	 * @return the jq grid filter
	 */
	public static JqGridFilter map(String jsonString) {
		
    	if (jsonString != null) {
        	ObjectMapper mapper = new ObjectMapper();
        	
        	try {
				return mapper.readValue(jsonString, JqGridFilter.class);
        	} catch (Exception e) {
				throw new RuntimeException (e);
			}
    	}
    	
		return null;
	}

}
