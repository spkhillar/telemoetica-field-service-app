/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telenoetica.jpa.entities.MaintenanceVisitCategory;

/**
 * The Interface MaintenanceVisitCategoryDAO.
 *
 * @author  Shiv Prasad Khillar
 */
public interface MaintenanceVisitCategoryDAO extends JpaRepository<MaintenanceVisitCategory, Long> {

}
