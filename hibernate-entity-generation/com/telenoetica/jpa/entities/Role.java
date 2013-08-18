package com.telenoetica.jpa.entities;



import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Role 
 */
@Entity
@Table(name = "role", catalog = "field_service_db")
public class Role implements java.io.Serializable {

	private Long id;
	private String name;
	private Set rolePermissions = new HashSet(0);
	private Set userRoles = new HashSet(0);

	public Role() {
	}

	public Role(String name, Set rolePermissions, Set userRoles) {
		this.name = name;
		this.rolePermissions = rolePermissions;
		this.userRoles = userRoles;
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

	@Column(name = "name", length = 250)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	public Set getRolePermissions() {
		return this.rolePermissions;
	}

	public void setRolePermissions(Set rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	public Set getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set userRoles) {
		this.userRoles = userRoles;
	}

}
