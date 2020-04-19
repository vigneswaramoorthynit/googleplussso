/**
 *
 */
package com.app.googleplusssoaddon.controllers.pages;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.app.googleplusssofacades.GoogleRegistrationFacade;
import com.app.googleplusssofacades.data.SocialCustomerData;


/**
 * The Class GoogleRegistrationPageController.
 *
 * @author vigneswaramoorthy.n
 */
@Controller
public class GoogleRegistrationPageController extends SocialNetworkAddonPageController
{

	/** The google registration facade. */
	@Resource(name = "googleRegistrationFacade")
	private GoogleRegistrationFacade googleRegistrationFacade;

	/**
	 * Register using google.
	 *
	 * @return the model and view
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/connect-google/register", method = RequestMethod.GET)
	public ModelAndView registerUsingGoogle() throws Exception
	{
		final String authorizeUrl = googleRegistrationFacade.getGoogleProvider();
		final RedirectView redirectView = new RedirectView(authorizeUrl, true, true, true);
		return new ModelAndView(redirectView);
	}

	/**
	 * Gets the google registration success callback.
	 *
	 * @param code the code
	 * @param redirectAttributes the redirect attributes
	 * @return the google registration success callback
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/google/register-callback", method = RequestMethod.GET)
	public String getGoogleRegistrationSuccessCallback(@RequestParam("code") final String code,
			final RedirectAttributes redirectAttributes)
			throws Exception
	{
		final SocialCustomerData socialCustomerData = googleRegistrationFacade.getGoogleRegistrationSuccessCallback(code);
		return getRedirectToRegisterPage();
	}

	/**
	 * Gets the goole registration error callback.
	 *
	 * @param error the error
	 * @return the goole registration error callback
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/google/register-callback", params = "error", method = RequestMethod.GET)
	public String getGooleRegistrationErrorCallback(@RequestParam("error") final String error) throws Exception
	{
		googleRegistrationFacade.getGoogleRegistrationErrorCallback(error);
		return getRedirectToRegisterPage();

	}
}
