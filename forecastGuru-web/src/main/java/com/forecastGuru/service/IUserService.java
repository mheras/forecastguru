package com.forecastGuru.service;

import java.util.List;

import com.forecastGuru.model.BaseUser;

/**
 * Object which exposes all user related services.
 * 
 * @author Martin A. Heras
 * 
 */
public interface IUserService extends IService {

	/**
	 * Authenticates the user, by giving its username and password.
	 * 
	 * @param userName
	 *            the username.
	 * @param password
	 *            the password.
	 * @return the user if authentication succeded, otherwise <code>null</code>.
	 */
	public BaseUser authenticateUser(String userName, String password);

	/**
	 * Gets all the existing users.
	 * 
	 * @return a list with all the users.
	 */
	public List<BaseUser> getUsers();

	/**
	 * Gets the user by giving its id.
	 * 
	 * @param userId
	 *            the user id.
	 * @return the user, or <code>null</code> if it does not exist.
	 */
	public BaseUser getUser(String userId);

	/**
	 * Gets the user by giving its username.
	 * 
	 * @param userName
	 *            the username.
	 * @return the user, or <code>null</code> if it does not exist.
	 */
	public BaseUser getUserByUserName(String userName);

	/**
	 * Saves the user.
	 * 
	 * @param user
	 *            the user to be saved.
	 */
	public void saveUser(BaseUser user);

	/**
	 * Removes the user by giving its id.
	 * 
	 * @param userId
	 *            the id of the user to be deleted.
	 */
	public void removeUser(String userId);
}
