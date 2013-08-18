/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.jpa.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telenoetica.jpa.entities.MaintenanceVisit;
import com.telenoetica.jpa.entities.Site;

/**
 * The Interface MaintenanceVisitDAO.
 * 
 * @author Shiv Prasad Khillar
 */
public interface MaintenanceVisitDAO extends
		JpaRepository<MaintenanceVisit, Long> {

	/**
	 * @param site
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<MaintenanceVisit> findBySiteAndCreatedAtBetween(Site site,
			Date startDate, Date endDate);

}
