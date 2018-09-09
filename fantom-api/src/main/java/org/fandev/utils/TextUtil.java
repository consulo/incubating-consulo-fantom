package org.fandev.utils;

import com.intellij.openapi.util.text.StringUtil;

/**
 * @author Dror Bereznitsky
 * @date Jan 19, 2009 12:00:46 PM
 */
@Deprecated
public class TextUtil
{
	/**
	 * @param s String
	 * @return true if string is empty or equals null.
	 */
	public static boolean isEmpty(final String s)
	{
		return StringUtil.isEmpty(s);
	}

	public static String getAsNotNull(final String str)
	{
		return StringUtil.notNullize(str);
	}
}
