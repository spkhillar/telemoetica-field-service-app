/* Copyright (C) 2013 Telenoetica, Inc. All rights reserved */
package com.telenoetica.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telenoetica.jpa.entities.Site;

/**
 * The Interface SiteDAO.
 *
 * @author  Shiv Prasad Khillar
 */
public interface SiteDAO extends JpaRepository<Site, Long> {

  /**
   * Find by name.
   *
   * @param name the name
   * @return the site
   */
  Site findByName(String name);
}
