package com.app.googleplusssofacades.dao.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.app.googleplusssofacades.dao.GooglePlusNetworkDao;


/**
 * The Class DefaultSocialNetworkDao.
 */
public class DefaultGooglePlusNetworkDao implements GooglePlusNetworkDao
{

	/** The flexible search service. */
	@Resource(name = "flexibleSearchService")
	private FlexibleSearchService flexibleSearchService;

	/** The model service. */
	@Resource(name = "modelService")
	private ModelService modelService;


	/**
	 *  (non-Javadoc)
	 */
	@Override
	public CustomerModel getCustomerLinkedToGoogleAccount(final String googlePlusUserId)
	{
		final String query = "SELECT {" + CustomerModel.PK + "} FROM {" + CustomerModel._TYPECODE + "} WHERE {"
				+ CustomerModel.GOOGLEPLUSUSERID + "}=?googlePlusUserId";
		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(query);
		fQuery.addQueryParameter("googlePlusUserId", googlePlusUserId);
		final SearchResult<CustomerModel> result = flexibleSearchService.search(fQuery);
		if (CollectionUtils.isNotEmpty(result.getResult()))
		{
			return result.getResult().get(0);
		}
		return null;
	}


	/**
	 *  (non-Javadoc)
	 */
	@Override
	public void addGoogleAttributesInCustomerModel(final CustomerModel customerModel, final String googlePlusUserId)
	{
		customerModel.setGooglePlusUserId(googlePlusUserId);
		modelService.save(customerModel);
	}

}
