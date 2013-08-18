package com.telenoetica.jpa.entities;



import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * MaintenanceVisit 
 */
@Entity
@Table(name = "maintenance_visit", catalog = "field_service_db")
public class MaintenanceVisit implements java.io.Serializable {

	private Long id;
	private Integer version;
	private User user;
	private Site site;
	private String accessCode;
	private String categoryOfMaintenance;
	private String sparesUsedItemsReplaced1;
	private String sparesUsedItemsReplaced2;
	private String sparesUsedItemsReplaced3;
	private String sparesUsedItemsReplaced4;
	private String sparesUsedItemsReplaced5;
	private String sparesUsedItemsReplaced6;
	private String cosumablesUsed1;
	private String cosumablesUsed2;
	private String cosumablesUsed3;
	private String cosumablesUsed4;
	private String cosumablesUsed5;
	private String cosumablesUsed6;
	private Long runHoursAfterPmdG1;
	private Long runHourAfterPmdG2;
	private Date createdAt;

	public MaintenanceVisit() {
	}

	public MaintenanceVisit(Date createdAt) {
		this.createdAt = createdAt;
	}

	public MaintenanceVisit(User user, Site site, String accessCode,
			String categoryOfMaintenance, String sparesUsedItemsReplaced1,
			String sparesUsedItemsReplaced2, String sparesUsedItemsReplaced3,
			String sparesUsedItemsReplaced4, String sparesUsedItemsReplaced5,
			String sparesUsedItemsReplaced6, String cosumablesUsed1,
			String cosumablesUsed2, String cosumablesUsed3,
			String cosumablesUsed4, String cosumablesUsed5,
			String cosumablesUsed6, Long runHoursAfterPmdG1,
			Long runHourAfterPmdG2, Date createdAt) {
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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "site_id")
	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	@Column(name = "access_code", length = 250)
	public String getAccessCode() {
		return this.accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	@Column(name = "category_of_maintenance", length = 250)
	public String getCategoryOfMaintenance() {
		return this.categoryOfMaintenance;
	}

	public void setCategoryOfMaintenance(String categoryOfMaintenance) {
		this.categoryOfMaintenance = categoryOfMaintenance;
	}

	@Column(name = "spares_used_items_replaced1", length = 250)
	public String getSparesUsedItemsReplaced1() {
		return this.sparesUsedItemsReplaced1;
	}

	public void setSparesUsedItemsReplaced1(String sparesUsedItemsReplaced1) {
		this.sparesUsedItemsReplaced1 = sparesUsedItemsReplaced1;
	}

	@Column(name = "spares_used_items_replaced2", length = 250)
	public String getSparesUsedItemsReplaced2() {
		return this.sparesUsedItemsReplaced2;
	}

	public void setSparesUsedItemsReplaced2(String sparesUsedItemsReplaced2) {
		this.sparesUsedItemsReplaced2 = sparesUsedItemsReplaced2;
	}

	@Column(name = "spares_used_items_replaced3", length = 250)
	public String getSparesUsedItemsReplaced3() {
		return this.sparesUsedItemsReplaced3;
	}

	public void setSparesUsedItemsReplaced3(String sparesUsedItemsReplaced3) {
		this.sparesUsedItemsReplaced3 = sparesUsedItemsReplaced3;
	}

	@Column(name = "spares_used_items_replaced4", length = 250)
	public String getSparesUsedItemsReplaced4() {
		return this.sparesUsedItemsReplaced4;
	}

	public void setSparesUsedItemsReplaced4(String sparesUsedItemsReplaced4) {
		this.sparesUsedItemsReplaced4 = sparesUsedItemsReplaced4;
	}

	@Column(name = "spares_used_items_replaced5", length = 250)
	public String getSparesUsedItemsReplaced5() {
		return this.sparesUsedItemsReplaced5;
	}

	public void setSparesUsedItemsReplaced5(String sparesUsedItemsReplaced5) {
		this.sparesUsedItemsReplaced5 = sparesUsedItemsReplaced5;
	}

	@Column(name = "spares_used_items_replaced6", length = 250)
	public String getSparesUsedItemsReplaced6() {
		return this.sparesUsedItemsReplaced6;
	}

	public void setSparesUsedItemsReplaced6(String sparesUsedItemsReplaced6) {
		this.sparesUsedItemsReplaced6 = sparesUsedItemsReplaced6;
	}

	@Column(name = "cosumables_used1", length = 250)
	public String getCosumablesUsed1() {
		return this.cosumablesUsed1;
	}

	public void setCosumablesUsed1(String cosumablesUsed1) {
		this.cosumablesUsed1 = cosumablesUsed1;
	}

	@Column(name = "cosumables_used2", length = 250)
	public String getCosumablesUsed2() {
		return this.cosumablesUsed2;
	}

	public void setCosumablesUsed2(String cosumablesUsed2) {
		this.cosumablesUsed2 = cosumablesUsed2;
	}

	@Column(name = "cosumables_used3", length = 250)
	public String getCosumablesUsed3() {
		return this.cosumablesUsed3;
	}

	public void setCosumablesUsed3(String cosumablesUsed3) {
		this.cosumablesUsed3 = cosumablesUsed3;
	}

	@Column(name = "cosumables_used4", length = 250)
	public String getCosumablesUsed4() {
		return this.cosumablesUsed4;
	}

	public void setCosumablesUsed4(String cosumablesUsed4) {
		this.cosumablesUsed4 = cosumablesUsed4;
	}

	@Column(name = "cosumables_used5", length = 250)
	public String getCosumablesUsed5() {
		return this.cosumablesUsed5;
	}

	public void setCosumablesUsed5(String cosumablesUsed5) {
		this.cosumablesUsed5 = cosumablesUsed5;
	}

	@Column(name = "cosumables_used6", length = 250)
	public String getCosumablesUsed6() {
		return this.cosumablesUsed6;
	}

	public void setCosumablesUsed6(String cosumablesUsed6) {
		this.cosumablesUsed6 = cosumablesUsed6;
	}

	@Column(name = "run_hours_after_pmd_g1")
	public Long getRunHoursAfterPmdG1() {
		return this.runHoursAfterPmdG1;
	}

	public void setRunHoursAfterPmdG1(Long runHoursAfterPmdG1) {
		this.runHoursAfterPmdG1 = runHoursAfterPmdG1;
	}

	@Column(name = "run_hour_after_pmd_g2")
	public Long getRunHourAfterPmdG2() {
		return this.runHourAfterPmdG2;
	}

	public void setRunHourAfterPmdG2(Long runHourAfterPmdG2) {
		this.runHourAfterPmdG2 = runHourAfterPmdG2;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, length = 19)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
