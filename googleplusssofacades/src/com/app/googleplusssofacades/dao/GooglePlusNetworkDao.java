package com.app.googleplusssofacades.dao;

import de.hybris.platform.core.model.user.CustomerModel;


/**
 * The Interface SocialNetworkDao.
 */
public interface GooglePlusNetworkDao
{

	/**
	 * Gets the customer linked to google account.
	 *
	 * @param googleEmailId the google email id
	 * @return the customer linked to google account
	 */
	CustomerModel getCustomerLinkedToGoogleAccount(String googleEmailId);

	/**
	 * Adds the google attributes in customer model.
	 *
	 * @param customerModel the customer model
	 * @param googlePlusEmailId the google plus email id
	 */
	void addGoogleAttributesInCustomerModel(CustomerModel customerModel, String googlePlusEmailId);

}
