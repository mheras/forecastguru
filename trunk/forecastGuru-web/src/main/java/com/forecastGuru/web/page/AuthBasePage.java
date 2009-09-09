package com.forecastGuru.web.page;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;

import com.forecastGuru.web.component.HeaderPanel;

/**
 * All pages which need the user to be authenticated should extend this page.
 * 
 * @author Martin A. Heras
 * 
 */
@AuthorizeInstantiation(value = { "General" })
public abstract class AuthBasePage extends BasePage {

	public AuthBasePage() {
		add(new HeaderPanel("pageHeader"));
	}
}
