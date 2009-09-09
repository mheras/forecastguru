package com.forecastGuru.web.page;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.markup.html.WebPage;

/**
 * All pages in the application should extend this base page, which ensures that
 * a header and a footer are included, as well as other common functionalities
 * among all pages in the application.
 * 
 * @author Martin A. Heras
 * 
 */
public abstract class BasePage extends WebPage {
	transient protected Log log = LogFactory.getLog(getClass());
}
