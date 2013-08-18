/* Copyright (C) 2013 Telenoetica, Inc. All rights reserved */
package com.telenoetica.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telenoetica.jpa.entities.Fault;

/**
 * The Interface FaultDAO.
 *
 * @author  Shiv Prasad Khillar
 */
public interface FaultDAO extends JpaRepository<Fault, Long> {

}
