/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telenoetica.jpa.entities.MaintenanceVisitCategory;
import com.telenoetica.jpa.repositories.MaintenanceVisitCategoryDAO;
import com.telenoetica.service.MaintenanceVisitCategoryService;

/**
 * The Class MaintenanceVisitCategoryServiceImpl.
 */
@Service("maintenanceVisitCategoryService")
public class MaintenanceVisitCategoryServiceImpl implements MaintenanceVisitCategoryService {

  /** The maintenance visit category dao. */
  @Autowired
  private MaintenanceVisitCategoryDAO maintenanceVisitCategoryDAO;

  /**
   * Retrieve.
   *
   * @param id the id
   * @return the maintenance visit category
   * @see com.telenoetica.service.BaseService#retrieve(java.lang.Long)
   */
  @Override
  public MaintenanceVisitCategory retrieve(final Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Save or update.
   *
   * @param baseEntity the base entity
   * @return the maintenance visit category
   * @see com.telenoetica.service.BaseService#saveOrUpdate(com.telenoetica.jpa.entities.BaseEntity)
   */
  @Override
  public MaintenanceVisitCategory saveOrUpdate(final MaintenanceVisitCategory baseEntity) {
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
  public void delete(final MaintenanceVisitCategory baseEntity) {
    // TODO Auto-generated method stub

  }

  /**
   * Gets the categories.
   *
   * @return the categories
   * @see com.telenoetica.service.MaintenanceVisitCategoryService#getCategories()
   */
  @Override
  public List<MaintenanceVisitCategory> getCategories() {
    return maintenanceVisitCategoryDAO.findAll();
  }

}
