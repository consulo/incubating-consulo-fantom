package org.fandev.lang.fan;

import consulo.language.Language;

/**
 * @author Dror
 * @date Dec 12, 2008 12:04:58 AM
 */
public class FanLanguage extends Language
{
	public static final FanLanguage INSTANCE = new FanLanguage();

	private FanLanguage()
	{
		super("Fantom", "text/fan", "application/fan");
	}
}
