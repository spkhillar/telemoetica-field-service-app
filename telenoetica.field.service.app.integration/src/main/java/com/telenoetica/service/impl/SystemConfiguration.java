/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * The Class SystemConfiguration. Place holder to fetch properties defined in
 * system-config.properties.
 */
@Service("systemConfig")
public class SystemConfiguration {

	/** The diesel details report directory. */
	@Value("${diesel.details.report.directory}")
	private String dieselDetailsReportDirectory;

	/** The spares utilization report directory. */
	@Value("${spares.utilization.report.directory}")
	private String sparesUtilizationReportDirectory;

	/** The diesel details report file name. */
	@Value("${diesel.details.report.file.name}")
	private String dieselDetailsReportFileName;

	/** The spares utilization report file name. */
	@Value("${spares.utilization.report.file.name}")
	private String sparesUtilizationReportFileName;

	/** The android app file path. */
	@Value("${android.apk.source.file.name}")
	private String androidAppFilePath;

	/** The mail service host. */
	@Value("${mail.host}")
	private String host;

	/** The mail service host. */
	@Value("${mail.port}")
	private String port;

	/** The mail service username. */
	@Value("${mail.username}")
	private String username;

	/** The mail service password. */
	@Value("${mail.password}")
	private String password;

	/** The mail service host. */
	@Value("${mail.transport.protocol}")
	private String transport_protocol;

	/** The mail service auth. */
	@Value("${mail.smtp.auth}")
	private String auth;

	/** The mail service smtp.starttls.enable. */
	@Value("${mail.smtp.starttls.enable}")
	private String starttls_enable;

	/** The mail service debug. */
	@Value("${mail.debug}")
	private String debug;

	/** The mail service to. */
	@Value("${mail.to}")
	private String to;

	/** The android app file path. */
	@Value("${android.help.file.name}")
	private String androidAppHelpFilePath;

	/**
	 * Gets the diesel details report directory.
	 * 
	 * @return the diesel details report directory
	 */
	public String getDieselDetailsReportDirectory() {
		return dieselDetailsReportDirectory;
	}

	@Value("${fieldapp.report.diselReport.template.path}")
	private String dieselDetailsReportTemplate;

	@Value("${fieldapp.report.spareUtilization.template.path}")
	private String spareUtilizationReportTemplate;

	public String getSpareUtilizationReportTemplate() {
		return spareUtilizationReportTemplate;
	}

	public String getDieselDetailsReportTemplate() {
		return dieselDetailsReportTemplate;
	}

	/**
	 * Gets the spares utilization report directory.
	 * 
	 * @return the spares utilization report directory
	 */
	public String getSparesUtilizationReportDirectory() {
		return sparesUtilizationReportDirectory;
	}

	/**
	 * Gets the diesel details report file name.
	 * 
	 * @return the diesel details report file name
	 */
	public String getDieselDetailsReportFileName() {
		return dieselDetailsReportFileName;
	}

	/**
	 * Gets the spares utilization report file name.
	 * 
	 * @return the spares utilization report file name
	 */
	public String getSparesUtilizationReportFileName() {
		return sparesUtilizationReportFileName;
	}

	/**
	 * Gets the android app file path.
	 * 
	 * @return the android app file path
	 */
	public String getAndroidAppFilePath() {
		return androidAppFilePath;
	}

	/**
	 * Gets the android app file path.
	 * 
	 * @return the android app file path
	 */
	public String getAndroidAppHelpFilePath() {
		return androidAppHelpFilePath;
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getTransport_protocol() {
		return transport_protocol;
	}

	public String getAuth() {
		return auth;
	}

	public String getStarttls_enable() {
		return starttls_enable;
	}

	public String getDebug() {
		return debug;
	}

	public String getTo() {
		return to;
	}

}
