/* Copyright (C) 2013 Telenoetica, Inc. All rights reserved */
package com.telenoetica.jpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Permission.
 *
 * @author  Shiv Prasad Khillar
 */
@Entity
@Table(name = "permission")
public class Permission implements java.io.Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -3939120811223951025L;
  
  /** The id. */
  private Long id;
  
  /** The name. */
  private String name;
  
  /** The role permissions. */
  private Set<RolePermission> rolePermissions = new HashSet<RolePermission>(0);

  /**
   * Instantiates a new permission.
   */
  public Permission() {}

  /**
   * Instantiates a new permission.
   *
   * @param name the name
   * @param rolePermissions the role permissions
   */
  public Permission(String name, Set<RolePermission> rolePermissions) {
    this.name = name;
    this.rolePermissions = rolePermissions;
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
   * Gets the role permissions.
   *
   * @return the role permissions
   */
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "permission")
  public Set<RolePermission> getRolePermissions() {
    return this.rolePermissions;
  }

  /**
   * Sets the role permissions.
   *
   * @param rolePermissions the new role permissions
   */
  public void setRolePermissions(Set<RolePermission> rolePermissions) {
    this.rolePermissions = rolePermissions;
  }

}
