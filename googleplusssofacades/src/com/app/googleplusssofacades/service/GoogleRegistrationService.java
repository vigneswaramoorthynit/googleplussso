package com.app.googleplusssofacades.service;

import com.app.googleplusssofacades.data.SocialCustomerData;



/**
 * The Interface GoogleRegistrationService.
 */
public interface GoogleRegistrationService
{

	/**
	 * Gets the google provider.
	 *
	 * @return the google provider
	 */
	String getGoogleProvider();

	/**
	 * Gets the google registration success callback.
	 *
	 * @param code the code
	 * @return the google registration success callback
	 */
	SocialCustomerData getGoogleRegistrationSuccessCallback(final String code);

	/**
	 * Gets the google registration error callback.
	 *
	 * @param errorReason the error reason
	 * @return the google registration error callback
	 */
	void getGoogleRegistrationErrorCallback(final String errorReason);

}
