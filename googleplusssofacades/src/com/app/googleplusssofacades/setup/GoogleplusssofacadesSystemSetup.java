package com.app.googleplusssofacades.setup;

import static com.app.googleplusssofacades.constants.GoogleplusssofacadesConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.app.googleplusssofacades.constants.GoogleplusssofacadesConstants;
import com.app.googleplusssofacades.service.GoogleplusssofacadesService;


@SystemSetup(extension = GoogleplusssofacadesConstants.EXTENSIONNAME)
public class GoogleplusssofacadesSystemSetup
{
	private final GoogleplusssofacadesService googleplusssofacadesService;

	public GoogleplusssofacadesSystemSetup(final GoogleplusssofacadesService googleplusssofacadesService)
	{
		this.googleplusssofacadesService = googleplusssofacadesService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		googleplusssofacadesService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return GoogleplusssofacadesSystemSetup.class.getResourceAsStream("/googleplusssofacades/sap-hybris-platform.png");
	}
}
