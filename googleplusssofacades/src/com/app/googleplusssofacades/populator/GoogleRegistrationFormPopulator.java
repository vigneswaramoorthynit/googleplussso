package com.app.googleplusssofacades.populator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.springframework.social.google.api.plus.Person;

import com.app.googleplusssofacades.data.SocialCustomerData;

/**
 * The Class GoogleRegistrationFormPopulator.
 */
public class GoogleRegistrationFormPopulator implements Populator<Person, SocialCustomerData>
{

	/**
	 * (non-Javadoc)
	 */
	@Override
	public void populate(final Person source, final SocialCustomerData target) throws ConversionException
	{
		target.setGender(source.getGender());
		target.setEmail(source.getAccountEmail());
		target.setFirstName(source.getGivenName());
		target.setLastName(source.getFamilyName());
		target.setGoogleUserId(source.getId());
		target.setSocialNetworkProvider(com.app.googleplusssofacades.enums.Provider.GOOGLEPLUS.toString());
	}

}
