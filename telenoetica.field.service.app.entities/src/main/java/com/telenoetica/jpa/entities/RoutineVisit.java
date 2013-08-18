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
 * RoutineVisit.
 * 
 * @author Shiv Prasad Khillar
 */
@Entity
@Table(name = "routine_visit")
@JsonAutoDetect(JsonMethod.NONE)
public class RoutineVisit implements java.io.Serializable, BaseEntity {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -8940474556363138173L;

  /** The id. */
  @JsonProperty
  private Long id;

  /** The version. */
  private Integer version;

  // @JsonProperty
  /** The user. */
  private User user;

  // @JsonProperty
  /** The site. */
  private Site site;

  /** The access code. */
  @JsonProperty
  private String accessCode;

  /** The diesel level t1. */
  @JsonProperty
  private Long dieselLevelT1;

  /** The diesel level t2. */
  @JsonProperty
  private Long dieselLevelT2;

  /** The run hour gen1. */
  @JsonProperty
  private Long runHourGen1;

  /** The run hour gen2. */
  @JsonProperty
  private Long runHourGen2;

  /** The voltage nr volts. */
  @JsonProperty
  private Long voltageNrVolts;

  /** The voltage ny volts. */
  @JsonProperty
  private Long voltageNyVolts;

  /** The voltage nb volts. */
  @JsonProperty
  private Long voltageNbVolts;

  /** The load r phase amps. */
  @JsonProperty
  private Long loadRPhaseAmps;

  /** The load y phase amps. */
  @JsonProperty
  private Long loadYPhaseAmps;

  /** The load b phase amps. */
  @JsonProperty
  private Long loadBPhaseAmps;

  /** The rectifier op voltage. */
  @JsonProperty
  private Long rectifierOpVoltage;

  /** The rectifier op current amp. */
  @JsonProperty
  private Long rectifierOpCurrentAmp;

  /** The battery capcity v. */
  @JsonProperty
  private Long batteryCapcityV;

  /** The battery capcity ah. */
  @JsonProperty
  private Long batteryCapcityAh;

  /** The ats functional. */
  @JsonProperty
  private Boolean atsFunctional = Boolean.FALSE;

  /** The ats sysncronising. */
  @JsonProperty
  private Boolean atsSysncronising = Boolean.FALSE;

  /** The drn book at site. */
  @JsonProperty
  private Boolean drnBookAtSite = Boolean.FALSE;

  /** The diesel log book maintained. */
  @JsonProperty
  private Boolean dieselLogBookMaintained = Boolean.FALSE;

  /** The aircon shelter1 cooling. */
  @JsonProperty
  private String airconShelter1Cooling;

  /** The aircon shelter2 cooling. */
  @JsonProperty
  private String airconShelter2Cooling;

  /** The aircon shelter3 cooling. */
  @JsonProperty
  private String airconShelter3Cooling;

  /** The aircon shelter4 cooling. */
  @JsonProperty
  private String airconShelter4Cooling;

  /** The created at. */
  @JsonProperty
  private Date createdAt = new Date();

  /** The user id. */
  @JsonProperty
  private String userId;

  /** The site id. */
  @JsonProperty
  private String siteId;

  /**
   * Instantiates a new routine visit.
   */
  public RoutineVisit() {
  }

