package com.forecastGuru.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Class which holds all user related information.
 * 
 * @author Martin A. Heras
 * 
 */
@Entity
@Table(name = "BaseUser")
public class BaseUser extends BaseEntity {

	private static final long serialVersionUID = -5689321683294507888L;

	@Column(name = "userName", unique = true, nullable = false)
	private String userName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "firstName", nullable = false)
	private String firstName;

	@Column(name = "lastName", nullable = false)
	private String lastName;

	@Column(name = "birthday")
	private Date birthday;

	@ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "User_Role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Collection<Role> roles;

	/**
	 * Creates a new user.
	 * 
	 * @param userName
	 *            the username.
	 * @param password
	 *            the password.
	 * @param firstName
	 *            the first name.
	 * @param lastName
	 *            the last name.
	 */
	public BaseUser(String userName, String password, String firstName, String lastName) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Default constructor needed by Hibernate.
	 */
	@SuppressWarnings("unused")
	private BaseUser() {
	}

	/**
	 * Gets the first name.
	 * 
	 * @return the first name.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 * 
	 * @param firstName
	 *            the first name.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 * 
	 * @return the last name.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 * 
	 * @param lastName
	 *            the last name.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the birthday.
	 * 
	 * @return the birthday.
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * Sets the birthday.
	 * 
	 * @param birthday
	 *            the birthday.
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * Gets the first name and last name concatenated.
	 * 
	 * @return the first name and last name concatenated.
	 */
	public String getFullName() {
		return firstName + ' ' + lastName;
	}

	/**
	 * Gets the username.
	 * 
	 * @return the username.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the username.
	 * 
	 * @param userName
	 *            the username.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets a collection with the roles the user has.
	 * 
	 * @return a collection of roles.
	 */
	public Collection<Role> getRoles() {
		return roles;
	}
}
