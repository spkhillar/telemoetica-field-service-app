/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.jpa.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telenoetica.jpa.entities.DieselVisit;
import com.telenoetica.jpa.entities.Site;

/**
 * The Interface DieselVisitDAO.
 * 
 * @author Shiv Prasad Khillar
 */
public interface DieselVisitDAO extends JpaRepository<DieselVisit, Long> {

	/**
	 * @param site
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<DieselVisit> findBySiteAndCreatedAtBetween(Site site, Date startDate,
			Date endDate);

}
