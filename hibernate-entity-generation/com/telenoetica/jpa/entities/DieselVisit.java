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
 * DieselVisit 
 */
@Entity
@Table(name = "diesel_visit", catalog = "field_service_db")
public class DieselVisit implements java.io.Serializable {

	private Long id;
	private Integer version;
	private User user;
	private Site site;
	private String accessCode;
	private String drnNumber;
	private String dieselTransferOrBulkSupply;
	private Long transferFromWhichSite;
	private String bulkNameOfVendor;
	private Long dieselLevelT1BeforeVisit;
	private Long dieselLevelT2BeforeVisit;
	private Long dieselReceivedLtrs;
	private Long runHourGen1;
	private Long runHourGen2;
	private Boolean drnBookAtSite;
	private Boolean dieselLogBookMaintained;
	private Date createdAt;

	public DieselVisit() {
	}

	public DieselVisit(Date createdAt) {
		this.createdAt = createdAt;
	}

	public DieselVisit(User user, Site site, String accessCode,
			String drnNumber, String dieselTransferOrBulkSupply,
			Long transferFromWhichSite, String bulkNameOfVendor,
			Long dieselLevelT1BeforeVisit, Long dieselLevelT2BeforeVisit,
			Long dieselReceivedLtrs, Long runHourGen1, Long runHourGen2,
			Boolean drnBookAtSite, Boolean dieselLogBookMaintained,
			Date createdAt) {
		this.user = user;
		this.site = site;
		this.accessCode = accessCode;
		this.drnNumber = drnNumber;
		this.dieselTransferOrBulkSupply = dieselTransferOrBulkSupply;
		this.transferFromWhichSite = transferFromWhichSite;
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

	@Column(name = "drn_number", length = 250)
	public String getDrnNumber() {
		return this.drnNumber;
	}

	public void setDrnNumber(String drnNumber) {
		this.drnNumber = drnNumber;
	}

	@Column(name = "diesel_transfer_or_bulk_supply", length = 250)
	public String getDieselTransferOrBulkSupply() {
		return this.dieselTransferOrBulkSupply;
	}

	public void setDieselTransferOrBulkSupply(String dieselTransferOrBulkSupply) {
		this.dieselTransferOrBulkSupply = dieselTransferOrBulkSupply;
	}

	@Column(name = "transfer_from_which_site")
	public Long getTransferFromWhichSite() {
		return this.transferFromWhichSite;
	}

	public void setTransferFromWhichSite(Long transferFromWhichSite) {
		this.transferFromWhichSite = transferFromWhichSite;
	}

	@Column(name = "bulk_name_of_vendor", length = 250)
	public String getBulkNameOfVendor() {
		return this.bulkNameOfVendor;
	}

	public void setBulkNameOfVendor(String bulkNameOfVendor) {
		this.bulkNameOfVendor = bulkNameOfVendor;
	}

	@Column(name = "diesel_level_t1_before_visit")
	public Long getDieselLevelT1BeforeVisit() {
		return this.dieselLevelT1BeforeVisit;
	}

	public void setDieselLevelT1BeforeVisit(Long dieselLevelT1BeforeVisit) {
		this.dieselLevelT1BeforeVisit = dieselLevelT1BeforeVisit;
	}

	@Column(name = "diesel_level_t2_before_visit")
	public Long getDieselLevelT2BeforeVisit() {
		return this.dieselLevelT2BeforeVisit;
	}

	public void setDieselLevelT2BeforeVisit(Long dieselLevelT2BeforeVisit) {
		this.dieselLevelT2BeforeVisit = dieselLevelT2BeforeVisit;
	}

	@Column(name = "diesel_received_ltrs")
	public Long getDieselReceivedLtrs() {
		return this.dieselReceivedLtrs;
	}

	public void setDieselReceivedLtrs(Long dieselReceivedLtrs) {
		this.dieselReceivedLtrs = dieselReceivedLtrs;
	}

	@Column(name = "run_hour_gen1")
	public Long getRunHourGen1() {
		return this.runHourGen1;
	}

	public void setRunHourGen1(Long runHourGen1) {
		this.runHourGen1 = runHourGen1;
	}

	@Column(name = "run_hour_gen2")
	public Long getRunHourGen2() {
		return this.runHourGen2;
	}

	public void setRunHourGen2(Long runHourGen2) {
		this.runHourGen2 = runHourGen2;
	}

	@Column(name = "drn_book_at_site")
	public Boolean getDrnBookAtSite() {
		return this.drnBookAtSite;
	}

	public void setDrnBookAtSite(Boolean drnBookAtSite) {
		this.drnBookAtSite = drnBookAtSite;
	}

	@Column(name = "diesel_log_book_maintained")
	public Boolean getDieselLogBookMaintained() {
		return this.dieselLogBookMaintained;
	}

	public void setDieselLogBookMaintained(Boolean dieselLogBookMaintained) {
		this.dieselLogBookMaintained = dieselLogBookMaintained;
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