  /**
   * Instantiates a new routine visit.
   * 
   * @param createdAt
   *            the created at
   */
  public RoutineVisit(final Date createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Instantiates a new routine visit.
   * 
   * @param user
   *            the user
   * @param site
   *            the site
   * @param accessCode
   *            the access code
   * @param dieselLevelT1
   *            the diesel level t1
   * @param dieselLevelT2
   *            the diesel level t2
   * @param runHourGen1
   *            the run hour gen1
   * @param runHourGen2
   *            the run hour gen2
   * @param voltageNrVolts
   *            the voltage nr volts
   * @param voltageNyVolts
   *            the voltage ny volts
   * @param voltageNbVolts
   *            the voltage nb volts
   * @param loadRPhaseAmps
   *            the load r phase amps
   * @param loadYPhaseAmps
   *            the load y phase amps
   * @param loadBPhaseAmps
   *            the load b phase amps
   * @param rectifierOpVoltage
   *            the rectifier op voltage
   * @param rectifierOpCurrentAmp
   *            the rectifier op current amp
   * @param batteryCapcityV
   *            the battery capcity v
   * @param batteryCapcityAh
   *            the battery capcity ah
   * @param atsFunctional
   *            the ats functional
   * @param atsSysncronising
   *            the ats sysncronising
   * @param drnBookAtSite
   *            the drn book at site
   * @param dieselLogBookMaintained
   *            the diesel log book maintained
   * @param createdAt
   *            the created at
   */
  public RoutineVisit(final User user, final Site site, final String accessCode,
      final Long dieselLevelT1, final Long dieselLevelT2, final Long runHourGen1,
      final Long runHourGen2, final Long voltageNrVolts, final Long voltageNyVolts,
      final Long voltageNbVolts, final Long loadRPhaseAmps, final Long loadYPhaseAmps,
      final Long loadBPhaseAmps, final Long rectifierOpVoltage,
      final Long rectifierOpCurrentAmp, final Long batteryCapcityV,
      final Long batteryCapcityAh, final Boolean atsFunctional,
      final Boolean atsSysncronising, final Boolean drnBookAtSite,
      final Boolean dieselLogBookMaintained, final Date createdAt) {
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
    if (this.site != null) {
      siteId = this.site.getName();
    }
  }

  /**
   * Gets the access code.
   * 
   * @return the access code
   */
  @Column(name = "access_code")
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
   * Gets the diesel level t1.
   * 
   * @return the diesel level t1
   */
  @Column(name = "diesel_level_t1")
  public Long getDieselLevelT1() {
    return dieselLevelT1;
  }

  /**
   * Sets the diesel level t1.
   * 
   * @param dieselLevelT1
   *            the new diesel level t1
   */
  public void setDieselLevelT1(final Long dieselLevelT1) {
    this.dieselLevelT1 = dieselLevelT1;
  }

  /**
   * Gets the diesel level t2.
   * 
   * @return the diesel level t2
   */
  @Column(name = "diesel_level_t2")
  public Long getDieselLevelT2() {
    return dieselLevelT2;
  }

  /**
   * Sets the diesel level t2.
   * 
   * @param dieselLevelT2
   *            the new diesel level t2
   */
  public void setDieselLevelT2(final Long dieselLevelT2) {
    this.dieselLevelT2 = dieselLevelT2;
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
   * Gets the voltage nr volts.
   * 
   * @return the voltage nr volts
   */
  @Column(name = "voltage_nr_volts")
  public Long getVoltageNrVolts() {
    return voltageNrVolts;
  }

  /**
   * Sets the voltage nr volts.
   * 
   * @param voltageNrVolts
   *            the new voltage nr volts
   */
  public void setVoltageNrVolts(final Long voltageNrVolts) {
    this.voltageNrVolts = voltageNrVolts;
  }

  /**
   * Gets the voltage ny volts.
   * 
   * @return the voltage ny volts
   */
  @Column(name = "voltage_ny_volts")
  public Long getVoltageNyVolts() {
    return voltageNyVolts;
  }

  /**
   * Sets the voltage ny volts.
   * 
   * @param voltageNyVolts
   *            the new voltage ny volts
   */
  public void setVoltageNyVolts(final Long voltageNyVolts) {
    this.voltageNyVolts = voltageNyVolts;
  }

  /**
   * Gets the voltage nb volts.
   * 
   * @return the voltage nb volts
   */
  @Column(name = "voltage_nb_volts")
  public Long getVoltageNbVolts() {
    return voltageNbVolts;
  }

  /**
   * Sets the voltage nb volts.
   * 
   * @param voltageNbVolts
   *            the new voltage nb volts
   */
  public void setVoltageNbVolts(final Long voltageNbVolts) {
    this.voltageNbVolts = voltageNbVolts;
  }

  /**
   * Gets the load r phase amps.
   * 
   * @return the load r phase amps
   */
  @Column(name = "load_r_phase_amps")
  public Long getLoadRPhaseAmps() {
    return loadRPhaseAmps;
  }

  /**
   * Sets the load r phase amps.
   * 
   * @param loadRPhaseAmps
   *            the new load r phase amps
   */
  public void setLoadRPhaseAmps(final Long loadRPhaseAmps) {
    this.loadRPhaseAmps = loadRPhaseAmps;
  }

  /**
   * Gets the load y phase amps.
   * 
   * @return the load y phase amps
   */
  @Column(name = "load_y_phase_amps")
  public Long getLoadYPhaseAmps() {
    return loadYPhaseAmps;
  }

  /**
   * Sets the load y phase amps.
   * 
   * @param loadYPhaseAmps
   *            the new load y phase amps
   */
  public void setLoadYPhaseAmps(final Long loadYPhaseAmps) {
    this.loadYPhaseAmps = loadYPhaseAmps;
  }

  /**
   * Gets the load b phase amps.
   * 
   * @return the load b phase amps
   */
  @Column(name = "load_b_phase_amps")
  public Long getLoadBPhaseAmps() {
    return loadBPhaseAmps;
  }

  /**
   * Sets the load b phase amps.
   * 
   * @param loadBPhaseAmps
   *            the new load b phase amps
   */
  public void setLoadBPhaseAmps(final Long loadBPhaseAmps) {
    this.loadBPhaseAmps = loadBPhaseAmps;
  }

  /**
   * Gets the rectifier op voltage.
   * 
   * @return the rectifier op voltage
   */
  @Column(name = "rectifier_op_voltage")
  public Long getRectifierOpVoltage() {
    return rectifierOpVoltage;
  }

  /**
   * Sets the rectifier op voltage.
   * 
   * @param rectifierOpVoltage
   *            the new rectifier op voltage
   */
  public void setRectifierOpVoltage(final Long rectifierOpVoltage) {
    this.rectifierOpVoltage = rectifierOpVoltage;
  }

  /**
   * Gets the rectifier op current amp.
   * 
   * @return the rectifier op current amp
   */
  @Column(name = "rectifier_op_current_amp")
  public Long getRectifierOpCurrentAmp() {
    return rectifierOpCurrentAmp;
  }

  /**
   * Sets the rectifier op current amp.
   * 
   * @param rectifierOpCurrentAmp
   *            the new rectifier op current amp
   */
  public void setRectifierOpCurrentAmp(final Long rectifierOpCurrentAmp) {
    this.rectifierOpCurrentAmp = rectifierOpCurrentAmp;
  }

  /**
   * Gets the battery capcity v.
   * 
   * @return the battery capcity v
   */
  @Column(name = "battery_capcity_v")
  public Long getBatteryCapcityV() {
    return batteryCapcityV;
  }

  /**
   * Sets the battery capcity v.
   * 
   * @param batteryCapcityV
   *            the new battery capcity v
   */
  public void setBatteryCapcityV(final Long batteryCapcityV) {
    this.batteryCapcityV = batteryCapcityV;
  }

  /**
   * Gets the battery capcity ah.
   * 
   * @return the battery capcity ah
   */
  @Column(name = "battery_capcity_ah")
  public Long getBatteryCapcityAh() {
    return batteryCapcityAh;
  }

  /**
   * Sets the battery capcity ah.
   * 
   * @param batteryCapcityAh
   *            the new battery capcity ah
   */
  public void setBatteryCapcityAh(final Long batteryCapcityAh) {
    this.batteryCapcityAh = batteryCapcityAh;
  }

  /**
   * Gets the ats functional.
   * 
   * @return the ats functional
   */
  @Column(name = "ats_functional")
  public Boolean getAtsFunctional() {
    return atsFunctional;
  }

  /**
   * Sets the ats functional.
   * 
   * @param atsFunctional
   *            the new ats functional
   */
  public void setAtsFunctional(final Boolean atsFunctional) {
    this.atsFunctional = atsFunctional;
  }

  /**
   * Gets the ats sysncronising.
   * 
   * @return the ats sysncronising
   */
  @Column(name = "ats_sysncronising")
  public Boolean getAtsSysncronising() {
    return atsSysncronising;
  }

  /**
   * Sets the ats sysncronising.
   * 
   * @param atsSysncronising
   *            the new ats sysncronising
   */
  public void setAtsSysncronising(final Boolean atsSysncronising) {
    this.atsSysncronising = atsSysncronising;
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
   * Gets the aircon shelter1 cooling.
   * 
   * @return the aircon shelter1 cooling
   */
  @Column(name = "aircon_shelter1_cooling")
  public String getAirconShelter1Cooling() {
    return airconShelter1Cooling;
  }

  /**
   * Sets the aircon shelter1 cooling.
   * 
   * @param airconShelter1Cooling
   *            the new aircon shelter1 cooling
   */
  public void setAirconShelter1Cooling(final String airconShelter1Cooling) {
    this.airconShelter1Cooling = airconShelter1Cooling;
  }

  /**
   * Gets the aircon shelter2 cooling.
   * 
   * @return the aircon shelter2 cooling
   */
  @Column(name = "aircon_shelter2_cooling")
  public String getAirconShelter2Cooling() {
    return airconShelter2Cooling;
  }

  /**
   * Sets the aircon shelter2 cooling.
   * 
   * @param airconShelter2Cooling
   *            the new aircon shelter2 cooling
   */
  public void setAirconShelter2Cooling(final String airconShelter2Cooling) {
    this.airconShelter2Cooling = airconShelter2Cooling;
  }

  /**
   * Gets the aircon shelter3 cooling.
   * 
   * @return the aircon shelter3 cooling
   */
  @Column(name = "aircon_shelter3_cooling")
  public String getAirconShelter3Cooling() {
    return airconShelter3Cooling;
  }

  /**
   * Sets the aircon shelter3 cooling.
   * 
   * @param airconShelter3Cooling
   *            the new aircon shelter3 cooling
   */
  public void setAirconShelter3Cooling(final String airconShelter3Cooling) {
    this.airconShelter3Cooling = airconShelter3Cooling;
  }

  /**
   * Gets the aircon shelter4 cooling.
   * 
   * @return the aircon shelter4 cooling
   */
  @Column(name = "aircon_shelter4_cooling")
  public String getAirconShelter4Cooling() {
    return airconShelter4Cooling;
  }

  /**
   * Sets the aircon shelter4 cooling.
   * 
   * @param airconShelter4Cooling
   *            the new aircon shelter4 cooling
   */
  public void setAirconShelter4Cooling(final String airconShelter4Cooling) {
    this.airconShelter4Cooling = airconShelter4Cooling;
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
   * Hash code.
   *
   * @return the int
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accessCode == null) ? 0 : accessCode.hashCode());
    result = prime * result + ((site == null) ? 0 : site.hashCode());
    result = prime * result + ((user == null) ? 0 : user.hashCode());
    return result;
  }

  /**
   * Equals.
   *
   * @param obj the obj
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
    RoutineVisit other = (RoutineVisit) obj;
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
    builder.append("RoutineVisit [");
    if (accessCode != null) {
      builder.append("accessCode=");
      builder.append(accessCode);
      builder.append(", ");
    }
    if (dieselLevelT1 != null) {
      builder.append("dieselLevelT1=");
      builder.append(dieselLevelT1);
      builder.append(", ");
    }
    if (dieselLevelT2 != null) {
      builder.append("dieselLevelT2=");
      builder.append(dieselLevelT2);
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
    if (voltageNrVolts != null) {
      builder.append("voltageNrVolts=");
      builder.append(voltageNrVolts);
      builder.append(", ");
    }
    if (voltageNyVolts != null) {
      builder.append("voltageNyVolts=");
      builder.append(voltageNyVolts);
      builder.append(", ");
    }
    if (voltageNbVolts != null) {
      builder.append("voltageNbVolts=");
      builder.append(voltageNbVolts);
      builder.append(", ");
    }
    if (loadRPhaseAmps != null) {
      builder.append("loadRPhaseAmps=");
      builder.append(loadRPhaseAmps);
      builder.append(", ");
    }
    if (loadYPhaseAmps != null) {
      builder.append("loadYPhaseAmps=");
      builder.append(loadYPhaseAmps);
      builder.append(", ");
    }
    if (loadBPhaseAmps != null) {
      builder.append("loadBPhaseAmps=");
      builder.append(loadBPhaseAmps);
      builder.append(", ");
    }
    if (rectifierOpVoltage != null) {
      builder.append("rectifierOpVoltage=");
      builder.append(rectifierOpVoltage);
      builder.append(", ");
    }
    if (rectifierOpCurrentAmp != null) {
      builder.append("rectifierOpCurrentAmp=");
      builder.append(rectifierOpCurrentAmp);
      builder.append(", ");
    }
    if (batteryCapcityV != null) {
      builder.append("batteryCapcityV=");
      builder.append(batteryCapcityV);
      builder.append(", ");
    }
    if (batteryCapcityAh != null) {
      builder.append("batteryCapcityAh=");
      builder.append(batteryCapcityAh);
      builder.append(", ");
    }
    if (atsFunctional != null) {
      builder.append("atsFunctional=");
      builder.append(atsFunctional);
      builder.append(", ");
    }
    if (atsSysncronising != null) {
      builder.append("atsSysncronising=");
      builder.append(atsSysncronising);
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
    if (airconShelter1Cooling != null) {
      builder.append("airconShelter1Cooling=");
      builder.append(airconShelter1Cooling);
      builder.append(", ");
    }
    if (airconShelter2Cooling != null) {
      builder.append("airconShelter2Cooling=");
      builder.append(airconShelter2Cooling);
      builder.append(", ");
    }
    if (airconShelter3Cooling != null) {
      builder.append("airconShelter3Cooling=");
      builder.append(airconShelter3Cooling);
      builder.append(", ");
    }
    if (airconShelter4Cooling != null) {
      builder.append("airconShelter4Cooling=");
      builder.append(airconShelter4Cooling);
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
