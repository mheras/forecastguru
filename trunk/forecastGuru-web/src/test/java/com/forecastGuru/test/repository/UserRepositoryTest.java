package com.forecastGuru.test.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.forecastGuru.model.BaseUser;
import com.forecastGuru.repository.IUserRepository;
import com.forecastGuru.test.TransactionalApplicationContextAwareTest;

/**
 * Unit test for {@link IUserRepository}.
 * 
 * @author Martin A. Heras
 * 
 */
public class UserRepositoryTest extends TransactionalApplicationContextAwareTest {

	private BaseUser user = null;

	@Autowired
	private IUserRepository userRepository = null;

	/**
	 * Gets the users.
	 */
	@Test
	public void getUsers() {
		user = new BaseUser("rjohnson", "123456", "Rod", "Johnson");
		userRepository.saveUser(user);
		Assert.assertTrue(userRepository.getUsers().size() >= 1);
	}

	/**
	 * Saves a user.
	 * 
	 * @throws Exception
	 *             if an error occurs.
	 */
	@Test
	public void saveUser() throws Exception {
		user = new BaseUser("rjohnson", "123456", "Rod", "Johnson");
		userRepository.saveUser(user);
		Assert.assertTrue("primary key assigned", user.getId() != 0);
		Assert.assertNotNull(user.getFirstName());
	}
}
