/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service;

import java.util.List;

import com.telenoetica.jpa.entities.MaintenanceVisitCategory;

/**
 * The Interface MaintenanceVisitCategoryService.
 */
public interface MaintenanceVisitCategoryService extends BaseService<MaintenanceVisitCategory> {

  /**
   * Gets the categories.
   *
   * @return the categories
   */
  List<MaintenanceVisitCategory> getCategories();
}
