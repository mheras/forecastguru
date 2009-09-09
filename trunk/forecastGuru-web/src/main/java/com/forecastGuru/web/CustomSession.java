package com.forecastGuru.web;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.forecastGuru.model.BaseUser;
import com.forecastGuru.model.Role;
import com.forecastGuru.service.IUserService;

/**
 * Custom session for the application. It adds authentication functionality,
 * among other things.
 * 
 * @author Martin A. Heras
 * 
 */
public class CustomSession extends AuthenticatedWebSession {

	private static final long serialVersionUID = -8565998559153778111L;

	@SpringBean
	private IUserService userService;
	private BaseUser user;

	/**
	 * Creates a new session.
	 * 
	 * @param request
	 *            the request.
	 */
	public CustomSession(Request request) {
		super(request);
		// This will make @SpringBean work here at this early phase.
		InjectorHolder.getInjector().inject(this);
	}

	/**
	 * Gets the current session if it exists, or creates a new one.
	 * 
	 * @return the current session if it exists, or a new one.
	 */
	public static CustomSession get() {
		return (CustomSession) Session.get();
	}

	/**
	 * Gets whether a user is authenticated or not.
	 * 
	 * @return <code>true</code> if a user is authenticated, otherwise
	 *         <code>false</code>.
	 */
	public boolean isAuthenticated() {
		return user != null;
	}

	/**
	 * Gets the user currently in the session.
	 * 
	 * @return the user in the session, or <code>null</code> if there is no
	 *         user.
	 */
	public BaseUser getUser() {
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.wicket.authentication.AuthenticatedWebSession#authenticate
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public boolean authenticate(String username, String password) {
		this.user = this.userService.authenticateUser(username, password);
		return this.user != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.wicket.authentication.AuthenticatedWebSession#getRoles()
	 */
	@Override
	public Roles getRoles() {
		Roles roles = new Roles();
		if (isSignedIn()) {
			for (Role role : user.getRoles()) {
				roles.add(role.getName());
			}
			return roles;
		}
		return null;
	}
}
