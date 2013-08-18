/* Copyright (C) 2013 Telenoetica, Inc. All rights reserved */
package com.telenoetica.jpa.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.telenoetica.jpa.entities.User;

/**
 * The Interface UserDAO.
 * 
 * @author Shiv Prasad Khillar
 */
public interface UserDAO extends JpaRepository<User, Long> {

	/**
	 * Find by user name like.
	 * 
	 * @param userName
	 *            the user name
	 * @param pageable
	 *            the pageable
	 * @return the page
	 */
	Page<User> findByUserNameLike(String userName, Pageable pageable);

	/**
	 * Find by first name like.
	 * 
	 * @param firstName
	 *            the first name
	 * @param pageable
	 *            the pageable
	 * @return the page
	 */
	Page<User> findByFirstNameLike(String firstName, Pageable pageable);

	/**
	 * Find by last name like.
	 * 
	 * @param lastName
	 *            the last name
	 * @param pageable
	 *            the pageable
	 * @return the page
	 */
	Page<User> findByLastNameLike(String lastName, Pageable pageable);

	/**
	 * Find by email like.
	 * 
	 * @param email
	 *            the email
	 * @param pageable
	 *            the pageable
	 * @return the page
	 */
	Page<User> findByEmailLike(String email, Pageable pageable);

	/**
	 * Find by enabled.
	 * 
	 * @param enabled
	 *            the enabled
	 * @param pageable
	 *            the pageable
	 * @return the page
	 */
	Page<User> findByEnabled(Boolean enabled, Pageable pageable);

	/**
	 * Find by role.
	 * 
	 * @param role
	 *            the role
	 * @param pageable
	 *            the pageable
	 * @return the page
	 */
	@Query("select u from User u where u.userRole.role.id = :role")
	Page<User> findByRole(@Param("role") Long role, Pageable pageable);

	/**
	 * Find by user name.
	 * 
	 * @param userName
	 *            the user name
	 * @return the user
	 */
	User findByUserName(String userName);

}
