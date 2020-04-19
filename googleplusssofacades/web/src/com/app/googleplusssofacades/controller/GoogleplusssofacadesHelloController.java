
package com.app.googleplusssofacades.controller;

import static com.app.googleplusssofacades.constants.GoogleplusssofacadesConstants.PLATFORM_LOGO_CODE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.googleplusssofacades.service.GoogleplusssofacadesService;


@Controller
public class GoogleplusssofacadesHelloController
{
	@Autowired
	private GoogleplusssofacadesService googleplusssofacadesService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(final ModelMap model)
	{
		model.addAttribute("logoUrl", googleplusssofacadesService.getHybrisLogoUrl(PLATFORM_LOGO_CODE));
		return "welcome";
	}
}
