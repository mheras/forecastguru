package com.forecastGuru.web.security.ssl;

import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.IRequestTarget;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.protocol.http.request.urlcompressing.UrlCompressingWebRequestProcessor;
import org.apache.wicket.request.target.component.IBookmarkablePageRequestTarget;
import org.apache.wicket.request.target.component.IPageRequestTarget;

import com.forecastGuru.web.CustomApplication;

/**
 * Wicket request processor which is SSL aware. It determines whether the
 * requested page is annotated with @{@link SSLSecured} or not, and switches to
 * https if necessary.
 * 
 * @author Martin A. Heras
 * 
 */
public class SSLAwareWebRequestProcessor extends UrlCompressingWebRequestProcessor {

	private CustomApplication application;

	/**
	 * @param application
	 *            the custom application, which knows about non-SSL and SSL
	 *            ports.
	 */
	public SSLAwareWebRequestProcessor(CustomApplication application) {
		this.application = application;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.wicket.request.AbstractRequestCycleProcessor#respond(org.apache
	 * .wicket.RequestCycle)
	 */
	@Override
	public void respond(RequestCycle requestCycle) {
		IRequestTarget requestTarget = requestCycle.getRequestTarget();
		if (requestTarget != null) {
			this.application.logResponseTarget(requestTarget);
			WebRequest webRequest = (WebRequest) requestCycle.getRequest();
			WebResponse webResponse = (WebResponse) requestCycle.getResponse();
			HttpServletRequest httpServletRequest = webRequest.getHttpServletRequest();

			// Get the page class, in order to check whether is annotated with
			// @SSLSecured or not.
			Class<?> pageClass = null;
			if (requestTarget instanceof IPageRequestTarget) {
				IPageRequestTarget pageTarget = (IPageRequestTarget) requestTarget;
				pageClass = pageTarget.getPage().getClass();
			} else if (requestTarget instanceof IBookmarkablePageRequestTarget) {
				IBookmarkablePageRequestTarget bookmarkableTarget = (IBookmarkablePageRequestTarget) requestTarget;
				pageClass = bookmarkableTarget.getPageClass();
			}

			if (pageClass != null && !httpServletRequest.isSecure() && pageClass.isAnnotationPresent(SSLSecured.class)) {
				// We should switch to https.
				String url = RequestUtils.toAbsolutePath(requestCycle.urlFor(requestTarget).toString());
				url = url.replace("http", "https");
				url = url.replace(this.application.getNonSSLPort(), this.application.getSSLPort());
				webResponse.redirect(url);
			} else if (pageClass != null && httpServletRequest.isSecure()
					&& !pageClass.isAnnotationPresent(SSLSecured.class)) {
				// We should switch to http.
				String url = RequestUtils.toAbsolutePath(requestCycle.urlFor(requestTarget).toString());
				url = url.replace("https", "http");
				url = url.replace(this.application.getSSLPort(), this.application.getNonSSLPort());
				webResponse.redirect(url);
			} else {
				// The default response.
				requestTarget.respond(requestCycle);
			}
		}
	}
}
