package org.fandev.lang.fan;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.components.ApplicationComponent;

/**
 * Date: Sep 2, 2009
 * Time: 11:52:56 PM
 *
 * @author Dror Bereznitsky
 */
public class FanLoader implements ApplicationComponent
{
	static
	{
		System.setProperty("fan.debug", "true");
	}

	@NotNull
	public String getComponentName()
	{
		return "fan.support.loader";
	}

	public void initComponent()
	{

	}

	public void disposeComponent()
	{

	}
}
