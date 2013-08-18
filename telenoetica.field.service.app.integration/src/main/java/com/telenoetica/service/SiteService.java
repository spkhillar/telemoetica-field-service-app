/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.telenoetica.jpa.entities.Site;

/**
 * The Interface SiteService.
 */
public interface SiteService extends BaseService<Site>{

  /**
   * Gets the sites.
   *
   * @param pageNumber the page number
   * @return the sites
   */
  public Page<Site> getSites(Integer pageNumber);

  /**
   * Gets the sites.
   *
   * @return the sites
   */
  List<Site> getSites();

  /**
   * Find site.
   *
   * @param name the name
   * @return the site
   */
  Site findSite(String name);
}
