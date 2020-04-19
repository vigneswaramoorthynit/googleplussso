package com.app.googleplusssofacades.service.impl;

import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.util.Config;

import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;

import com.app.googleplusssofacades.data.SocialCustomerData;
import com.app.googleplusssofacades.service.GoogleRegistrationService;


/**
 * The Class DefaultGoogleRegistrationService.
 */
public class DefaultGoogleRegistrationService implements GoogleRegistrationService
{

	/**
	 * The google O auth 2 parameters.
	 **/
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;

	/**
	 * The google connection factory.
	 **/
	@Resource(name = "googleConnectionFactory")
	private GoogleConnectionFactory googleConnectionFactory;

	/**
	 * The google data converter.
	 **/
	@Resource(name = "googleDataConverter")
	private Converter<Person, SocialCustomerData> googleDataConverter;

	/**
	 * The Constant LOG.
	 **/
	private static final Logger LOG = LoggerFactory.getLogger(DefaultGoogleRegistrationService.class);

	/**
	 * Sets the google connection factory.
	 *
	 * @param googleConnectionFactory
	 *   the new google connection factory
	 */
	public void setGoogleConnectionFactory(final GoogleConnectionFactory googleConnectionFactory)
	{
		this.googleConnectionFactory = googleConnectionFactory;
	}

	 /**
   * The sessionService.
   **/
  @Resource(name = "sessionService")
  private SessionService sessionService;

	/**
	 * @return the google provider
	 */
	@Override
	public String getGoogleProvider()
	{
	  final Locale locale = sessionService.getCurrentSession().getAttribute("locale");
	  final OAuth2Template oauthOperations = (OAuth2Template) googleConnectionFactory.getOAuthOperations();
		googleOAuth2Parameters.setState(Config.getParameter("spring.social.google.state"));
		googleOAuth2Parameters.setRedirectUri(Config.getParameter(String.format("spring.social.google.callback.%s", locale.getLanguage())));
		googleOAuth2Parameters.setScope(Config.getParameter("spring.social.google.scope"));
		final String authorizeUrl = oauthOperations.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		return authorizeUrl;
	}

	/**
	 *
	 * (non-Javadoc).
	 *
	 * @param code the code
	 * @return the google registration success callback
	 */
	@Override
	public SocialCustomerData getGoogleRegistrationSuccessCallback(final String code)
	{
	  final Locale locale = sessionService.getCurrentSession().getAttribute("locale");
	  final AccessGrant accessGrant = googleConnectionFactory.getOAuthOperations().exchangeForAccess(code,
		    Config.getParameter(String.format("spring.social.google.callback.%s", locale.getLanguage())), null);
		final String accessToken = accessGrant.getAccessToken();
		final Google google = new GoogleTemplate(accessToken);
		final Person person= google.plusOperations().getGoogleProfile();
		return googleDataConverter.convert(person);
	}

	/**
	 *
	 * (non-Javadoc).
	 *
	 * @param errorReason the error reason
	 * @return the google registration error callback
	 */
	@Override
	public void getGoogleRegistrationErrorCallback(final String errorReason)
	{
		LOG.error("Google registration failed due to ERR_NTFY_AST_INT08 - {}", errorReason);
	}

}
