/*
 * Copyright (C) 2013 Telenoetica, Inc. All rights reserved
 */
package com.telenoetica.jpa.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class JobHistory.
 * 
 * @author Shiv Prasad Khillar
 */
@Entity
@Table(name = "job_history")
@JsonAutoDetect(JsonMethod.NONE)
public class JobHistory implements BaseEntity, Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -3083249622983667890L;

  /** The id. */
  @JsonProperty
  private Long id;

  /** The version. */
  private Integer version;

  /** The job name. */
  @JsonProperty
  private String jobName;

  /** The description. */
  @JsonProperty
  private String description;

  /** The start time. */
  @JsonProperty
  private Date startTime;

  /** The end time. */
  @JsonProperty
  private Date endTime;

  /** The job status. */
  @JsonProperty
  private JobStatus jobStatus;

  /** The report path. */
  @JsonProperty
  private String path;

  /**
   * Instantiates a new job history.
   */
  public JobHistory() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Instantiates a new job history.
   * 
   * @param jobName
   *            the job name
   * @param description
   *            the description
   * @param startTime
   *            the start time
   * @param endTime
   *            the end time
   * @param jobStatus
   *            the job status
   */
  public JobHistory(final String jobName, final String description,
      final Date startTime, final Date endTime, final JobStatus jobStatus) {
    super();
    this.jobName = jobName;
    this.description = description;
    this.startTime = startTime;
    this.endTime = endTime;
    this.jobStatus = jobStatus;
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
   * Gets the job name.
   * 
   * @return the job name
   */
  @Column(name = "job_name", length = 250)
  public String getJobName() {
    return jobName;
  }

  /**
   * Sets the job name.
   * 
   * @param jobName
   *            the new job name
   */
  public void setJobName(final String jobName) {
    this.jobName = jobName;
  }

  /**
   * Gets the description.
   * 
   * @return the description
   */
  @Column(name = "description", length = 250)
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description.
   * 
   * @param description
   *            the new description
   */
  public void setDescription(final String description) {
    this.description = description;
  }

  /**
   * Gets the start time.
   * 
   * @return the start time
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "start_time", nullable = false, length = 19)
  public Date getStartTime() {
    return startTime;
  }

  /**
   * Sets the start time.
   * 
   * @param startTime
   *            the new start time
   */
  public void setStartTime(final Date startTime) {
    this.startTime = startTime;
  }

  /**
   * Gets the end time.
   * 
   * @return the end time
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "end_time", nullable = false, length = 19)
  public Date getEndTime() {
    return endTime;
  }

  /**
   * Sets the end time.
   * 
   * @param endTime
   *            the new end time
   */
  public void setEndTime(final Date endTime) {
    this.endTime = endTime;
  }

  /**
   * Gets the job status.
   * 
   * @return the job status
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 20)
  public JobStatus getJobStatus() {
    return jobStatus;
  }

  /**
   * Sets the job status.
   * 
   * @param jobStatus
   *            the new job status
   */
  public void setJobStatus(final JobStatus jobStatus) {
    this.jobStatus = jobStatus;
  }

  /**
   * Gets the path.
   *
   * @return the path
   */
  public String getPath() {
    return path;
  }

  /**
   * Sets the path.
   *
   * @param path the new path
   */
  public void setPath(final String path) {
    this.path = path;
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
   * Hash code.
   *
   * @return the int
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((jobName == null) ? 0 : jobName.hashCode());
    result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
    JobHistory other = (JobHistory) obj;
    if (jobName == null) {
      if (other.jobName != null) {
        return false;
      }
    } else if (!jobName.equals(other.jobName)) {
      return false;
    }
    if (startTime == null) {
      if (other.startTime != null) {
        return false;
      }
    } else if (!startTime.equals(other.startTime)) {
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
    builder.append("JobHistory [");
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
    if (jobName != null) {
      builder.append("jobName=");
      builder.append(jobName);
      builder.append(", ");
    }
    if (description != null) {
      builder.append("description=");
      builder.append(description);
      builder.append(", ");
    }
    if (startTime != null) {
      builder.append("startTime=");
      builder.append(startTime);
      builder.append(", ");
    }
    if (endTime != null) {
      builder.append("endTime=");
      builder.append(endTime);
      builder.append(", ");
    }
    if (jobStatus != null) {
      builder.append("jobStatus=");
      builder.append(jobStatus);
    }
    builder.append("]");
    return builder.toString();
  }

}
