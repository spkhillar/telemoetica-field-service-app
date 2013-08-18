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
 * Role.
 * 
 * @author Shiv Prasad Khillar
 */
@Entity
@Table(name = "role")
public class Role implements java.io.Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6457616256442358384L;

	/** The id. */
	private Long id;

	/** The name. */
	private String name;

	/** The role name. */
	private String roleName;

	/** The description. */
	private String description;

	/** The role permissions. */
	private Set<RolePermission> rolePermissions = new HashSet<RolePermission>(0);

	/** The user roles. */
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	/**
	 * Instantiates a new role.
	 */
	public Role() {
	}

	/**
	 * Instantiates a new role.
	 * 
	 * @param name
	 *            the name
	 * @param roleName
	 *            the role name
	 * @param rolePermissions
	 *            the role permissions
	 * @param userRoles
	 *            the user roles
	 */
	public Role(String name, String roleName,
			Set<RolePermission> rolePermissions, Set<UserRole> userRoles) {
		this.name = name;
		this.roleName = roleName;
		this.rolePermissions = rolePermissions;
		this.userRoles = userRoles;
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
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the role name.
	 * 
	 * @return the role name
	 */
	@Column(name = "role_name", length = 250)
	public String getRoleName() {
		return roleName;
	}

	/**
	 * Sets the role name.
	 * 
	 * @param roleName
	 *            the new role name
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the role permissions.
	 * 
	 * @return the role permissions
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	public Set<RolePermission> getRolePermissions() {
		return rolePermissions;
	}

	/**
	 * Sets the role permissions.
	 * 
	 * @param rolePermissions
	 *            the new role permissions
	 */
	public void setRolePermissions(Set<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	/**
	 * Gets the user roles.
	 * 
	 * @return the user roles
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	/**
	 * Sets the user roles.
	 * 
	 * @param userRoles
	 *            the new user roles
	 */
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
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
		builder.append("Role [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (roleName != null) {
			builder.append("roleName=");
			builder.append(roleName);
			builder.append(", ");
		}
		if (description != null) {
			builder.append("description=");
			builder.append(description);
		}
		builder.append("]");
		return builder.toString();
	}

}
