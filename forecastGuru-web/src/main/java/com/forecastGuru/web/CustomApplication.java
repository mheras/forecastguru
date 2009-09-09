package com.forecastGuru.web;

import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.IRequestCycleProcessor;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.forecastGuru.web.page.SignInPage;
import com.forecastGuru.web.page.WelcomePage;
import com.forecastGuru.web.security.ssl.SSLAwareWebRequestProcessor;

/**
 * Custom application which performs Spring integration, bookmarkable pages
 * mounting, and so...
 * 
 * @author Martin A. Heras
 * 
 */
public class CustomApplication extends AuthenticatedWebApplication {

	private String nonSSLPort = "8080";
	private String SSLPort = "8443";

	@Override
	public void init() {
		super.init();

		// Let Wicket know about Spring.
		addComponentInstantiationListener(new SpringComponentInjector(this));
		configure();

		// Remove <wicket:> tags in generated markup.
		getMarkupSettings().setStripWicketTags(true);

		// Make bookmarkable pages for easy linking from the menu.
		mountBookmarkablePage("/signIn", SignInPage.class);
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return SignInPage.class;
	}

	@Override
	protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
		return CustomSession.class;
	}

	@Override
	public Class<? extends WebPage> getHomePage() {
		return WelcomePage.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.wicket.protocol.http.WebApplication#newRequestCycleProcessor()
	 */
	@Override
	protected IRequestCycleProcessor newRequestCycleProcessor() {
		return new SSLAwareWebRequestProcessor(this);
	}

	/**
	 * Gets the non-SSL port.
	 * 
	 * @return the non-SSL port.
	 */
	public String getNonSSLPort() {
		return nonSSLPort;
	}

	/**
	 * Sets the non-SSL port.
	 * 
	 * @param nonSSLPort
	 *            the non-SSL port.
	 */
	public void setNonSSLPort(String nonSSLPort) {
		this.nonSSLPort = nonSSLPort;
	}

	/**
	 * Gets the SSL port.
	 * 
	 * @return the SSL port.
	 */
	public String getSSLPort() {
		return SSLPort;
	}

	/**
	 * Sets the SSL port.
	 * 
	 * @param SSLPort
	 *            the SSL port.
	 */
	public void setSSLPort(String SSLPort) {
		this.SSLPort = SSLPort;
	}
}