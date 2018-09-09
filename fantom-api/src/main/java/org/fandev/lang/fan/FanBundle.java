package org.fandev.lang.fan;

import org.jetbrains.annotations.PropertyKey;
import com.intellij.AbstractBundle;

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
