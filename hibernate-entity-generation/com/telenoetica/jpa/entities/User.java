package com.telenoetica.jpa.entities;



import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * User 
 */
@Entity
@Table(name = "user", catalog = "field_service_db")
public class User implements java.io.Serializable {

	private Long id;
	private Integer version;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Boolean enabled;
	private Date createdAt;
	private Set callOutVisits = new HashSet(0);
	private Set routineVisits = new HashSet(0);
	private Set userRoles = new HashSet(0);
	private Set maintenanceVisits = new HashSet(0);
	private Set dieselVisits = new HashSet(0);

	public User() {
	}

	public User(Date createdAt) {
		this.createdAt = createdAt;
	}

	public User(String username, String password, String firstName,
			String lastName, String email, Boolean enabled, Date createdAt,
			Set callOutVisits, Set routineVisits, Set userRoles,
			Set maintenanceVisits, Set dieselVisits) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.enabled = enabled;
		this.createdAt = createdAt;
		this.callOutVisits = callOutVisits;
		this.routineVisits = routineVisits;
		this.userRoles = userRoles;
		this.maintenanceVisits = maintenanceVisits;
		this.dieselVisits = dieselVisits;
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

	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "username", length = 250)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 250)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "first_name", length = 250)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", length = 250)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email", length = 250)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "enabled")
	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, length = 19)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set getCallOutVisits() {
		return this.callOutVisits;
	}

	public void setCallOutVisits(Set callOutVisits) {
		this.callOutVisits = callOutVisits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set getRoutineVisits() {
		return this.routineVisits;
	}

	public void setRoutineVisits(Set routineVisits) {
		this.routineVisits = routineVisits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set userRoles) {
		this.userRoles = userRoles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set getMaintenanceVisits() {
		return this.maintenanceVisits;
	}

	public void setMaintenanceVisits(Set maintenanceVisits) {
		this.maintenanceVisits = maintenanceVisits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set getDieselVisits() {
		return this.dieselVisits;
	}

	public void setDieselVisits(Set dieselVisits) {
		this.dieselVisits = dieselVisits;
	}

}
