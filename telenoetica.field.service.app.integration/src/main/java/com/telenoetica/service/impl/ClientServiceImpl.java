/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telenoetica.jpa.entities.Client;
import com.telenoetica.jpa.repositories.ClientDAO;
import com.telenoetica.service.ClientService;

/**
 * The Class ClientServiceImpl.
 */
@Service("clientService")
public class ClientServiceImpl implements ClientService {

  /** The client dao. */
  @Autowired
  private ClientDAO clientDAO;

  /**
   * Retrieve.
   *
   * @param id the id
   * @return the client
   * @see com.telenoetica.service.BaseService#retrieve(java.lang.Long)
   */
  @Override
  public Client retrieve(final Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Save or update.
   *
   * @param baseEntity the base entity
   * @return the client
   * @see com.telenoetica.service.BaseService#saveOrUpdate(com.telenoetica.jpa.entities.BaseEntity)
   */
  @Override
  public Client saveOrUpdate(final Client baseEntity) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Delete.
   *
   * @param baseEntity the base entity
   * @see com.telenoetica.service.BaseService#delete(com.telenoetica.jpa.entities.BaseEntity)
   */
  @Override
  public void delete(final Client baseEntity) {
    // TODO Auto-generated method stub

  }

  /**
   * Gets the clients.
   *
   * @return the clients
   * @see com.telenoetica.service.ClientService#getClients()
   */
  @Override
  public List<Client> getClients() {
    // TODO Auto-generated method stub
    return clientDAO.findAll();
  }

}
