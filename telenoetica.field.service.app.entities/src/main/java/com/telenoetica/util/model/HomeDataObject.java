/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved 
 */
package com.telenoetica.util.model;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

import com.telenoetica.jpa.entities.Client;
import com.telenoetica.jpa.entities.DieselVendor;
import com.telenoetica.jpa.entities.Fault;
import com.telenoetica.jpa.entities.MaintenanceVisitCategory;
import com.telenoetica.jpa.entities.Site;
import com.telenoetica.jpa.entities.Spare;

/**
 * The Class HomeDataObject.
 * 
 * @author Shiv Prasad Khillar
 */
@JsonAutoDetect(JsonMethod.NONE)
public class HomeDataObject {

	/** The sites. */
	@JsonProperty
	private List<Site> sites = new ArrayList<Site>(1150);

	/** The spares. */
	@JsonProperty
	private List<Spare> spares = new ArrayList<Spare>(34);

	/** The clients. */
	@JsonProperty
	private List<Client> clients = new ArrayList<Client>(50);

	/** The faults. */
	@JsonProperty
	private List<Fault> faults = new ArrayList<Fault>(10);

	/** The maintenance categories. */
	@JsonProperty
	List<MaintenanceVisitCategory> maintenanceCategories = new ArrayList<MaintenanceVisitCategory>(
			10);

	/** The dieselVendor. */
	@JsonProperty
	private List<DieselVendor> dieselVendor = new ArrayList<DieselVendor>(25);

	/**
	 * Instantiates a new home data object.
	 */
	public HomeDataObject() {
	}

	/**
	 * Instantiates a new home data object.
	 * 
	 * @param sites
	 *            the sites
	 * @param spares
	 *            the spares
	 * @param clients
	 *            the clients
	 * @param faults
	 *            the faults
	 * @param maintenanceCategories
	 *            the maintenance categories
	 */
	public HomeDataObject(final List<Site> sites, final List<Spare> spares,
			final List<Client> clients, final List<Fault> faults,
			final List<MaintenanceVisitCategory> maintenanceCategories,
			final List<DieselVendor> dieselVendor) {
		super();
		this.sites = sites;
		this.spares = spares;
		this.clients = clients;
		this.faults = faults;
		this.maintenanceCategories = maintenanceCategories;
		this.dieselVendor = dieselVendor;
	}

	/**
	 * Gets the sites.
	 * 
	 * @return the sites
	 */
	public List<Site> getSites() {
		return sites;
	}

	/**
	 * Sets the sites.
	 * 
	 * @param sites
	 *            the new sites
	 */
	public void setSites(final List<Site> sites) {
		this.sites = sites;
	}

	/**
	 * Gets the spares.
	 * 
	 * @return the spares
	 */
	public List<Spare> getSpares() {
		return spares;
	}

	/**
	 * Sets the spares.
	 * 
	 * @param spares
	 *            the new spares
	 */
	public void setSpares(final List<Spare> spares) {
		this.spares = spares;
	}

	/**
	 * Gets the clients.
	 * 
	 * @return the clients
	 */
	public List<Client> getClients() {
		return clients;
	}

	/**
	 * Sets the clients.
	 * 
	 * @param clients
	 *            the new clients
	 */
	public void setClients(final List<Client> clients) {
		this.clients = clients;
	}

	/**
	 * Gets the faults.
	 * 
	 * @return the faults
	 */
	public List<Fault> getFaults() {
		return faults;
	}

	/**
	 * Sets the faults.
	 * 
	 * @param faults
	 *            the new faults
	 */
	public void setFaults(final List<Fault> faults) {
		this.faults = faults;
	}

	/**
	 * Gets the maintenance categories.
	 * 
	 * @return the maintenance categories
	 */
	public List<MaintenanceVisitCategory> getMaintenanceCategories() {
		return maintenanceCategories;
	}

	/**
	 * Sets the maintenance categories.
	 * 
	 * @param maintenanceCategories
	 *            the new maintenance categories
	 */
	public void setMaintenanceCategories(
			final List<MaintenanceVisitCategory> maintenanceCategories) {
		this.maintenanceCategories = maintenanceCategories;
	}

	/**
	 * @return DieselVendor List
	 */
	public List<DieselVendor> getDieselVendor() {
		return dieselVendor;
	}

	/**
	 * @param dieselVendor
	 */
	public void setDieselVendor(final List<DieselVendor> dieselVendor) {
		this.dieselVendor = dieselVendor;
	}

}
