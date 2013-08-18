/* Copyright (C) 2013 Telenoetica, Inc. All rights reserved */
package com.telenoetica.jpa.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
 * Client.
 *
 * @author  Shiv Prasad Khillar
 */
@Entity
@Table(name = "client")
@JsonAutoDetect(JsonMethod.NONE)
public class Client implements BaseEntity, java.io.Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -2252865704700104403L;

  /** The id. */
  @JsonProperty
  private Long id;

  /** The version. */
  private Integer version;

  /** The name. */
  @JsonProperty
  private String name;

  /** The created at. */
  @JsonProperty
  private Date createdAt=new Date();

  /**
   * Instantiates a new client.
   */
  public Client() {}

  /**
   * Instantiates a new client.
   *
   * @param createdAt the created at
   */
  public Client(final Date createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Instantiates a new client.
   *
   * @param name the name
   * @param createdAt the created at
   */
  public Client(final String name, final Date createdAt) {
    this.name = name;
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
   * @param id the new id
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
   * @param version the new version
   */
  public void setVersion(final Integer version) {
    this.version = version;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  @Column(name = "name", length = 250)
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName(final String name) {
    this.name = name;
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
   * @param createdAt the new created at
   */
  public void setCreatedAt(final Date createdAt) {
    this.createdAt = createdAt;
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
    result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
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
    Client other = (Client) obj;
    if (createdAt == null) {
      if (other.createdAt != null) {
        return false;
      }
    } else if (!createdAt.equals(other.createdAt)) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
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
    builder.append("Site [");
    if (id != null) {
      builder.append("id=").append(id).append(", ");
    }
    if (version != null) {
      builder.append("version=").append(version).append(", ");
    }
    if (name != null) {
      builder.append("name=").append(name).append(", ");
    }
    if (createdAt != null) {
      builder.append("createdAt=").append(createdAt);
    }
    builder.append("]");
    return builder.toString();
  }


}
