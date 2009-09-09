package com.forecastGuru.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Role class, which is very important at authorization over pages, components
 * and business logic.
 * 
 * @author Martin A. Heras
 * 
 */
@Entity
@Table(name = "Role")
public class Role extends BaseEntity {

	private static final long serialVersionUID = 4203952201296509044L;

	@Column(name = "name", nullable = false, unique = true)
	private String name;
	@Column(name = "description")
	private String description;
	@ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "roles", targetEntity = BaseUser.class)
	private Collection<BaseUser> users;

	/**
	 * Creates a new role.
	 * 
	 * @param name
	 *            the name of the role.
	 */
	public Role(String name) {
		this.name = name;
	}

	/**
	 * Default constructor needed by Hibernate.
	 */
	@SuppressWarnings("unused")
	private Role() {
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description
	 *            the description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the users which have this role.
	 * 
	 * @return a collection of users which have this role.
	 */
	public Collection<BaseUser> getUsers() {
		return users;
	}
}
