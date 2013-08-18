/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.entities.User;
import com.telenoetica.service.EmailService;
import com.telenoetica.service.SiteService;
import com.telenoetica.service.UserService;
import com.telenoetica.service.mail.EmailTemplate;
import com.telenoetica.service.util.ApplicationServiceException;

/**
 * The Class AbstractBaseService.
 */
public abstract class AbstractBaseService {

	protected static final Logger logger = Logger
			.getLogger(AbstractBaseService.class);

	/** The user service. */
	@Autowired
	protected UserService userService;

	/** The site service. */
	@Autowired
	protected SiteService siteService;

	@Autowired
	protected EmailService emailService;

	/** The system configuration. */
	@Autowired
	protected SystemConfiguration systemConfiguration;

	/**
	 * Gets the user.
	 * 
	 * @param username
	 *            the username
	 * @return the user
	 */
	public User getUser(final String username) {
		User user = userService.findByUserName(username);
		if (user == null) {
			throw new ApplicationServiceException("User " + username
					+ "not found in system");
		}
		return user;
	}

	/**
	 * Gets the site.
	 * 
	 * @param siteName
	 *            the site name
	 * @return the site
	 */
	public Site getSite(final String siteName) {
		Site site = siteService.findSite(siteName);
		if (site == null) {
			throw new ApplicationServiceException("Site \"" + siteName
					+ "\" not found in system");
		}
		return site;
	}

	public void sendEmail(final String reportFilePath) {
		logger.debug("Sending Diesel detail report in email");
		String recipient = systemConfiguration.getTo();
		File attachment = new File(reportFilePath);
		List<String> toAddress = new ArrayList(Arrays.asList(recipient
				.split(",")));
		String subject = "";
		if (reportFilePath.contains("diesel")) {
			subject = "Diesel Detail Report ";
		} else {
			subject = "Spare Utilization Report ";
		}
		// toAddress.add(recipient);
		EmailTemplate emailTemplate = new EmailTemplate(toAddress,
				"***** System-Generated Message...Please DO NOT Reply *****",
				subject);
		emailTemplate.setAttachmentFileName(attachment.getAbsolutePath());
		try {
			emailService.sendEmail(emailTemplate);
		} catch (Exception e) {
			logger.error("mail sending failed..", e);
		}
	}

}
