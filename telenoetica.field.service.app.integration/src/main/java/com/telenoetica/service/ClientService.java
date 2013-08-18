/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service;

import java.util.List;

import com.telenoetica.jpa.entities.Client;

/**
 * The Interface ClientService.
 */
public interface ClientService extends BaseService<Client>{

  /**
   * Gets the clients.
   *
   * @return the clients
   */
  List<Client> getClients();
}
