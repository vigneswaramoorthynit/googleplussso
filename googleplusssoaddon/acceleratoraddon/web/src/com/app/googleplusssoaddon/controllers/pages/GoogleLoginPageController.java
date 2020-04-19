
package com.app.googleplusssoaddon.controllers.pages;

import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.app.googleplusssoaddon.strategy.SocialNetworkLoginStrategy;
import com.app.googleplusssofacades.GoogleLoginFacade;
import com.app.googleplusssofacades.data.SocialCustomerData;
import com.app.googleplusssofacades.util.GooglePlusNetworkUtil;


/**
 * The Class GoogleLoginPageController.
 *
 * @author vigneswaramoorthy.n
 */
@Controller
public class GoogleLoginPageController extends SocialNetworkAddonPageController
{

	/** The google login facade. */
	@Resource(name = "googleLoginFacade")
	private GoogleLoginFacade googleLoginFacade;

	/** The googlePlusNetworkUtil */
	@Resource(name = "googlePlusNetworkUtil")
	private GooglePlusNetworkUtil googlePlusNetworkUtil;

	/** The social network login strategy. */
	@Resource(name = "socialNetworkLoginStrategy")
	private SocialNetworkLoginStrategy socialNetworkLoginStrategy;

	/** The customer facade. */
	@Resource(name = "customerFacade")
	private CustomerFacade customerFacade;

	@Resource(name = "commonI18NService")
	private CommonI18NService commonI18NService;


	/**
	 * Register using google.
	 *
	 * @return the model and view
	 * @throws Exception the exception
	 * <code>
	 *   public ModelAndView registerUsingGoogle() throws Exception
   * {
   * final String authorizeUrl = googleLoginFacade.getGoogleLoginProvider();
   * final RedirectView redirectView = new RedirectView(authorizeUrl, true, true, true);
   * return new ModelAndView(redirectView);
   * }
	 * </code>
	 */
	@RequestMapping(value = "/connect-google/login", method = RequestMethod.GET)
	public ModelAndView registerUsingGoogle() throws Exception
	{
		final String authorizeUrl = googleLoginFacade.getGoogleLoginProvider();
		final RedirectView redirectView = new RedirectView(authorizeUrl, true, true, true);
		return new ModelAndView(redirectView);
	}

	/**
	 * Gets the google login success callback.
	 *
	 * @param code the code
	 * @param request the request
	 * @param response the response
	 * @param redirectAttributes the redirect attributes
	 * @return the google login success callback
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/google/login-callback", method = RequestMethod.GET)
	public String getGoogleLoginSuccessCallback(@RequestParam("code") final String code, final HttpServletRequest request,
			final HttpServletResponse response,final RedirectAttributes redirectAttributes) throws Exception
	{
		try
		{
			final SocialCustomerData socialCustomerData = googleLoginFacade.getGoogleLoginSuccessCallback(code);
			final CustomerModel customerModel = googleLoginFacade
					.getCustomerLinkedToGoogleAccount(socialCustomerData.getGoogleUserId());
			if (null != customerModel)
			{
				final Authentication authentication = socialNetworkLoginStrategy.login(socialCustomerData, request,
						response);
				customerFacade.loginSuccess();
				getGuidCookieStrategy().setCookie(request, response);
				customerModel.setSessionLanguage(commonI18NService.getCurrentLanguage());
				getRememberMeServices().loginSuccess(request, response, authentication);
				return getRedirectToHomePage();
			}
			else
			{
				googlePlusNetworkUtil.initializeGoogleAttribute(socialCustomerData.getGoogleUserId());
				return getRedirectToLoginPage();
			}

		}
		catch (final Exception e)
		{
			LOGGER.error("Error in Google plus login ERR_NTFY_AST_INT08 - {}", e);
			return getRedirectToLoginPage();
		}
	}

	/**
	 * Gets the google login error callback.
	 *
	 * @param errorReason the error reason
	 * @return the google login error callback
	 * @throws Exception the exception
	 */

	@RequestMapping(value = "/google/login-callback", params = "error", method = RequestMethod.GET)
	public String getGoogleLoginErrorCallback(@RequestParam("error") final String errorReason) throws Exception
	{
		googleLoginFacade.getGoogleLoginErrorCallback(errorReason);
		return getRedirectToLoginPage();
	}




}
