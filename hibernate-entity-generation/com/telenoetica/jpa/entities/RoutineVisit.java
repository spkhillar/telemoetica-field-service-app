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
 * RoutineVisit 
 */
@Entity
@Table(name = "routine_visit", catalog = "field_service_db")
public class RoutineVisit implements java.io.Serializable {

	private Long id;
	private Integer version;
	private User user;
	private Site site;
	private Long accessCode;
	private Long dieselLevelT1;
	private Long dieselLevelT2;
	private Long runHourGen1;
	private Long runHourGen2;
	private Long voltageNrVolts;
	private Long voltageNyVolts;
	private Long voltageNbVolts;
	private Long loadRPhaseAmps;
	private Long loadYPhaseAmps;
	private Long loadBPhaseAmps;
	private Long rectifierOpVoltage;
	private Long rectifierOpCurrentAmp;
	private Long batteryCapcityV;
	private Long batteryCapcityAh;
	private Boolean atsFunctional;
	private Boolean atsSysncronising;
	private Boolean drnBookAtSite;
	private Boolean dieselLogBookMaintained;
	private Date createdAt;

	public RoutineVisit() {
	}

	public RoutineVisit(Date createdAt) {
		this.createdAt = createdAt;
	}

	public RoutineVisit(User user, Site site, Long accessCode,
			Long dieselLevelT1, Long dieselLevelT2, Long runHourGen1,
			Long runHourGen2, Long voltageNrVolts, Long voltageNyVolts,
			Long voltageNbVolts, Long loadRPhaseAmps, Long loadYPhaseAmps,
			Long loadBPhaseAmps, Long rectifierOpVoltage,
			Long rectifierOpCurrentAmp, Long batteryCapcityV,
			Long batteryCapcityAh, Boolean atsFunctional,
			Boolean atsSysncronising, Boolean drnBookAtSite,
			Boolean dieselLogBookMaintained, Date createdAt) {
		this.user = user;
		this.site = site;
		this.accessCode = accessCode;
		this.dieselLevelT1 = dieselLevelT1;
		this.dieselLevelT2 = dieselLevelT2;
		this.runHourGen1 = runHourGen1;
		this.runHourGen2 = runHourGen2;
		this.voltageNrVolts = voltageNrVolts;
		this.voltageNyVolts = voltageNyVolts;
		this.voltageNbVolts = voltageNbVolts;
		this.loadRPhaseAmps = loadRPhaseAmps;
		this.loadYPhaseAmps = loadYPhaseAmps;
		this.loadBPhaseAmps = loadBPhaseAmps;
		this.rectifierOpVoltage = rectifierOpVoltage;
		this.rectifierOpCurrentAmp = rectifierOpCurrentAmp;
		this.batteryCapcityV = batteryCapcityV;
		this.batteryCapcityAh = batteryCapcityAh;
		this.atsFunctional = atsFunctional;
		this.atsSysncronising = atsSysncronising;
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

	@Column(name = "access_code")
	public Long getAccessCode() {
		return this.accessCode;
	}

	public void setAccessCode(Long accessCode) {
		this.accessCode = accessCode;
	}

	@Column(name = "diesel_level_t1")
	public Long getDieselLevelT1() {
		return this.dieselLevelT1;
	}

	public void setDieselLevelT1(Long dieselLevelT1) {
		this.dieselLevelT1 = dieselLevelT1;
	}

	@Column(name = "diesel_level_t2")
	public Long getDieselLevelT2() {
		return this.dieselLevelT2;
	}

	public void setDieselLevelT2(Long dieselLevelT2) {
		this.dieselLevelT2 = dieselLevelT2;
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

	@Column(name = "voltage_nr_volts")
	public Long getVoltageNrVolts() {
		return this.voltageNrVolts;
	}

	public void setVoltageNrVolts(Long voltageNrVolts) {
		this.voltageNrVolts = voltageNrVolts;
	}

	@Column(name = "voltage_ny_volts")
	public Long getVoltageNyVolts() {
		return this.voltageNyVolts;
	}

	public void setVoltageNyVolts(Long voltageNyVolts) {
		this.voltageNyVolts = voltageNyVolts;
	}

	@Column(name = "voltage_nb_volts")
	public Long getVoltageNbVolts() {
		return this.voltageNbVolts;
	}

	public void setVoltageNbVolts(Long voltageNbVolts) {
		this.voltageNbVolts = voltageNbVolts;
	}

	@Column(name = "load_r_phase_amps")
	public Long getLoadRPhaseAmps() {
		return this.loadRPhaseAmps;
	}

	public void setLoadRPhaseAmps(Long loadRPhaseAmps) {
		this.loadRPhaseAmps = loadRPhaseAmps;
	}

	@Column(name = "load_y_phase_amps")
	public Long getLoadYPhaseAmps() {
		return this.loadYPhaseAmps;
	}

	public void setLoadYPhaseAmps(Long loadYPhaseAmps) {
		this.loadYPhaseAmps = loadYPhaseAmps;
	}

	@Column(name = "load_b_phase_amps")
	public Long getLoadBPhaseAmps() {
		return this.loadBPhaseAmps;
	}

	public void setLoadBPhaseAmps(Long loadBPhaseAmps) {
		this.loadBPhaseAmps = loadBPhaseAmps;
	}

	@Column(name = "rectifier_op_voltage")
	public Long getRectifierOpVoltage() {
		return this.rectifierOpVoltage;
	}

	public void setRectifierOpVoltage(Long rectifierOpVoltage) {
		this.rectifierOpVoltage = rectifierOpVoltage;
	}

	@Column(name = "rectifier_op_current_amp")
	public Long getRectifierOpCurrentAmp() {
		return this.rectifierOpCurrentAmp;
	}

	public void setRectifierOpCurrentAmp(Long rectifierOpCurrentAmp) {
		this.rectifierOpCurrentAmp = rectifierOpCurrentAmp;
	}

	@Column(name = "battery_capcity_v")
	public Long getBatteryCapcityV() {
		return this.batteryCapcityV;
	}

	public void setBatteryCapcityV(Long batteryCapcityV) {
		this.batteryCapcityV = batteryCapcityV;
	}

	@Column(name = "battery_capcity_ah")
	public Long getBatteryCapcityAh() {
		return this.batteryCapcityAh;
	}

	public void setBatteryCapcityAh(Long batteryCapcityAh) {
		this.batteryCapcityAh = batteryCapcityAh;
	}

	@Column(name = "ats_functional")
	public Boolean getAtsFunctional() {
		return this.atsFunctional;
	}

	public void setAtsFunctional(Boolean atsFunctional) {
		this.atsFunctional = atsFunctional;
	}

	@Column(name = "ats_sysncronising")
	public Boolean getAtsSysncronising() {
		return this.atsSysncronising;
	}

	public void setAtsSysncronising(Boolean atsSysncronising) {
		this.atsSysncronising = atsSysncronising;
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
