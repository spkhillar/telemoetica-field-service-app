/* Copyright (C) 2013 Telenoetica, Inc. All rights reserved */
package com.telenoetica.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UserRole.
 *
 * @author  Shiv Prasad Khillar
 */
@Entity
@Table(name = "user_role")
public class UserRole implements java.io.Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 6247808108412977322L;
  
  /** The id. */
  private Long id;
  
  /** The user. */
  private User user;
  
  /** The role. */
  private Role role;

  /**
   * Instantiates a new user role.
   */
  public UserRole() {}

  /**
   * Instantiates a new user role.
   *
   * @param user the user
   * @param role the role
   */
  public UserRole(User user, Role role) {
    this.user = user;
    this.role = role;
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
   * Gets the user.
   *
   * @return the user
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  public User getUser() {
    return this.user;
  }

  /**
   * Sets the user.
   *
   * @param user the new user
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Gets the role.
   *
   * @return the role
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id")
  public Role getRole() {
    return this.role;
  }

  /**
   * Sets the role.
   *
   * @param role the new role
   */
  public void setRole(Role role) {
    this.role = role;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("UserRole [");
    if (id != null) {
      builder.append("id=");
      builder.append(id);
      builder.append(", ");
    }
   /* if (user != null) {
      builder.append("user=");
      builder.append(user);
      builder.append(", ");
    }*/
    if (role != null) {
      builder.append("role=");
      builder.append(role);
    }
    builder.append("]");
    return builder.toString();
  }

  
}
