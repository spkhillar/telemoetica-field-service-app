/* Copyright (C) 2013 Telenoetica, Inc. All rights reserved */
package com.telenoetica.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telenoetica.jpa.entities.Client;

/**
 * The Interface ClientDAO.
 *
 * @author  Shiv Prasad Khillar
 */
public interface ClientDAO extends JpaRepository<Client, Long> {

}
