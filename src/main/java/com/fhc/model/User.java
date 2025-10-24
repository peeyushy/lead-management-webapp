package com.fhc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fhc.constants.AppConstants;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String fullname;

	private String username;

	private String password;

	private String email;

	private String contactno;

	private String notificationtype;

	private boolean active = true;

	private AppConstants.RoleNames role;

	private Date createdat;

	private Date updatedat;

	private String createdby;

	private String lastupdatedby;

	private Long teamleaderid;

	private Set<GrantedAuthority> authorities = new HashSet<>();

	public User() {
		super();
	}	

	public User(Long id) {
		super();
		this.id = id;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the contactno
	 */
	public String getContactno() {
		return contactno;
	}

	/**
	 * @param contactno the contactno to set
	 */
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	/**
	 * @return the notificationtype
	 */
	public String getNotificationtype() {
		return notificationtype;
	}

	/**
	 * @param notificationtype the notificationtype to set
	 */
	public void setNotificationtype(String notificationtype) {
		this.notificationtype = notificationtype;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the role
	 */
	public AppConstants.RoleNames getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(AppConstants.RoleNames role) {
		this.role = role;
	}

	/**
	 * @return the createdat
	 */
	public Date getCreatedat() {
		return createdat;
	}

	/**
	 * @param createdat the createdat to set
	 */
	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}

	/**
	 * @return the updatedat
	 */
	public Date getUpdatedat() {
		return updatedat;
	}

	/**
	 * @param updatedat the updatedat to set
	 */
	public void setUpdatedat(Date updatedat) {
		this.updatedat = updatedat;
	}

	/**
	 * @return the createdby
	 */
	public String getCreatedby() {
		return createdby;
	}

	/**
	 * @param createdby the createdby to set
	 */
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	/**
	 * @return the lastupdatedby
	 */
	public String getLastupdatedby() {
		return lastupdatedby;
	}

	/**
	 * @param lastupdatedby the lastupdatedby to set
	 */
	public void setLastupdatedby(String lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	/**
	 * @return the teamleaderid
	 */
	public Long getTeamleaderid() {
		return teamleaderid;
	}

	/**
	 * @param teamleaderid the teamleaderid to set
	 */
	public void setTeamleaderid(Long teamleaderid) {
		this.teamleaderid = teamleaderid;
	}

	/**
	 * @return the authorities
	 */
	public Set<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result + ((contactno == null) ? 0 : contactno.hashCode());
		result = prime * result + ((createdat == null) ? 0 : createdat.hashCode());
		result = prime * result + ((createdby == null) ? 0 : createdby.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + ((lastupdatedby == null) ? 0 : lastupdatedby.hashCode());
		result = prime * result + ((notificationtype == null) ? 0 : notificationtype.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((teamleaderid == null) ? 0 : teamleaderid.hashCode());
		result = prime * result + ((updatedat == null) ? 0 : updatedat.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		User other = (User) obj;
		if (active != other.active)
			return false;
		if (authorities == null) {
			if (other.authorities != null)
				return false;
		} else if (!authorities.equals(other.authorities))
			return false;
		if (contactno == null) {
			if (other.contactno != null)
				return false;
		} else if (!contactno.equals(other.contactno))
			return false;
		if (createdat == null) {
			if (other.createdat != null)
				return false;
		} else if (!createdat.equals(other.createdat))
			return false;
		if (createdby == null) {
			if (other.createdby != null)
				return false;
		} else if (!createdby.equals(other.createdby))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (lastupdatedby == null) {
			if (other.lastupdatedby != null)
				return false;
		} else if (!lastupdatedby.equals(other.lastupdatedby))
			return false;
		if (notificationtype == null) {
			if (other.notificationtype != null)
				return false;
		} else if (!notificationtype.equals(other.notificationtype))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		if (teamleaderid == null) {
			if (other.teamleaderid != null)
				return false;
		} else if (!teamleaderid.equals(other.teamleaderid))
			return false;
		if (updatedat == null) {
			if (other.updatedat != null)
				return false;
		} else if (!updatedat.equals(other.updatedat))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", fullname=" + fullname + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", contactno=" + contactno + ", notificationtype=" + notificationtype
				+ ", active=" + active + ", role=" + role + ", createdat=" + createdat + ", updatedat=" + updatedat
				+ ", createdby=" + createdby + ", lastupdatedby=" + lastupdatedby + ", teamleaderid=" + teamleaderid
				+ ", authorities=" + authorities + "]";
	}
}
