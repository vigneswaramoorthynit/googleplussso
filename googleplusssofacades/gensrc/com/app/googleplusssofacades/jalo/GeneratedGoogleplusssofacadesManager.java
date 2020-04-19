/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 16-Apr-2020 01:04:01                        ---
 * ----------------------------------------------------------------
 */
package com.app.googleplusssofacades.jalo;

import com.app.googleplusssofacades.constants.GoogleplusssofacadesConstants;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.user.Customer;
import de.hybris.platform.jalo.user.User;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>GoogleplusssofacadesManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedGoogleplusssofacadesManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put("googlePlusUserId", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.user.Customer", Collections.unmodifiableMap(tmp));
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	@Override
	public String getName()
	{
		return GoogleplusssofacadesConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.googlePlusUserId</code> attribute.
	 * @return the googlePlusUserId - Google EmailId
	 */
	public String getGooglePlusUserId(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, GoogleplusssofacadesConstants.Attributes.Customer.GOOGLEPLUSUSERID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.googlePlusUserId</code> attribute.
	 * @return the googlePlusUserId - Google EmailId
	 */
	public String getGooglePlusUserId(final Customer item)
	{
		return getGooglePlusUserId( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.googlePlusUserId</code> attribute. 
	 * @param value the googlePlusUserId - Google EmailId
	 */
	public void setGooglePlusUserId(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, GoogleplusssofacadesConstants.Attributes.Customer.GOOGLEPLUSUSERID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.googlePlusUserId</code> attribute. 
	 * @param value the googlePlusUserId - Google EmailId
	 */
	public void setGooglePlusUserId(final Customer item, final String value)
	{
		setGooglePlusUserId( getSession().getSessionContext(), item, value );
	}
	
}
