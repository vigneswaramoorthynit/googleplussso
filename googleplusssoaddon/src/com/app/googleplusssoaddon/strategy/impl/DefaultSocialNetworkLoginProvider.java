package com.app.googleplusssoaddon.strategy.impl;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.jalo.JaloConnection;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.jalo.user.UserManager;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.spring.security.CoreAuthenticationProvider;
import de.hybris.platform.spring.security.CoreUserDetails;

import java.util.Collections;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.app.googleplusssofacades.dao.GooglePlusNetworkDao;
import com.app.googleplusssofacades.data.SocialCustomerData;



// TODO: Auto-generated Javadoc
/**
 * The Class DefaultSocialNetworkLoginProvider.
 */
public class DefaultSocialNetworkLoginProvider extends CoreAuthenticationProvider
{

	/**
	 *  The Constant LOG.
	 **/
	private static final Logger LOG = Logger.getLogger(DefaultSocialNetworkLoginProvider.class);

	/**
	 * The social network dao.
	 **/
	@Resource(name = "googlePlusNetworkDao")
	private GooglePlusNetworkDao googlePlusNetworkDao;

	/**
	 * Social network authenticate.
	 *
	 * @param authentication
	 *   the authentication
	 * @param socialCustomerData
	 *   the social customer data
	 * @return the authentication
	 *
	 * @throws AuthenticationException the authentication exception
	 */
	public Authentication socialNetworkAuthenticate(final Authentication authentication, final SocialCustomerData socialCustomerData) throws AuthenticationException
	{
		if (Registry.hasCurrentTenant() && JaloConnection.getInstance().isSystemInitialized())
		{
			final CustomerModel customerModel = getSocialNetworkCustomerLinkedToCustomer(socialCustomerData);

			final String username = customerModel == null ? "NONE_PROVIDED" : customerModel.getUid();
			UserDetails userDetails = null;
			try
			{
				userDetails = retrieveSocialNetworkUser(username);
			}
			catch (final UsernameNotFoundException arg5)
			{
				throw new BadCredentialsException(
						this.messages.getMessage("CoreAuthenticationProvider.badCredentials", "Bad credentials"), arg5);
			}

			super.getPreAuthenticationChecks().check(userDetails);
			final User user = UserManager.getInstance().getUserByLogin(userDetails.getUsername());

			super.additionalAuthenticationChecks(userDetails, (AbstractAuthenticationToken) authentication);
			JaloSession.getCurrentSession().setUser(user);
			return super.createSuccessAuthentication(authentication, userDetails);
		}
		else
		{
			return super.createSuccessAuthentication(authentication, new CoreUserDetails("systemNotInitialized",
					"systemNotInitialized", true, false, true, true, Collections.emptyList(), (String) null));
		}
	}

	/**
	 * Retrieve social network user.
	 *
	 * @param username
	 *   the username
	 * @return the user details
	 * @throws AuthenticationException the authentication exception
	 *
	 */
	protected UserDetails retrieveSocialNetworkUser(final String username) throws AuthenticationException
	{
		final UserDetails loadedUser;
		try
		{
			loadedUser = super.getUserDetailsService().loadUserByUsername(username);
		}
		catch (final UnknownIdentifierException e)
		{
			LOG.debug("Error syncing from social network", e);
			throw new AuthenticationServiceException("Error syncing from social network", e);
		}

		if (loadedUser == null)
		{
			LOG.debug("UserDetailsService returned null, which is an interface contract violation");
			throw new AuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
		}
		else
		{
			return loadedUser;
		}
	}

	/**
	 *
	 *
	 * @param socialCustomerData
	 *   the social customer data
	 */
	private CustomerModel getSocialNetworkCustomerLinkedToCustomer(final SocialCustomerData socialCustomerData)
	{
		return googlePlusNetworkDao.getCustomerLinkedToGoogleAccount(socialCustomerData.getGoogleUserId());
	}

}
