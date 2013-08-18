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
 * Site.
 *
 * @author  Shiv Prasad Khillar
 */
@Entity
@Table(name = "site")
@JsonAutoDetect(JsonMethod.NONE)
public class Site implements BaseEntity, java.io.Serializable {

  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -8172399414155973257L;
  
  /** The id. */
  @JsonProperty
  private Long id;
  
  /** The version. */
  private Integer version;
  
  /** The name. */
  @JsonProperty
  private String name;
  
  /** The state. */
  @JsonProperty
  private String state;
  
  /** The created at. */
  @JsonProperty
  private Date createdAt=new Date();

  /**
   * Instantiates a new site.
   */
  public Site() {}

  /**
   * Instantiates a new site.
   *
   * @param createdAt the created at
   */
  public Site(Date createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * Instantiates a new site.
   *
   * @param name the name
   * @param state the state
   * @param createdAt the created at
   */
  public Site(String name,String state, Date createdAt) {
    this.name = name;
    this.createdAt = createdAt;
    this.state=state;
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
    return this.id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(Long id) {
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
    return this.version;
  }

  /**
   * Sets the version.
   *
   * @param version the new version
   */
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  @Column(name = "name", length = 250)
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * Gets the state.
   *
   * @return the state
   */
  @Column(name = "state", length = 100)
  public String getState() {
    return state;
  }

  /**
   * Sets the state.
   *
   * @param state the new state
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Gets the created at.
   *
   * @return the created at
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false, length = 19)
  public Date getCreatedAt() {
    return this.createdAt;
  }

  /**
   * Sets the created at.
   *
   * @param createdAt the new created at
   */
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
  
  /* (non-Javadoc)
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

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Site other = (Site) obj;
    if (createdAt == null) {
      if (other.createdAt != null)
        return false;
    } else if (!createdAt.equals(other.createdAt))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Site [");
    if (id != null)
      builder.append("id=").append(id).append(", ");
    if (version != null)
      builder.append("version=").append(version).append(", ");
    if (name != null)
      builder.append("name=").append(name).append(", ");
    if (state != null)
      builder.append("state=").append(state).append(", ");
    if (createdAt != null)
      builder.append("createdAt=").append(createdAt);
    builder.append("]");
    return builder.toString();
  }
  

}
