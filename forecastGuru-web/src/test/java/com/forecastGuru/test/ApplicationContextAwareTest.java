package com.forecastGuru.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Base class for any test which needs to be aware of the application context.
 * 
 * @author Martin A. Heras
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/WEB-INF/applicationContext*.xml" })
public abstract class ApplicationContextAwareTest {
	protected Log log = LogFactory.getLog(getClass());
}