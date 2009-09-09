package com.forecastGuru.repository;

import java.util.List;

import com.forecastGuru.model.BaseUser;

/**
 * This is the interface for user repository implementations, which interact
 * with the data layer to save and retrieve {@link BaseUser} objects.
 * 
 * @author Martin A. Heras
 */
public interface IUserRepository extends IRepository {

	/**
	 * Gets all the users in the repository.
	 * 
	 * @return a list containing all the users in the repository.
	 */
	public List<BaseUser> getUsers();

	/**
	 * Gets the user by giving its id.
	 * 
	 * @param userId
	 *            the user id.
	 * @return the user, or <code>null</code> if it does not exist.
	 */
	public BaseUser getUser(long userId);

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
	 *            the id for the user to be removed.
	 */
	public void removeUser(long userId);

	/**
	 * Removes the user.
	 * 
	 * @param user
	 *            the user to be removed.
	 */
	public void removeUser(BaseUser user);
}
