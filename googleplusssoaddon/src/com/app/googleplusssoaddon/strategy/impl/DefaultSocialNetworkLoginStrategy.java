package com.app.googleplusssoaddon.strategy.impl;

import de.hybris.platform.commercefacades.customer.CustomerFacade;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.app.googleplusssoaddon.strategy.SocialNetworkLoginStrategy;
import com.app.googleplusssofacades.data.SocialCustomerData;



// TODO: Auto-generated Javadoc
/**
 * The Class DefaultSocialNetworkLoginStrategy.
 */
public class DefaultSocialNetworkLoginStrategy implements SocialNetworkLoginStrategy
{

	/**
	 * The Constant LOG.
	 **/
	private static final Logger LOG = Logger.getLogger(DefaultSocialNetworkLoginStrategy.class);

	/**
	 * The social network login provider.
	 **/
	@Resource(name = "socialNetworkLoginProvider")
	private DefaultSocialNetworkLoginProvider socialNetworkLoginProvider;

	/**
	 * The customer facade.
	 **/
	@Resource(name = "customerFacade")
	private CustomerFacade customerFacade;

	/**
	 * Gets the social network login provider.
	 *
	 * @return the social network login provider
	 */
	public DefaultSocialNetworkLoginProvider getSocialNetworkLoginProvider()
	{
		return socialNetworkLoginProvider;
	}

	/**
	 * Sets the social network login provider.
	 *
	 * @param socialNetworkLoginProvider
	 *   the new social network login provider
	 */
	public void setSocialNetworkLoginProvider(final DefaultSocialNetworkLoginProvider socialNetworkLoginProvider)
	{
		this.socialNetworkLoginProvider = socialNetworkLoginProvider;
	}

	/**
	 * Gets the customer facade.
	 *
	 * @return the customer facade
	 */
	public CustomerFacade getCustomerFacade()
	{
		return customerFacade;
	}

	/**
	 * Sets the customer facade.
	 *
	 * @param customerFacade
	 *   the new customer facade
	 */
	public void setCustomerFacade(final CustomerFacade customerFacade)
	{
		this.customerFacade = customerFacade;
	}

	/**
	 *  (non-Javadoc).
	 *
	 * @param socialCustomerData the social customer data
	 * @param request the request
	 * @param response the response
	 * @return the authentication
	 * </code>
	 */
	@Override
	public Authentication login(final SocialCustomerData socialCustomerData, final HttpServletRequest request, final HttpServletResponse response)
	{
		final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(socialCustomerData.getEmail(),
				null,
				AuthorityUtils.createAuthorityList("ROLE_USER"));
		token.setDetails(new WebAuthenticationDetails(request));
		Authentication authentication = null;
		try
		{
			authentication = socialNetworkLoginProvider.socialNetworkAuthenticate(token, socialCustomerData);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		catch (final Exception e)
		{
			SecurityContextHolder.getContext().setAuthentication(null);
			LOG.error("Failure during Social Network Login", e);
		}
		return authentication;
	}

}
