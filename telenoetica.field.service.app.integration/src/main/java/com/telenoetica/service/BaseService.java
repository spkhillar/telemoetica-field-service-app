/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service;

import com.telenoetica.jpa.entities.BaseEntity;


/**
 * The Interface BaseService.
 * @author Satyam
 *
 * @param <E> the element type
 */
public interface BaseService<E extends BaseEntity>{

  /**
   * Retrieve.
   *
   * @param id the id
   * @return the e
   */
  public E retrieve(Long id);

  /**
   * Save or update.
   *
   * @param baseEntity the base entity
   * @return the e
   */
  public E saveOrUpdate(E baseEntity);

  /**
   * Delete.
   *
   * @param baseEntity the base entity
   */
  public void delete(E baseEntity);
}
