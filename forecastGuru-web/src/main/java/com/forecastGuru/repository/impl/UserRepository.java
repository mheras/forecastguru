package com.forecastGuru.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.forecastGuru.model.BaseUser;
import com.forecastGuru.repository.IUserRepository;

/**
 * This class interacts with the data layer to save and retrieve User objects.
 * 
 * @author Martin A. Heras
 */
@Repository(value = "userRepository")
public class UserRepository extends HibernateDaoSupport implements IUserRepository {

	/**
	 * Creates a new user repository.
	 * 
	 * @param sessionFactory
	 *            the session factory, which is autowired by Spring.
	 */
	@Autowired
	public UserRepository(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.appfuse.dao.UserRepository#getUsers()
	 */
	@SuppressWarnings("unchecked")
	public List<BaseUser> getUsers() {
		return getHibernateTemplate().find("from BaseUser");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.appfuse.dao.UserRepository#getUser(long)
	 */
	public BaseUser getUser(long id) {
		return (BaseUser) getHibernateTemplate().get(BaseUser.class, id);
	}

	/* (non-Javadoc)
	 * @see com.forecastGuru.repository.IUserRepository#getUserByUserName(java.lang.String)
	 */
	public BaseUser getUserByUserName(String userName) {
		return (BaseUser) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.find("from BaseUser where userName = ?", userName));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.appfuse.dao.UserRepository#saveUser(org.appfuse.model.User)
	 */
	public void saveUser(BaseUser user) {
		getHibernateTemplate().saveOrUpdate(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.appfuse.dao.UserRepository#removeUser(long)
	 */
	public void removeUser(long id) {
		getHibernateTemplate().delete(getUser(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.appfuse.dao.UserRepository#removeUser(org.appfuse.model.User)
	 */
	public void removeUser(BaseUser user) {
		getHibernateTemplate().delete(user);
	}
}
