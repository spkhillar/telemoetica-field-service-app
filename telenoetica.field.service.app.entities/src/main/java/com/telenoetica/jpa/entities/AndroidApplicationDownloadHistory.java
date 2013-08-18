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
import javax.persistence.Version;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Site.
 * 
 * @author Shiv Prasad Khillar
 */
@Entity
@Table(name = "android_application_download_history")
@JsonAutoDetect(JsonMethod.NONE)
public class AndroidApplicationDownloadHistory implements BaseEntity, java.io.Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -8172399414155973257L;

  /** The id. */
  @JsonProperty
  private Long id;
  /** The name. */
  @JsonProperty
  private String userDeviceId;

  /** The user. */
  private User user;

  /** The created at. */
  @JsonProperty
  private Date createdAt = new Date();

  /** The version. */
  private Integer version;

  /**
   * Instantiates a new site.
   */
  public AndroidApplicationDownloadHistory() {}

  public AndroidApplicationDownloadHistory(final String userDeviceId, final User user) {
    super();
    this.userDeviceId = userDeviceId;
    this.user = user;
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
   *          the new id
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
   *          the new version
   */
  public void setVersion(final Integer version) {
    this.version = version;
  }

  /**
   * @return the userDeviceId
   */
  @Column(name = "user_device_id", length = 250)
  public String getUserDeviceId() {
    return userDeviceId;
  }

  /**
   * @param userDeviceId
   *          the userDeviceId to set
   */
  public void setUserDeviceId(final String userDeviceId) {
    this.userDeviceId = userDeviceId;
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
   *          the new user
   */
  public void setUser(final User user) {
    this.user = user;
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
   *          the new created at
   */
  public void setCreatedAt(final Date createdAt) {
    this.createdAt = createdAt;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
    result = prime * result + ((user == null) ? 0 : user.hashCode());
    result = prime * result + ((userDeviceId == null) ? 0 : userDeviceId.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   * 
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
    AndroidApplicationDownloadHistory other = (AndroidApplicationDownloadHistory) obj;
    if (createdAt == null) {
      if (other.createdAt != null) {
        return false;
      }
    } else if (!createdAt.equals(other.createdAt)) {
      return false;
    }
    if (user == null) {
      if (other.user != null) {
        return false;
      }
    } else if (!user.equals(other.user)) {
      return false;
    }
    if (userDeviceId == null) {
      if (other.userDeviceId != null) {
        return false;
      }
    } else if (!userDeviceId.equals(other.userDeviceId)) {
      return false;
    }
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("AndroidApplicationDownloadHistory [");
    if (id != null) {
      builder.append("id=");
      builder.append(id);
      builder.append(", ");
    }
    if (userDeviceId != null) {
      builder.append("userDeviceId=");
      builder.append(userDeviceId);
      builder.append(", ");
    }
    if (user != null) {
      builder.append("user=");
      builder.append(user);
      builder.append(", ");
    }
    if (createdAt != null) {
      builder.append("createdAt=");
      builder.append(createdAt);
      builder.append(", ");
    }
    if (version != null) {
      builder.append("version=");
      builder.append(version);
    }
    builder.append("]");
    return builder.toString();
  }

}
