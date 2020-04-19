/**
 *
 */
package com.app.googleplusssoaddon.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.security.GUIDCookieStrategy;
import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Controller;

/**
 * The Class SocialNetworkAddonPageController.
 *
 * @author vigneswaramoorthy.n
 */

@Controller
public class SocialNetworkAddonPageController extends AbstractAddOnPageController
{

	/** The Constant REDIRECT_TO_REGISTER. */
	public static final String REDIRECT_TO_REGISTER = "/register";

	/** The Constant REDIRECT_TO_LOGIN. */
	public static final String REDIRECT_TO_LOGIN = "/login";

	/** The Constant REDIRECT_HOME. */
	private static final String REDIRECT_HOME = "/";

	/** The logger. */
	final Logger LOGGER = LoggerFactory.getLogger(SocialNetworkAddonPageController.class);

	/** The guid cookie strategy. */
	@Resource(name = "guidCookieStrategy")
	private GUIDCookieStrategy guidCookieStrategy;

	/** The remember me services. */
	@Resource(name = "rememberMeServices")
	private RememberMeServices rememberMeServices;

	/**
	 * Gets the remember me services.
	 *
	 * @return the remember me services
	 */
	public RememberMeServices getRememberMeServices()
	{
		return rememberMeServices;
	}

	/**
	 * Sets the remember me services.
	 *
	 * @param rememberMeServices the new remember me services
	 */
	public void setRememberMeServices(final RememberMeServices rememberMeServices)
	{
		this.rememberMeServices = rememberMeServices;
	}

	/**
	 * Gets the redirect to register page.
	 *
	 * @return the redirect to register page
	 */
	protected String getRedirectToRegisterPage()
	{
		return REDIRECT_PREFIX + REDIRECT_TO_REGISTER;
	}

	/**
	 * Gets the redirect to home page.
	 *
	 * @return the redirect to home page
	 */
	protected String getRedirectToHomePage()
	{
		return REDIRECT_PREFIX + REDIRECT_HOME;
	}

	/**
	 * Gets the redirect to login page.
	 *
	 * @return the redirect to login page
	 */
	protected String getRedirectToLoginPage()
	{
		return REDIRECT_PREFIX + REDIRECT_TO_LOGIN;
	}


	/**
	 * Gets the guid cookie strategy.
	 *
	 * @return the guid cookie strategy
	 */
	public GUIDCookieStrategy getGuidCookieStrategy()
	{
		return guidCookieStrategy;
	}

	/**
	 * Sets the guid cookie strategy.
	 *
	 * @param guidCookieStrategy the new guid cookie strategy
	 */
	public void setGuidCookieStrategy(final GUIDCookieStrategy guidCookieStrategy)
	{
		this.guidCookieStrategy = guidCookieStrategy;
	}


}
