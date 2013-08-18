/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service;

import java.util.List;

import com.telenoetica.jpa.entities.Spare;

/**
 * The Interface SpareService.
 */
public interface SpareService extends BaseService<Spare>{

  /**
   * Gets the spares.
   *
   * @return the spares
   */
  List<Spare> getSpares();
}
