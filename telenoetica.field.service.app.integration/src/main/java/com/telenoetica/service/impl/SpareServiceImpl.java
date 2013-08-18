/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.telenoetica.jpa.entities.Spare;
import com.telenoetica.jpa.repositories.SpareDAO;
import com.telenoetica.service.SpareService;

/**
 * The Class SpareServiceImpl.
 */
@Service("spareService")
public class SpareServiceImpl implements SpareService {

  /** The spare dao. */
  @Autowired
  private SpareDAO spareDAO;

  /**
   * Retrieve.
   *
   * @param id the id
   * @return the spare
   * @see com.telenoetica.service.BaseService#retrieve(java.lang.Long)
   */
  @Override
  public Spare retrieve(final Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Save or update.
   *
   * @param baseEntity the base entity
   * @return the spare
   * @see com.telenoetica.service.BaseService#saveOrUpdate(com.telenoetica.jpa.entities.BaseEntity)
   */
  @Override
  public Spare saveOrUpdate(final Spare baseEntity) {
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
  public void delete(final Spare baseEntity) {
    // TODO Auto-generated method stub

  }

  /**
   * Gets the spares.
   *
   * @return the spares
   * @see com.telenoetica.service.SpareService#getSpares()
   */
  @Override
  public List<Spare> getSpares() {
    return spareDAO.findAll(new Sort("name"));
  }

}
