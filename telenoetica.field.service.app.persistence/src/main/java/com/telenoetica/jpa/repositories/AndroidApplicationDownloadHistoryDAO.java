/* Copyright (C) 2013 Telenoetica, Inc. All rights reserved */
package com.telenoetica.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telenoetica.jpa.entities.AndroidApplicationDownloadHistory;
import com.telenoetica.jpa.entities.User;

/**
 * The Interface CallOutVisitDAO.
 *
 * @author  Shiv Prasad Khillar
 */
public interface AndroidApplicationDownloadHistoryDAO extends JpaRepository<AndroidApplicationDownloadHistory, Long> {
  public AndroidApplicationDownloadHistory findByUser(User user);
}
