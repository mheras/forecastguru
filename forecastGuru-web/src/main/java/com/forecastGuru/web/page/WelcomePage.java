package com.forecastGuru.web.page;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;

/**
 * Page which displays a welcome message.
 * 
 * @author Martin A. Heras
 * 
 */
public class WelcomePage extends AuthBasePage {

	public WelcomePage() {
		super();
		add(new Label("fullName", new PropertyModel(this, "session.user.fullName")));
	}
}
