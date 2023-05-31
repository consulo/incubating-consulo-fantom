package org.fandev.impl.lang.fan.structure;

import org.fandev.impl.lang.fan.psi.impl.FanFileImpl;

/**
 * @author Dror Bereznitsky
 * @date Jan 7, 2009 5:23:32 PM
 */
public class FanElementPresentation
{
	public static String getFilePresentableText(final FanFileImpl file)
	{
		return file.getName();
	}
}
