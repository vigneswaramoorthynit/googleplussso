package com.app.googleplusssofacades.util;

import de.hybris.platform.servicelayer.session.SessionService;

import javax.annotation.Resource;


/**
 * The Class SocialNetworkUtil.
 */
public class GooglePlusNetworkUtil
{

	/** The session service. */
	@Resource(name = "sessionService")
	private SessionService sessionService;

	/**
	 * Initialize google attribute.
	 *
	 * @param googlePlusUserId the google plus user id
	 */
	public void initializeGoogleAttribute(final String googlePlusUserId)
	{
		sessionService.setAttribute("googlePlusUserId", googlePlusUserId);
	}



	/**
	 * Removes the google attributes.
	 */
	public void removeGoogleAttributes()
	{
		if (isGoogleAttributesAvailable())
		{
			sessionService.removeAttribute("googlePlusUserId");
		}
	}


	/**
	 * Checks if is google attributes available.
	 *
	 * @return true, if is google attributes available
	 */
	public boolean isGoogleAttributesAvailable()
	{
		return null != sessionService.getAttribute("googlePlusUserId");
	}


	/**
	 * Gets the google plus user id.
	 *
	 * @return the google plus user id
	 */
	public String getGooglePlusUserId()
	{

		return sessionService.getAttribute("googlePlusUserId");
	}
}
