/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.telenoetica.jpa.entities.Fault;
import com.telenoetica.jpa.repositories.FaultDAO;
import com.telenoetica.service.FaultService;

/**
 * The Class FaultServiceImpl.
 */
@Service("faultService")
public class FaultServiceImpl implements FaultService {

  /** The fault dao. */
  @Autowired
  private FaultDAO faultDAO;

  /**
   * Retrieve.
   *
   * @param id the id
   * @return the fault
   * @see com.telenoetica.service.BaseService#retrieve(java.lang.Long)
   */
  @Override
  public Fault retrieve(final Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Save or update.
   *
   * @param baseEntity the base entity
   * @return the fault
   * @see com.telenoetica.service.BaseService#saveOrUpdate(com.telenoetica.jpa.entities.BaseEntity)
   */
  @Override
  public Fault saveOrUpdate(final Fault baseEntity) {
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
  public void delete(final Fault baseEntity) {
    // TODO Auto-generated method stub

  }

  /**
   * Gets the faults.
   *
   * @return the faults
   * @see com.telenoetica.service.FaultService#getFaults()
   */
  @Override
  public List<Fault> getFaults() {
    // TODO Auto-generated method stub
    return faultDAO.findAll(new Sort("name"));
  }

}
