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
 * RolePermission.
 *
 * @author  Shiv Prasad Khillar
 */
@Entity
@Table(name = "role_permission")
public class RolePermission implements java.io.Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 7549364120469444471L;
  
  /** The id. */
  private Long id;
  
  /** The permission. */
  private Permission permission;
  
  /** The role. */
  private Role role;

  /**
   * Instantiates a new role permission.
   */
  public RolePermission() {}

  /**
   * Instantiates a new role permission.
   *
   * @param permission the permission
   * @param role the role
   */
  public RolePermission(Permission permission, Role role) {
    this.permission = permission;
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
   * Gets the permission.
   *
   * @return the permission
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "permission_id")
  public Permission getPermission() {
    return this.permission;
  }

  /**
   * Sets the permission.
   *
   * @param permission the new permission
   */
  public void setPermission(Permission permission) {
    this.permission = permission;
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

}
