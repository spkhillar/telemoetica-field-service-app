/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telenoetica.jpa.entities.UserRole;

/**
 * The Interface UserRoleDAO.
 *
 * @author  Shiv Prasad Khillar
 */
public interface UserRoleDAO extends JpaRepository<UserRole, Long> {

}
