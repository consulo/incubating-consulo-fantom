package org.fandev.lang.fan;

import consulo.component.util.localize.AbstractBundle;
import org.jetbrains.annotations.PropertyKey;

public class FanBundle extends AbstractBundle
{
	private static final FanBundle ourInstance = new FanBundle();

	private FanBundle()
	{
		super("messages.FanBundle");
	}

	public static String message(@PropertyKey(resourceBundle = "messages.FanBundle") String key)
	{
		return ourInstance.getMessage(key);
	}

	public static String message(@PropertyKey(resourceBundle = "messages.FanBundle") String key, Object... params)
	{
		return ourInstance.getMessage(key, params);
	}
}
