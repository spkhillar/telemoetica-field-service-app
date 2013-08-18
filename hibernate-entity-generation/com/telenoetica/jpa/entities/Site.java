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
 * Site 
 */
@Entity
@Table(name = "site", catalog = "field_service_db")
public class Site implements java.io.Serializable {

	private Long id;
	private Integer version;
	private String name;
	private Date createdAt;
	private Set routineVisits = new HashSet(0);
	private Set callOutVisits = new HashSet(0);
	private Set maintenanceVisits = new HashSet(0);
	private Set dieselVisits = new HashSet(0);

	public Site() {
	}

	public Site(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Site(String name, Date createdAt, Set routineVisits,
			Set callOutVisits, Set maintenanceVisits, Set dieselVisits) {
		this.name = name;
		this.createdAt = createdAt;
		this.routineVisits = routineVisits;
		this.callOutVisits = callOutVisits;
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

	@Column(name = "name", length = 250)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, length = 19)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "site")
	public Set getRoutineVisits() {
		return this.routineVisits;
	}

	public void setRoutineVisits(Set routineVisits) {
		this.routineVisits = routineVisits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "site")
	public Set getCallOutVisits() {
		return this.callOutVisits;
	}

	public void setCallOutVisits(Set callOutVisits) {
		this.callOutVisits = callOutVisits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "site")
	public Set getMaintenanceVisits() {
		return this.maintenanceVisits;
	}

	public void setMaintenanceVisits(Set maintenanceVisits) {
		this.maintenanceVisits = maintenanceVisits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "site")
	public Set getDieselVisits() {
		return this.dieselVisits;
	}

	public void setDieselVisits(Set dieselVisits) {
		this.dieselVisits = dieselVisits;
	}

}
