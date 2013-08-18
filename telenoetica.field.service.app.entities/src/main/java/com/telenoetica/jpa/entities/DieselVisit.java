/* Copyright (C) 2013 Telenoetica, Inc. All rights reserved */
package com.telenoetica.jpa.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * DieselVisit.
 * 
 * @author Shiv Prasad Khillar
 */
@Entity
@Table(name = "diesel_visit")
@JsonAutoDetect(JsonMethod.NONE)
public class DieselVisit implements java.io.Serializable, BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -68293699494074823L;

	/** The id. */
	@JsonProperty
	private Long id;

	/** The version. */
	private Integer version;

	/** The user. */
	private User user;

	/** The site. */
	private Site site;

	/** The access code. */
	@JsonProperty
	private String accessCode;

	/** The drn number. */
	@JsonProperty
	private String drnNumber;

	/** The diesel transfer or bulk supply. */
	@JsonProperty
	private String dieselTransferOrBulkSupply;

	/** The transfer from which site. */
	@JsonProperty
	private Site transferredSite;

	/** The bulk name of vendor. */
	@JsonProperty
	private String bulkNameOfVendor;

	/** The diesel level t1 before visit. */
	@JsonProperty
	private Long dieselLevelT1BeforeVisit;

	/** The diesel level t2 before visit. */
	@JsonProperty
	private Long dieselLevelT2BeforeVisit;

	/** The diesel received ltrs. */
	@JsonProperty
	private Long dieselReceivedLtrs;

	/** The run hour gen1. */
	@JsonProperty
	private Long runHourGen1;

	/** The run hour gen2. */
	@JsonProperty
	private Long runHourGen2;

	/** The drn book at site. */
	@JsonProperty
	private Boolean drnBookAtSite = Boolean.FALSE;

	/** The diesel log book maintained. */
	@JsonProperty
	private Boolean dieselLogBookMaintained = Boolean.FALSE;

	/** The phcn installed. */
	@JsonProperty
	private Boolean phcnInstalled = Boolean.FALSE;

	/** The phcn hrs per day. */
	@JsonProperty
	private Long phcnHrsPerDay;

	/** The hybrid or piu installed. */
	@JsonProperty
	private Boolean hybridOrPiuInstalled = Boolean.FALSE;

	/** The hybrid or piu hrs per day. */
	@JsonProperty
	private Long hybridOrPiuHrsPerDay;

	/** The created at. */
	@JsonProperty
	private Date createdAt = new Date();

	/** The user id. */
	@JsonProperty
	private String userId;

	/** The site id. */
	@JsonProperty
	private String siteId;

	/** The transferred site id. */
	@JsonProperty
	private String transferredSiteId;

	/**
	 * the dieselDensity
	 */
	@JsonProperty
	private Float dieselDensity;

	/**
	 * Instantiates a new diesel visit.
	 */
	public DieselVisit() {
	}

	/**
	 * Instantiates a new diesel visit.
	 * 
	 * @param createdAt
	 *            the created at
	 */
	public DieselVisit(final Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Instantiates a new diesel visit.
	 * 
	 * @param user
	 *            the user
	 * @param site
	 *            the site
	 * @param accessCode
	 *            the access code
	 * @param drnNumber
	 *            the drn number
	 * @param dieselTransferOrBulkSupply
	 *            the diesel transfer or bulk supply
	 * @param transferredSite
	 *            the transferred site
	 * @param bulkNameOfVendor
	 *            the bulk name of vendor
	 * @param dieselLevelT1BeforeVisit
	 *            the diesel level t1 before visit
	 * @param dieselLevelT2BeforeVisit
	 *            the diesel level t2 before visit
	 * @param dieselReceivedLtrs
	 *            the diesel received ltrs
	 * @param runHourGen1
	 *            the run hour gen1
	 * @param runHourGen2
	 *            the run hour gen2
	 * @param drnBookAtSite
	 *            the drn book at site
	 * @param dieselLogBookMaintained
	 *            the diesel log book maintained
	 * @param createdAt
	 *            the created at
	 */
	public DieselVisit(final User user, final Site site,
			final String accessCode, final String drnNumber,
			final String dieselTransferOrBulkSupply,
			final Site transferredSite, final String bulkNameOfVendor,
			final Long dieselLevelT1BeforeVisit,
			final Long dieselLevelT2BeforeVisit, final Long dieselReceivedLtrs,
			final Long runHourGen1, final Long runHourGen2,
			final Boolean drnBookAtSite, final Boolean dieselLogBookMaintained,
			final Date createdAt) {
		this.user = user;
		this.site = site;
		this.accessCode = accessCode;
		this.drnNumber = drnNumber;
		this.dieselTransferOrBulkSupply = dieselTransferOrBulkSupply;
		this.transferredSite = transferredSite;
		this.bulkNameOfVendor = bulkNameOfVendor;
		this.dieselLevelT1BeforeVisit = dieselLevelT1BeforeVisit;
		this.dieselLevelT2BeforeVisit = dieselLevelT2BeforeVisit;
		this.dieselReceivedLtrs = dieselReceivedLtrs;
		this.runHourGen1 = runHourGen1;
		this.runHourGen2 = runHourGen2;
		this.drnBookAtSite = drnBookAtSite;
		this.dieselLogBookMaintained = dieselLogBookMaintained;
		this.createdAt = createdAt;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Gets the version.
	 * 
	 * @return the version
	 */
	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 * 
	 * @param version
	 *            the new version
	 */
	public void setVersion(final Integer version) {
		this.version = version;
	}

	/**
	 * Gets the user.
	 * 
	 * @return the user
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 * 
	 * @param user
	 *            the new user
	 */
	public void setUser(final User user) {
		this.user = user;
		if (this.user != null) {
			userId = this.user.getUserName();
		}
	}

	/**
	 * Gets the site.
	 * 
	 * @return the site
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "site_id")
	public Site getSite() {
		return site;
	}

	/**
	 * Sets the site.
	 * 
	 * @param site
	 *            the new site
	 */
	public void setSite(final Site site) {
		this.site = site;
		if (getSite() != null) {
			siteId = getSite().getName();
		}
	}

	/**
	 * Gets the access code.
	 * 
	 * @return the access code
	 */
	@Column(name = "access_code", length = 250)
	public String getAccessCode() {
		return accessCode;
	}

	/**
	 * Sets the access code.
	 * 
	 * @param accessCode
	 *            the new access code
	 */
	public void setAccessCode(final String accessCode) {
		this.accessCode = accessCode;
	}

	/**
	 * Gets the drn number.
	 * 
	 * @return the drn number
	 */
	@Column(name = "drn_number", length = 250)
	public String getDrnNumber() {
		return drnNumber;
	}

	/**
	 * Sets the drn number.
	 * 
	 * @param drnNumber
	 *            the new drn number
	 */
	public void setDrnNumber(final String drnNumber) {
		this.drnNumber = drnNumber;
	}

	/**
	 * Gets the diesel transfer or bulk supply.
	 * 
	 * @return the diesel transfer or bulk supply
	 */
	@Column(name = "diesel_transfer_or_bulk_supply", length = 250)
	public String getDieselTransferOrBulkSupply() {
		return dieselTransferOrBulkSupply;
	}

	/**
	 * Sets the diesel transfer or bulk supply.
	 * 
	 * @param dieselTransferOrBulkSupply
	 *            the new diesel transfer or bulk supply
	 */
	public void setDieselTransferOrBulkSupply(
			final String dieselTransferOrBulkSupply) {
		this.dieselTransferOrBulkSupply = dieselTransferOrBulkSupply;
	}

	/**
	 * Gets the transferred site.
	 * 
	 * @return the transferred site
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transfer_from_which_site")
	public Site getTransferredSite() {
		return transferredSite;
	}

	/**
	 * Sets the transferred site.
	 * 
	 * @param transferredSite
	 *            the new transferred site
	 */
	public void setTransferredSite(final Site transferredSite) {
		this.transferredSite = transferredSite;
		if (this.transferredSite != null) {
			transferredSiteId = getTransferredSite().getName();
		}
	}

	/**
	 * Gets the bulk name of vendor.
	 * 
	 * @return the bulk name of vendor
	 */
	@Column(name = "bulk_name_of_vendor", length = 250)
	public String getBulkNameOfVendor() {
		return bulkNameOfVendor;
	}

	/**
	 * Sets the bulk name of vendor.
	 * 
	 * @param bulkNameOfVendor
	 *            the new bulk name of vendor
	 */
	public void setBulkNameOfVendor(final String bulkNameOfVendor) {
		this.bulkNameOfVendor = bulkNameOfVendor;
	}

	/**
	 * Gets the diesel level t1 before visit.
	 * 
	 * @return the diesel level t1 before visit
	 */
	@Column(name = "diesel_level_t1_before_visit")
	public Long getDieselLevelT1BeforeVisit() {
		return dieselLevelT1BeforeVisit;
	}

	/**
	 * Sets the diesel level t1 before visit.
	 * 
	 * @param dieselLevelT1BeforeVisit
	 *            the new diesel level t1 before visit
	 */
	public void setDieselLevelT1BeforeVisit(final Long dieselLevelT1BeforeVisit) {
		this.dieselLevelT1BeforeVisit = dieselLevelT1BeforeVisit;
	}

	/**
	 * Gets the diesel level t2 before visit.
	 * 
	 * @return the diesel level t2 before visit
	 */
	@Column(name = "diesel_level_t2_before_visit")
	public Long getDieselLevelT2BeforeVisit() {
		return dieselLevelT2BeforeVisit;
	}

	/**
	 * Sets the diesel level t2 before visit.
	 * 
	 * @param dieselLevelT2BeforeVisit
	 *            the new diesel level t2 before visit
	 */
	public void setDieselLevelT2BeforeVisit(final Long dieselLevelT2BeforeVisit) {
		this.dieselLevelT2BeforeVisit = dieselLevelT2BeforeVisit;
	}

	/**
	 * Gets the diesel received ltrs.
	 * 
	 * @return the diesel received ltrs
	 */
	@Column(name = "diesel_received_ltrs")
	public Long getDieselReceivedLtrs() {
		return dieselReceivedLtrs;
	}

	/**
	 * Sets the diesel received ltrs.
	 * 
	 * @param dieselReceivedLtrs
	 *            the new diesel received ltrs
	 */
	public void setDieselReceivedLtrs(final Long dieselReceivedLtrs) {
		this.dieselReceivedLtrs = dieselReceivedLtrs;
	}

	/**
	 * Gets the run hour gen1.
	 * 
	 * @return the run hour gen1
	 */
	@Column(name = "run_hour_gen1")
	public Long getRunHourGen1() {
		return runHourGen1;
	}

	/**
	 * Sets the run hour gen1.
	 * 
	 * @param runHourGen1
	 *            the new run hour gen1
	 */
	public void setRunHourGen1(final Long runHourGen1) {
		this.runHourGen1 = runHourGen1;
	}

	/**
	 * Gets the run hour gen2.
	 * 
	 * @return the run hour gen2
	 */
	@Column(name = "run_hour_gen2")
	public Long getRunHourGen2() {
		return runHourGen2;
	}

	/**
	 * Sets the run hour gen2.
	 * 
	 * @param runHourGen2
	 *            the new run hour gen2
	 */
	public void setRunHourGen2(final Long runHourGen2) {
		this.runHourGen2 = runHourGen2;
	}

	/**
	 * Gets the drn book at site.
	 * 
	 * @return the drn book at site
	 */
	@Column(name = "drn_book_at_site")
	public Boolean getDrnBookAtSite() {
		return drnBookAtSite;
	}

	/**
	 * Sets the drn book at site.
	 * 
	 * @param drnBookAtSite
	 *            the new drn book at site
	 */
	public void setDrnBookAtSite(final Boolean drnBookAtSite) {
		this.drnBookAtSite = drnBookAtSite;
	}

	/**
	 * Gets the diesel log book maintained.
	 * 
	 * @return the diesel log book maintained
	 */
	@Column(name = "diesel_log_book_maintained")
	public Boolean getDieselLogBookMaintained() {
		return dieselLogBookMaintained;
	}

	/**
	 * Sets the diesel log book maintained.
	 * 
	 * @param dieselLogBookMaintained
	 *            the new diesel log book maintained
	 */
	public void setDieselLogBookMaintained(final Boolean dieselLogBookMaintained) {
		this.dieselLogBookMaintained = dieselLogBookMaintained;
	}

	/**
	 * Gets the phcn installed.
	 * 
	 * @return the phcn installed
	 */
	@Column(name = "phcn_installed")
	public Boolean getPhcnInstalled() {
		return phcnInstalled;
	}

	/**
	 * Sets the phcn installed.
	 * 
	 * @param phcnInstalled
	 *            the new phcn installed
	 */
	public void setPhcnInstalled(final Boolean phcnInstalled) {
		this.phcnInstalled = phcnInstalled;
	}

	/**
	 * Gets the phcn hrs per day.
	 * 
	 * @return the phcn hrs per day
	 */
	@Column(name = "phcn_hr_per_day")
	public Long getPhcnHrsPerDay() {
		return phcnHrsPerDay;
	}

	/**
	 * Sets the phcn hrs per day.
	 * 
	 * @param phcnHrsPerDay
	 *            the new phcn hrs per day
	 */
	public void setPhcnHrsPerDay(final Long phcnHrsPerDay) {
		this.phcnHrsPerDay = phcnHrsPerDay;
	}

	/**
	 * Gets the hybrid or piu installed.
	 * 
	 * @return the hybrid or piu installed
	 */
	@Column(name = "hybrid_or_piu_installed")
	public Boolean getHybridOrPiuInstalled() {
		return hybridOrPiuInstalled;
	}

	/**
	 * Sets the hybrid or piu installed.
	 * 
	 * @param hybridOrPiuInstalled
	 *            the new hybrid or piu installed
	 */
	public void setHybridOrPiuInstalled(final Boolean hybridOrPiuInstalled) {
		this.hybridOrPiuInstalled = hybridOrPiuInstalled;
	}

	/**
	 * Gets the hybrid or piu hrs per day.
	 * 
	 * @return the hybrid or piu hrs per day
	 */
	@Column(name = "hybrid_or_piu_hr_per_day")
	public Long getHybridOrPiuHrsPerDay() {
		return hybridOrPiuHrsPerDay;
	}

	/**
	 * Sets the hybrid or piu hrs per day.
	 * 
	 * @param hybridOrPiuHrsPerDay
	 *            the new hybrid or piu hrs per day
	 */
	public void setHybridOrPiuHrsPerDay(final Long hybridOrPiuHrsPerDay) {
		this.hybridOrPiuHrsPerDay = hybridOrPiuHrsPerDay;
	}

	/**
	 * Gets the created at.
	 * 
	 * @return the created at
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, length = 19)
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets the created at.
	 * 
	 * @param createdAt
	 *            the new created at
	 */
	public void setCreatedAt(final Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	@Transient
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 * 
	 * @param userId
	 *            the new user id
	 */
	public void setUserId(final String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the site id.
	 * 
	 * @return the site id
	 */
	@Transient
	public String getSiteId() {
		return siteId;
	}

	/**
	 * Sets the site id.
	 * 
	 * @param siteId
	 *            the new site id
	 */
	public void setSiteId(final String siteId) {
		this.siteId = siteId;
	}

	/**
	 * Gets the transferred site id.
	 * 
	 * @return the transferred site id
	 */
	@Transient
	public String getTransferredSiteId() {
		return transferredSiteId;
	}

	/**
	 * Sets the transferred site id.
	 * 
	 * @param transferredSiteId
	 *            the new transferred site id
	 */
	public void setTransferredSiteId(final String transferredSiteId) {
		this.transferredSiteId = transferredSiteId;
	}

	/**
	 * Gets the dieselDensity.
	 * 
	 * @return the dieselDensity
	 */
	@Column(name = "diesel_density", nullable = true)
	public Float getDieselDensity() {
		return dieselDensity;
	}

	/**
	 * Sets the Diesel Density.
	 * 
	 * @param dieselDensity
	 *            the new dieselDensity
	 */
	public void setDieselDensity(final Float dieselDensity) {
		this.dieselDensity = dieselDensity;
	}

	/**
	 * Hash code.
	 * 
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result)
				+ ((accessCode == null) ? 0 : accessCode.hashCode());
		result = (prime * result)
				+ ((createdAt == null) ? 0 : createdAt.hashCode());
		result = (prime * result) + ((siteId == null) ? 0 : siteId.hashCode());
		result = (prime * result) + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	/**
	 * To string.
	 * 
	 * @return the string
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DieselVisit [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (version != null) {
			builder.append("version=");
			builder.append(version);
			builder.append(", ");
		}
		if (accessCode != null) {
			builder.append("accessCode=");
			builder.append(accessCode);
			builder.append(", ");
		}
		if (drnNumber != null) {
			builder.append("drnNumber=");
			builder.append(drnNumber);
			builder.append(", ");
		}
		if (dieselTransferOrBulkSupply != null) {
			builder.append("dieselTransferOrBulkSupply=");
			builder.append(dieselTransferOrBulkSupply);
			builder.append(", ");
		}
		if (transferredSite != null) {
			builder.append("transferredSite=");
			builder.append(transferredSite);
			builder.append(", ");
		}
		if (bulkNameOfVendor != null) {
			builder.append("bulkNameOfVendor=");
			builder.append(bulkNameOfVendor);
			builder.append(", ");
		}
		if (dieselLevelT1BeforeVisit != null) {
			builder.append("dieselLevelT1BeforeVisit=");
			builder.append(dieselLevelT1BeforeVisit);
			builder.append(", ");
		}
		if (dieselLevelT2BeforeVisit != null) {
			builder.append("dieselLevelT2BeforeVisit=");
			builder.append(dieselLevelT2BeforeVisit);
			builder.append(", ");
		}
		if (dieselReceivedLtrs != null) {
			builder.append("dieselReceivedLtrs=");
			builder.append(dieselReceivedLtrs);
			builder.append(", ");
		}
		if (runHourGen1 != null) {
			builder.append("runHourGen1=");
			builder.append(runHourGen1);
			builder.append(", ");
		}
		if (runHourGen2 != null) {
			builder.append("runHourGen2=");
			builder.append(runHourGen2);
			builder.append(", ");
		}
		if (drnBookAtSite != null) {
			builder.append("drnBookAtSite=");
			builder.append(drnBookAtSite);
			builder.append(", ");
		}
		if (dieselLogBookMaintained != null) {
			builder.append("dieselLogBookMaintained=");
			builder.append(dieselLogBookMaintained);
			builder.append(", ");
		}
		if (phcnInstalled != null) {
			builder.append("phcnInstalled=");
			builder.append(phcnInstalled);
			builder.append(", ");
		}
		if (phcnHrsPerDay != null) {
			builder.append("phcnHrsPerDay=");
			builder.append(phcnHrsPerDay);
			builder.append(", ");
		}
		if (hybridOrPiuInstalled != null) {
			builder.append("hybridOrPiuInstalled=");
			builder.append(hybridOrPiuInstalled);
			builder.append(", ");
		}
		if (hybridOrPiuHrsPerDay != null) {
			builder.append("hybridOrPiuHrsPerDay=");
			builder.append(hybridOrPiuHrsPerDay);
			builder.append(", ");
		}
		if (createdAt != null) {
			builder.append("createdAt=");
			builder.append(createdAt);
			builder.append(", ");
		}
		if (userId != null) {
			builder.append("userId=");
			builder.append(userId);
			builder.append(", ");
		}
		if (siteId != null) {
			builder.append("siteId=");
			builder.append(siteId);
			builder.append(", ");
		}
		if (transferredSiteId != null) {
			builder.append("transferredSiteId=");
			builder.append(transferredSiteId);
		}
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Equals.
	 * 
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DieselVisit other = (DieselVisit) obj;
		if (accessCode == null) {
			if (other.accessCode != null) {
				return false;
			}
		} else if (!accessCode.equals(other.accessCode)) {
			return false;
		}
		if (createdAt == null) {
			if (other.createdAt != null) {
				return false;
			}
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (siteId == null) {
			if (other.siteId != null) {
				return false;
			}
		} else if (!siteId.equals(other.siteId)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		return true;
	}

}
