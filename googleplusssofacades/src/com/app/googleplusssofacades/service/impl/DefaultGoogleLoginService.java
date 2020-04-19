package com.app.googleplusssofacades.service.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
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

import com.app.googleplusssofacades.dao.GooglePlusNetworkDao;
import com.app.googleplusssofacades.data.SocialCustomerData;
import com.app.googleplusssofacades.service.GoogleLoginService;



/**
 * The Class DefaultGoogleLoginService.
 */
public class DefaultGoogleLoginService implements GoogleLoginService
{

	/** The google O auth 2 parameters. */
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;

	/** The google connection factory. */
	@Resource(name = "googleConnectionFactory")
	private GoogleConnectionFactory googleConnectionFactory;

	/** The google data converter. */
	@Resource(name = "googleDataConverter")
	private Converter<Person, SocialCustomerData> googleDataConverter;

	/** The social network dao. */
	@Resource(name = "googlePlusNetworkDao")
	private GooglePlusNetworkDao googlePlusNetworkDao;

	/** The user service. */
	@Resource(name = "userService")
	private UserService userService;

	 /**
   * The sessionService.
   **/
  @Resource(name = "sessionService")
  private SessionService sessionService;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DefaultGoogleLoginService.class);

	/* (non-Javadoc)
	 */
	@Override
	public String getGoogleLoginProvider()
	{
	  final Locale locale = sessionService.getCurrentSession().getAttribute("locale");
	  final OAuth2Template oauthOperations = (OAuth2Template) googleConnectionFactory.getOAuthOperations();
		googleOAuth2Parameters.setState("receivedfromgoogletoken");
		googleOAuth2Parameters.setRedirectUri(Config.getParameter(String.format("spring.social.google.logincallback.%s", locale.getLanguage())));
		googleOAuth2Parameters.setScope(Config.getParameter("spring.social.google.scope"));
		final String authorizeUrl = oauthOperations.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		return authorizeUrl;
	}

/**
 */
	@Override
	public SocialCustomerData getGoogleLoginSuccessCallback(final String code)
	{
	  final Locale locale = sessionService.getCurrentSession().getAttribute("locale");
	  final AccessGrant accessGrant = googleConnectionFactory.getOAuthOperations().exchangeForAccess(code,
		    Config.getParameter(String.format("spring.social.google.logincallback.%s", locale.getLanguage())), null);
		final String accessToken = accessGrant.getAccessToken();
		final Google google = new GoogleTemplate(accessToken);
		final Person person = google.plusOperations().getGoogleProfile();

		return googleDataConverter.convert(person);
	}

	/**
	 *  (non-Javadoc).
	 *
	 * @param errorReason the error reason
	 * @return the google login error callback
	 */
	@Override
	public void getGoogleLoginErrorCallback(final String errorReason)
	{
		LOG.error("Google Login failed due to  - {}", errorReason);
	}

	/**
	 *  (non-Javadoc).
	 *
	 * @param googlePlusUserId the google plus user id
	 */
	@Override
	public void addGoogleAttributesInCustomerModel(final String googlePlusUserId)
	{
		final CustomerModel customerModel = (CustomerModel) userService.getCurrentUser();
		googlePlusNetworkDao.addGoogleAttributesInCustomerModel(customerModel, googlePlusUserId);
	}

	/**
	 *  (non-Javadoc).
	 *
	 * @param googlePlusUserId the google plus user id
	 * @return the customer linked to google account
	 */
	@Override
	public CustomerModel getCustomerLinkedToGoogleAccount(final String googlePlusUserId)
	{
		return googlePlusNetworkDao.getCustomerLinkedToGoogleAccount(googlePlusUserId);
	}

}
