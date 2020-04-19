package com.app.googleplusssofacades.impl;

import de.hybris.platform.core.model.user.CustomerModel;

import javax.annotation.Resource;

import com.app.googleplusssofacades.GoogleLoginFacade;
import com.app.googleplusssofacades.data.SocialCustomerData;
import com.app.googleplusssofacades.service.GoogleLoginService;


/**
 * The Class DefaultGoogleLoginFacade.
 */
public class DefaultGoogleLoginFacade implements GoogleLoginFacade
{

	/** The google login service. */
	@Resource(name = "googleLoginService")
	private GoogleLoginService googleLoginService;

	/* (non-Javadoc)
	 */
	@Override
	public String getGoogleLoginProvider()
	{
		return googleLoginService.getGoogleLoginProvider();
	}

	/* (non-Javadoc)
	 */
	@Override
	public SocialCustomerData getGoogleLoginSuccessCallback(final String code)
	{
		return googleLoginService.getGoogleLoginSuccessCallback(code);
	}

	/* (non-Javadoc)
	 */
	@Override
	public void getGoogleLoginErrorCallback(final String errorReason)
	{
		googleLoginService.getGoogleLoginErrorCallback(errorReason);
	}

	/* (non-Javadoc)
	 */
	@Override
	public void addGoogleAttributesInCustomerModel(final String googlePlusUserId)
	{
		googleLoginService.addGoogleAttributesInCustomerModel(googlePlusUserId);
	}

	/* (non-Javadoc)
	 */
	@Override
	public CustomerModel getCustomerLinkedToGoogleAccount(final String googlePlusUserId)
	{
		return googleLoginService.getCustomerLinkedToGoogleAccount(googlePlusUserId);
	}

}
