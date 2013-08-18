/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telenoetica.jpa.entities.Role;

/**
 * The Interface RoleDAO.
 *
 * @author  Shiv Prasad Khillar
 */
public interface RoleDAO extends JpaRepository<Role, Long> {

}
