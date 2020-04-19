package com.app.googleplusssofacades.impl;

import javax.annotation.Resource;

import com.app.googleplusssofacades.GoogleRegistrationFacade;
import com.app.googleplusssofacades.data.SocialCustomerData;
import com.app.googleplusssofacades.service.GoogleRegistrationService;

/**
 * The Class DefaultGoogleRegistrationFacade.
 *
 * This is the facade implementation for social media based registration.
 *
 * This is in particular built for GOOGLE+ based registration.
 */
public class DefaultGoogleRegistrationFacade implements GoogleRegistrationFacade
{

	/** The google registration service. */
	@Resource(name = "googleRegistrationService")
	private GoogleRegistrationService googleRegistrationService;

	/* (non-Javadoc)
	 */
	@Override
	public String getGoogleProvider()
	{
		return googleRegistrationService.getGoogleProvider();
	}

	/* (non-Javadoc)
	 */
	@Override
	public SocialCustomerData getGoogleRegistrationSuccessCallback(final String code)
	{
		return googleRegistrationService.getGoogleRegistrationSuccessCallback(code);
	}

	/* (non-Javadoc)
	 */
	@Override
	public void getGoogleRegistrationErrorCallback(final String errorReason)
	{
		googleRegistrationService.getGoogleRegistrationErrorCallback(errorReason);
	}

}
