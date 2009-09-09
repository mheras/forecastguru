package com.forecastGuru.web.security.ssl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotations tells Wicket that the page being annotated should be
 * processed in SSL mode. The {@link SSLAwareWebRequestProcessor} checks whether
 * the page has been annotated with this annotation. If so, the request is
 * switched to https.
 * 
 * @author Martin A. Heras
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE })
@Inherited
public @interface SSLSecured {
}