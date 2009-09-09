package com.forecastGuru.web.component;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import com.forecastGuru.web.page.AuthBasePage;

/**
 * Panel for the page header. All pages which extend {@link AuthBasePage} will
 * have this panel as a header.
 * 
 * @author Martin A. Heras
 * 
 */
public class HeaderPanel extends Panel {

	private static final long serialVersionUID = -4495440883439854886L;

	public HeaderPanel(String id) {
		super(id);
		this.setMarkupId("pageHeader");
		this.setOutputMarkupId(true);
		add(new Label("fullName", new PropertyModel(this, "session.user.fullName")));
	}
}
