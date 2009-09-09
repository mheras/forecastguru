package com.forecastGuru.web.page;

import javax.servlet.http.Cookie;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.forecastGuru.presenter.ISignInPresenter;
import com.forecastGuru.web.security.ssl.SSLSecured;
import com.forecastGuru.web.view.ISignInView;

/**
 * Login page where the user is authenticated.
 * 
 * @author Martin A. Heras
 * 
 */
@SSLSecured
public class SignInPage extends WebPage implements ISignInView {

	private static final String USERNAME_COOKIE_NAME = "userName";

	@SpringBean
	private ISignInPresenter loginPresenter;

	private String userName;
	private String password;
	private boolean rememberMe;

	/**
	 * Creates a new sign in page.
	 */
	public SignInPage() {
		super();
		// Get username from the cookie (if exists).
		retrieveUserNameCookie();
		add(new SignInForm("signInForm"));
	}

	/**
	 * Login form with the username and password fields already initialized.
	 * 
	 * @author Martin A. Heras
	 * 
	 */
	private class SignInForm extends StatelessForm {

		private static final long serialVersionUID = -1336441648598050517L;

		/**
		 * Creates a new login form, with the username and password fields
		 * already initialized.
		 * 
		 * @param id
		 *            the wicket id for this form.
		 */
		public SignInForm(String id) {
			super(id);
			add(new TextField("userName", new PropertyModel(SignInPage.this, "userName")));
			add(new PasswordTextField("password", new PropertyModel(SignInPage.this, "password")));
			add(new CheckBox("rememberMe", new PropertyModel(SignInPage.this, "rememberMe")));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.apache.wicket.markup.html.form.Form#onSubmit()
		 */
		@Override
		protected void onSubmit() {
			super.onSubmit();

			// Save username in a cookie, if necessary.
			SignInPage.this.saveUserNameCookie();

			// Authenticate user credentials.
			if (loginPresenter.signIn(SignInPage.this)) {
				// Check whether a request to a page was intercepted. If so,
				// redirect to that page, otherwise redirect to the home page.
				if (!this.continueToOriginalDestination()) {
					setResponsePage(this.getApplication().getHomePage());
				}
			}
		}
	}

	/**
	 * Retrieves the cookie (if exists) to fill the username field and check the
	 * remember me checkbox.
	 */
	private void retrieveUserNameCookie() {
		WebRequest request = (WebRequest) getRequestCycle().getRequest();
		Cookie userNameCookie = request.getCookie(USERNAME_COOKIE_NAME);
		if (userNameCookie != null && userNameCookie.getValue() != null
				&& !userNameCookie.getValue().equals(StringUtils.EMPTY)) {
			rememberMe = true;
			userName = userNameCookie.getValue();
		}
	}

	/**
	 * Saves the cookie.
	 */
	private void saveUserNameCookie() {
		WebResponse response = (WebResponse) getRequestCycle().getResponse();
		Cookie userNameCookie = new Cookie(USERNAME_COOKIE_NAME, rememberMe ? userName : StringUtils.EMPTY);
		userNameCookie.setMaxAge(Integer.MAX_VALUE);
		userNameCookie.setPath("/");
		response.addCookie(userNameCookie);
	}

	/* (non-Javadoc)
	 * @see com.forecastGuru.web.view.ISignInView#getPassword()
	 */
	public String getPassword() {
		return this.password;
	}

	/* (non-Javadoc)
	 * @see com.forecastGuru.web.view.ISignInView#getUserName()
	 */
	public String getUserName() {
		return this.userName;
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
	 * Sets the password.
	 * 
	 * @param password
	 *            the password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
