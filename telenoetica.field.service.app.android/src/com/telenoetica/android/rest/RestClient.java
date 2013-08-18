/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.android.rest;

import java.util.Collections;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * The Enum RestClient.
 */
public enum RestClient {

  /** The instance. */
  INSTANCE;

  /**
   * Execute rest.
   *
   * @param <T> the generic type
   * @param url the url
   * @param userName the user name
   * @param password the password
   * @param httpMethod the http method
   * @param requestObject the request object
   * @param returnType the return type
   * @param contentType the content type
   * @return the t
   */
  public <T> T executeRest(final String url, final String userName, final String password, final HttpMethod httpMethod,
      final Object requestObject, final Class<T> returnType, final MediaType contentType) {
    HttpAuthentication authHeader = new HttpBasicAuthentication(userName, password);
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setAuthorization(authHeader);
    requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    // Create a new RestTemplate instance
    HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory =
        new HttpComponentsClientHttpRequestFactory();
    httpComponentsClientHttpRequestFactory.setConnectTimeout(30*1000);
    RestTemplate restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory);
    restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
    ResponseEntity<T> response = null;
    if (contentType != null) {
      requestHeaders.setContentType(contentType);
    }
    try {
      response =
          restTemplate.exchange(url, httpMethod, new HttpEntity<Object>(requestObject, requestHeaders), returnType);
    } catch (RestClientException e) {
      throw new RuntimeException(e);
    }
    if (response != null) {
      return response.getBody();
    }
    return null;
  }
}
