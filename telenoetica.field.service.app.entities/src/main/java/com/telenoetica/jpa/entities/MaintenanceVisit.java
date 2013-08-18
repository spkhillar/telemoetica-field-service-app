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

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * MaintenanceVisit.
 * 
 * @author Shiv Prasad Khillar
 */
@Entity
@Table(name = "maintenance_visit")
public class MaintenanceVisit implements java.io.Serializable, BaseEntity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6792436011860802620L;

	/** The id. */
	private Long id;

	/** The version. */
	private Integer version;

	/** The user. */
	private User user;

	/** The site. */
	private Site site;

	/** The access code. */
	private String accessCode;

	/** The category of maintenance. */
	private String categoryOfMaintenance;

	/** The spares used items replaced1. */
	private String sparesUsedItemsReplaced1;

	/** The spares used items replaced2. */
	private String sparesUsedItemsReplaced2;

	/** The spares used items replaced3. */
	private String sparesUsedItemsReplaced3;

	/** The spares used items replaced4. */
	private String sparesUsedItemsReplaced4;

	/** The spares used items replaced5. */
	private String sparesUsedItemsReplaced5;

	/** The spares used items replaced6. */
	private String sparesUsedItemsReplaced6;

	/** The cosumables used1. */
	private String cosumablesUsed1;

	/** The cosumables used2. */
	private String cosumablesUsed2;

	/** The cosumables used3. */
	private String cosumablesUsed3;

	/** The cosumables used4. */
	private String cosumablesUsed4;

	/** The cosumables used5. */
	private String cosumablesUsed5;

	/** The cosumables used6. */
	private String cosumablesUsed6;

	/** The run hours after pmd g1. */
	private Long runHoursAfterPmdG1;

	/** The run hour after pmd g2. */
	private Long runHourAfterPmdG2;

	/** The created at. */
	private Date createdAt = new Date();

	/** The user id. */
	@JsonProperty
	private String userId;

	/** The site id. */
	@JsonProperty
	private String siteId;

	/**
	 * Instantiates a new maintenance visit.
	 */
	public MaintenanceVisit() {
	}

	/**
	 * Instantiates a new maintenance visit.
	 * 
	 * @param createdAt
	 *            the created at
	 */
	public MaintenanceVisit(final Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Instantiates a new maintenance visit.
	 * 
	 * @param user
	 *            the user
	 * @param site
	 *            the site
	 * @param accessCode
	 *            the access code
	 * @param categoryOfMaintenance
	 *            the category of maintenance
	 * @param sparesUsedItemsReplaced1
	 *            the spares used items replaced1
	 * @param sparesUsedItemsReplaced2
	 *            the spares used items replaced2
	 * @param sparesUsedItemsReplaced3
	 *            the spares used items replaced3
	 * @param sparesUsedItemsReplaced4
	 *            the spares used items replaced4
	 * @param sparesUsedItemsReplaced5
	 *            the spares used items replaced5
	 * @param sparesUsedItemsReplaced6
	 *            the spares used items replaced6
	 * @param cosumablesUsed1
	 *            the cosumables used1
	 * @param cosumablesUsed2
	 *            the cosumables used2
	 * @param cosumablesUsed3
	 *            the cosumables used3
	 * @param cosumablesUsed4
	 *            the cosumables used4
	 * @param cosumablesUsed5
	 *            the cosumables used5
	 * @param cosumablesUsed6
	 *            the cosumables used6
	 * @param runHoursAfterPmdG1
	 *            the run hours after pmd g1
	 * @param runHourAfterPmdG2
	 *            the run hour after pmd g2
	 * @param createdAt
	 *            the created at
	 */
	public MaintenanceVisit(final User user, final Site site,
			final String accessCode, final String categoryOfMaintenance,
			final String sparesUsedItemsReplaced1,
			final String sparesUsedItemsReplaced2,
			final String sparesUsedItemsReplaced3,
			final String sparesUsedItemsReplaced4,
			final String sparesUsedItemsReplaced5,
			final String sparesUsedItemsReplaced6,
			final String cosumablesUsed1, final String cosumablesUsed2,
			final String cosumablesUsed3, final String cosumablesUsed4,
			final String cosumablesUsed5, final String cosumablesUsed6,
			final Long runHoursAfterPmdG1, final Long runHourAfterPmdG2,
			final Date createdAt) {
		this.user = user;
		this.site = site;
		this.accessCode = accessCode;
		this.categoryOfMaintenance = categoryOfMaintenance;
		this.sparesUsedItemsReplaced1 = sparesUsedItemsReplaced1;
		this.sparesUsedItemsReplaced2 = sparesUsedItemsReplaced2;
		this.sparesUsedItemsReplaced3 = sparesUsedItemsReplaced3;
		this.sparesUsedItemsReplaced4 = sparesUsedItemsReplaced4;
		this.sparesUsedItemsReplaced5 = sparesUsedItemsReplaced5;
		this.sparesUsedItemsReplaced6 = sparesUsedItemsReplaced6;
		this.cosumablesUsed1 = cosumablesUsed1;
		this.cosumablesUsed2 = cosumablesUsed2;
		this.cosumablesUsed3 = cosumablesUsed3;
		this.cosumablesUsed4 = cosumablesUsed4;
		this.cosumablesUsed5 = cosumablesUsed5;
		this.cosumablesUsed6 = cosumablesUsed6;
		this.runHoursAfterPmdG1 = runHoursAfterPmdG1;
		this.runHourAfterPmdG2 = runHourAfterPmdG2;
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
	 * Gets the category of maintenance.
	 * 
	 * @return the category of maintenance
	 */
	@Column(name = "category_of_maintenance", length = 250)
	public String getCategoryOfMaintenance() {
		return categoryOfMaintenance;
	}

	/**
	 * Sets the category of maintenance.
	 * 
	 * @param categoryOfMaintenance
	 *            the new category of maintenance
	 */
	public void setCategoryOfMaintenance(final String categoryOfMaintenance) {
		this.categoryOfMaintenance = categoryOfMaintenance;
	}

	/**
	 * Gets the spares used items replaced1.
	 * 
	 * @return the spares used items replaced1
	 */
	@Column(name = "spares_used_items_replaced1", length = 250)
	public String getSparesUsedItemsReplaced1() {
		return sparesUsedItemsReplaced1;
	}

	/**
	 * Sets the spares used items replaced1.
	 * 
	 * @param sparesUsedItemsReplaced1
	 *            the new spares used items replaced1
	 */
	public void setSparesUsedItemsReplaced1(
			final String sparesUsedItemsReplaced1) {
		this.sparesUsedItemsReplaced1 = sparesUsedItemsReplaced1;
	}

	/**
	 * Gets the spares used items replaced2.
	 * 
	 * @return the spares used items replaced2
	 */
	@Column(name = "spares_used_items_replaced2", length = 250)
	public String getSparesUsedItemsReplaced2() {
		return sparesUsedItemsReplaced2;
	}

	/**
	 * Sets the spares used items replaced2.
	 * 
	 * @param sparesUsedItemsReplaced2
	 *            the new spares used items replaced2
	 */
	public void setSparesUsedItemsReplaced2(
			final String sparesUsedItemsReplaced2) {
		this.sparesUsedItemsReplaced2 = sparesUsedItemsReplaced2;
	}

	/**
	 * Gets the spares used items replaced3.
	 * 
	 * @return the spares used items replaced3
	 */
	@Column(name = "spares_used_items_replaced3", length = 250)
	public String getSparesUsedItemsReplaced3() {
		return sparesUsedItemsReplaced3;
	}

	/**
	 * Sets the spares used items replaced3.
	 * 
	 * @param sparesUsedItemsReplaced3
	 *            the new spares used items replaced3
	 */
	public void setSparesUsedItemsReplaced3(
			final String sparesUsedItemsReplaced3) {
		this.sparesUsedItemsReplaced3 = sparesUsedItemsReplaced3;
	}

	/**
	 * Gets the spares used items replaced4.
	 * 
	 * @return the spares used items replaced4
	 */
	@Column(name = "spares_used_items_replaced4", length = 250)
	public String getSparesUsedItemsReplaced4() {
		return sparesUsedItemsReplaced4;
	}

	/**
	 * Sets the spares used items replaced4.
	 * 
	 * @param sparesUsedItemsReplaced4
	 *            the new spares used items replaced4
	 */
	public void setSparesUsedItemsReplaced4(
			final String sparesUsedItemsReplaced4) {
		this.sparesUsedItemsReplaced4 = sparesUsedItemsReplaced4;
	}

	/**
	 * Gets the spares used items replaced5.
	 * 
	 * @return the spares used items replaced5
	 */
	@Column(name = "spares_used_items_replaced5", length = 250)
	public String getSparesUsedItemsReplaced5() {
		return sparesUsedItemsReplaced5;
	}

	/**
	 * Sets the spares used items replaced5.
	 * 
	 * @param sparesUsedItemsReplaced5
	 *            the new spares used items replaced5
	 */
	public void setSparesUsedItemsReplaced5(
			final String sparesUsedItemsReplaced5) {
		this.sparesUsedItemsReplaced5 = sparesUsedItemsReplaced5;
	}

	/**
	 * Gets the spares used items replaced6.
	 * 
	 * @return the spares used items replaced6
	 */
	@Column(name = "spares_used_items_replaced6", length = 250)
	public String getSparesUsedItemsReplaced6() {
		return sparesUsedItemsReplaced6;
	}

	/**
	 * Sets the spares used items replaced6.
	 * 
	 * @param sparesUsedItemsReplaced6
	 *            the new spares used items replaced6
	 */
	public void setSparesUsedItemsReplaced6(
			final String sparesUsedItemsReplaced6) {
		this.sparesUsedItemsReplaced6 = sparesUsedItemsReplaced6;
	}

	/**
	 * Gets the cosumables used1.
	 * 
	 * @return the cosumables used1
	 */
	@Column(name = "cosumables_used1", length = 250)
	public String getCosumablesUsed1() {
		return cosumablesUsed1;
	}

	/**
	 * Sets the cosumables used1.
	 * 
	 * @param cosumablesUsed1
	 *            the new cosumables used1
	 */
	public void setCosumablesUsed1(final String cosumablesUsed1) {
		this.cosumablesUsed1 = cosumablesUsed1;
	}

	/**
	 * Gets the cosumables used2.
	 * 
	 * @return the cosumables used2
	 */
	@Column(name = "cosumables_used2", length = 250)
	public String getCosumablesUsed2() {
		return cosumablesUsed2;
	}

	/**
	 * Sets the cosumables used2.
	 * 
	 * @param cosumablesUsed2
	 *            the new cosumables used2
	 */
	public void setCosumablesUsed2(final String cosumablesUsed2) {
		this.cosumablesUsed2 = cosumablesUsed2;
	}

	/**
	 * Gets the cosumables used3.
	 * 
	 * @return the cosumables used3
	 */
	@Column(name = "cosumables_used3", length = 250)
	public String getCosumablesUsed3() {
		return cosumablesUsed3;
	}

	/**
	 * Sets the cosumables used3.
	 * 
	 * @param cosumablesUsed3
	 *            the new cosumables used3
	 */
	public void setCosumablesUsed3(final String cosumablesUsed3) {
		this.cosumablesUsed3 = cosumablesUsed3;
	}

	/**
	 * Gets the cosumables used4.
	 * 
	 * @return the cosumables used4
	 */
	@Column(name = "cosumables_used4", length = 250)
	public String getCosumablesUsed4() {
		return cosumablesUsed4;
	}

	/**
	 * Sets the cosumables used4.
	 * 
	 * @param cosumablesUsed4
	 *            the new cosumables used4
	 */
	public void setCosumablesUsed4(final String cosumablesUsed4) {
		this.cosumablesUsed4 = cosumablesUsed4;
	}

	/**
	 * Gets the cosumables used5.
	 * 
	 * @return the cosumables used5
	 */
	@Column(name = "cosumables_used5", length = 250)
	public String getCosumablesUsed5() {
		return cosumablesUsed5;
	}

	/**
	 * Sets the cosumables used5.
	 * 
	 * @param cosumablesUsed5
	 *            the new cosumables used5
	 */
	public void setCosumablesUsed5(final String cosumablesUsed5) {
		this.cosumablesUsed5 = cosumablesUsed5;
	}

	/**
	 * Gets the cosumables used6.
	 * 
	 * @return the cosumables used6
	 */
	@Column(name = "cosumables_used6", length = 250)
	public String getCosumablesUsed6() {
		return cosumablesUsed6;
	}

	/**
	 * Sets the cosumables used6.
	 * 
	 * @param cosumablesUsed6
	 *            the new cosumables used6
	 */
	public void setCosumablesUsed6(final String cosumablesUsed6) {
		this.cosumablesUsed6 = cosumablesUsed6;
	}

	/**
	 * Gets the run hours after pmd g1.
	 * 
	 * @return the run hours after pmd g1
	 */
	@Column(name = "run_hours_after_pmd_g1")
	public Long getRunHoursAfterPmdG1() {
		return runHoursAfterPmdG1;
	}

	/**
	 * Sets the run hours after pmd g1.
	 * 
	 * @param runHoursAfterPmdG1
	 *            the new run hours after pmd g1
	 */
	public void setRunHoursAfterPmdG1(final Long runHoursAfterPmdG1) {
		this.runHoursAfterPmdG1 = runHoursAfterPmdG1;
	}

	/**
	 * Gets the run hour after pmd g2.
	 * 
	 * @return the run hour after pmd g2
	 */
	@Column(name = "run_hour_after_pmd_g2")
	public Long getRunHourAfterPmdG2() {
		return runHourAfterPmdG2;
	}

	/**
	 * Sets the run hour after pmd g2.
	 * 
	 * @param runHourAfterPmdG2
	 *            the new run hour after pmd g2
	 */
	public void setRunHourAfterPmdG2(final Long runHourAfterPmdG2) {
		this.runHourAfterPmdG2 = runHourAfterPmdG2;
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
		if (this.site != null) {
			siteId = this.site.getName();
		}
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
	 * Hash code.
	 * 
	 * @return the int
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accessCode == null) ? 0 : accessCode.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	/**
	 * Equals.
	 * 
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 * @see java.lang.Object#equals(java.lang.Object)
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
		MaintenanceVisit other = (MaintenanceVisit) obj;
		if (accessCode == null) {
			if (other.accessCode != null) {
				return false;
			}
		} else if (!accessCode.equals(other.accessCode)) {
			return false;
		}
		if (site == null) {
			if (other.site != null) {
				return false;
			}
		} else if (!site.equals(other.site)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;
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
		builder.append("MaintenanceVisit [");
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
		if (user != null) {
			builder.append("user=");
			builder.append(user);
			builder.append(", ");
		}
		if (site != null) {
			builder.append("site=");
			builder.append(site);
			builder.append(", ");
		}
		if (accessCode != null) {
			builder.append("accessCode=");
			builder.append(accessCode);
			builder.append(", ");
		}
		if (categoryOfMaintenance != null) {
			builder.append("categoryOfMaintenance=");
			builder.append(categoryOfMaintenance);
			builder.append(", ");
		}
		if (sparesUsedItemsReplaced1 != null) {
			builder.append("sparesUsedItemsReplaced1=");
			builder.append(sparesUsedItemsReplaced1);
			builder.append(", ");
		}
		if (sparesUsedItemsReplaced2 != null) {
			builder.append("sparesUsedItemsReplaced2=");
			builder.append(sparesUsedItemsReplaced2);
			builder.append(", ");
		}
		if (sparesUsedItemsReplaced3 != null) {
			builder.append("sparesUsedItemsReplaced3=");
			builder.append(sparesUsedItemsReplaced3);
			builder.append(", ");
		}
		if (sparesUsedItemsReplaced4 != null) {
			builder.append("sparesUsedItemsReplaced4=");
			builder.append(sparesUsedItemsReplaced4);
			builder.append(", ");
		}
		if (sparesUsedItemsReplaced5 != null) {
			builder.append("sparesUsedItemsReplaced5=");
			builder.append(sparesUsedItemsReplaced5);
			builder.append(", ");
		}
		if (sparesUsedItemsReplaced6 != null) {
			builder.append("sparesUsedItemsReplaced6=");
			builder.append(sparesUsedItemsReplaced6);
			builder.append(", ");
		}
		if (cosumablesUsed1 != null) {
			builder.append("cosumablesUsed1=");
			builder.append(cosumablesUsed1);
			builder.append(", ");
		}
		if (cosumablesUsed2 != null) {
			builder.append("cosumablesUsed2=");
			builder.append(cosumablesUsed2);
			builder.append(", ");
		}
		if (cosumablesUsed3 != null) {
			builder.append("cosumablesUsed3=");
			builder.append(cosumablesUsed3);
			builder.append(", ");
		}
		if (cosumablesUsed4 != null) {
			builder.append("cosumablesUsed4=");
			builder.append(cosumablesUsed4);
			builder.append(", ");
		}
		if (cosumablesUsed5 != null) {
			builder.append("cosumablesUsed5=");
			builder.append(cosumablesUsed5);
			builder.append(", ");
		}
		if (cosumablesUsed6 != null) {
			builder.append("cosumablesUsed6=");
			builder.append(cosumablesUsed6);
			builder.append(", ");
		}
		if (runHoursAfterPmdG1 != null) {
			builder.append("runHoursAfterPmdG1=");
			builder.append(runHoursAfterPmdG1);
			builder.append(", ");
		}
		if (runHourAfterPmdG2 != null) {
			builder.append("runHourAfterPmdG2=");
			builder.append(runHourAfterPmdG2);
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
		}
		builder.append("]");
		return builder.toString();
	}

}
