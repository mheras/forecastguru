package com.forecastGuru.web.view;

import com.forecastGuru.web.page.SignInPage;

/**
 * View for {@link SignInPage}.
 * 
 * @author Martin A. Heras
 * 
 */
public interface ISignInView {

	/**
	 * Gets the username.
	 * 
	 * @return the username.
	 */
	public String getUserName();

	/**
	 * Gets the password.
	 * 
	 * @return the password.
	 */
	public String getPassword();
}
