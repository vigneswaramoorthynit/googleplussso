
package com.app.googleplusssofacades;

import de.hybris.platform.core.model.user.CustomerModel;

import com.app.googleplusssofacades.data.SocialCustomerData;



/**
 * The Interface GoogleLoginFacade.
 */
public interface GoogleLoginFacade
{

	/**
	 * Gets the google login provider.
	 *
	 * @return the google login provider
	 */
	String getGoogleLoginProvider();

	/**
	 * Gets the google login success callback.
	 *
	 * @param code the code
	 * @return the google login success callback
	 */
	SocialCustomerData getGoogleLoginSuccessCallback(final String code);

	/**
	 * Gets the google login error callback.
	 *
	 * @param errorReason the error reason
	 * @return the google login error callback
	 */
	void getGoogleLoginErrorCallback(final String errorReason);

	/**
	 * Adds the google attributes in customer model.
	 *
	 * @param googleEmailId the google email id
	 */
	void addGoogleAttributesInCustomerModel(String googleEmailId);

	/**
	 * Gets the customer linked to google account.
	 *
	 * @param googleEmailId the google email id
	 * @return the customer linked to google account
	 */
	CustomerModel getCustomerLinkedToGoogleAccount(String googleEmailId);

}
