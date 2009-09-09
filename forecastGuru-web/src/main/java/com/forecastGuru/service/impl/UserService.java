package com.forecastGuru.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forecastGuru.model.BaseUser;
import com.forecastGuru.repository.IUserRepository;
import com.forecastGuru.service.IUserService;
import com.forecastGuru.util.CryptUtils;

/**
 * Implementation of {@link IUserService}.
 * 
 * @author Martin A. Heras
 * 
 */
@Service(value = "userService")
public class UserService implements IUserService {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	IUserRepository userRepository;

	/* (non-Javadoc)
	 * @see com.forecastGuru.service.IUserService#getUsers()
	 */
	public List<BaseUser> getUsers() {
		return userRepository.getUsers();
	}

	/* (non-Javadoc)
	 * @see com.forecastGuru.service.IUserService#getUser(java.lang.String)
	 */
	public BaseUser getUser(String userId) {
		return userRepository.getUser(Long.valueOf(userId));
	}

	/* (non-Javadoc)
	 * @see com.forecastGuru.service.IUserService#getUserByUserName(java.lang.String)
	 */
	public BaseUser getUserByUserName(String userName) {
		return userRepository.getUserByUserName(userName);
	}

	/* (non-Javadoc)
	 * @see com.forecastGuru.service.IUserService#saveUser(com.forecastGuru.model.BaseUser)
	 */
	public void saveUser(BaseUser user) {
		userRepository.saveUser(user);
	}

	/* (non-Javadoc)
	 * @see com.forecastGuru.service.IUserService#removeUser(java.lang.String)
	 */
	public void removeUser(String userId) {
		userRepository.removeUser(Long.valueOf(userId));
	}

	/* (non-Javadoc)
	 * @see com.forecastGuru.service.IUserService#authenticateUser(java.lang.String, java.lang.String)
	 */
	public BaseUser authenticateUser(String userName, String password) {
		BaseUser user = getUserByUserName(userName);
		try {
			if (user == null || !user.getPassword().equals(CryptUtils.calculateSHA1Hash(password))) {
				return null;
			}
		} catch (NoSuchAlgorithmException e) {
			log.error("No SHA1 algorithm is present. Cannot hash password to "
					+ "authenticate user. The user will not be authenticated!");
			return null;
		}
		return user;
	}
}
