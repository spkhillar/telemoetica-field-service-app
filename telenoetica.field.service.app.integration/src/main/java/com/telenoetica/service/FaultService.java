/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service;

import java.util.List;

import com.telenoetica.jpa.entities.Fault;

/**
 * The Interface FaultService.
 */
public interface FaultService extends BaseService<Fault>{

  /**
   * Gets the faults.
   *
   * @return the faults
   */
  List<Fault> getFaults();
}
