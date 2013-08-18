/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.repositories.SiteDAO;
import com.telenoetica.service.SiteService;

/**
 * The Class SiteServiceImpl.
 */
@Service("siteService")
@Transactional
public class SiteServiceImpl implements SiteService {

  /** The site dao. */
  @Autowired
  private SiteDAO siteDAO;


  /**
   * Retrieve.
   *
   * @param id the id
   * @return the site
   * @see com.telenoetica.service.BaseService#retrieve(java.lang.Long)
   */
  @Override
  public Site retrieve(final Long id) {
    return siteDAO.findOne(id);
  }

  /**
   * Save or update.
   *
   * @param baseEntity the base entity
   * @return the site
   * @see com.telenoetica.service.BaseService#saveOrUpdate(com.telenoetica.jpa.entities.BaseEntity)
   */
  @Override
  public Site saveOrUpdate(final Site baseEntity) {
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
  public void delete(final Site baseEntity) {
    // TODO Auto-generated method stub

  }

  /**
   * Gets the sites.
   *
   * @param pageNumber the page number
   * @return the sites
   * @see com.telenoetica.service.SiteService#getSites(java.lang.Integer)
   */
  @Override
  public Page<Site> getSites(final Integer pageNumber) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Gets the sites.
   *
   * @return the sites
   * @see com.telenoetica.service.SiteService#getSites()
   */
  @Override
  public List<Site> getSites() {
    // TODO Auto-generated method stub
    return siteDAO.findAll();
  }

  /**
   * Find site.
   *
   * @param name the name
   * @return the site
   * @see com.telenoetica.service.SiteService#findSite(java.lang.String)
   */
  @Override
  public Site findSite(final String name) {
    return siteDAO.findByName(name);
  }

}
