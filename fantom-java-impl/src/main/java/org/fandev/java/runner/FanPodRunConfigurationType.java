package org.fandev.java.runner;

import consulo.annotation.component.ExtensionImpl;
import consulo.execution.configuration.ConfigurationTypeUtil;
import consulo.fantom.FantomIcons;
import consulo.ui.image.Image;

import javax.annotation.Nonnull;

/**
 * Date: Sep 4, 2009
 * Time: 11:57:21 PM
 *
 * @author Dror Bereznitsky
 */
@ExtensionImpl
public class FanPodRunConfigurationType extends FanRunConfigurationType
{
	public FanPodRunConfigurationType()
	{
		super();
		configurationFactory = new FanPodConfigurationFactory(this);
	}

	public String getDisplayName()
	{
		return "Fan Pod";
	}

	public String getConfigurationTypeDescription()
	{
		return "Fan Pod";
	}

	public Image getIcon()
	{
		return FantomIcons.Fantom;
	}

	@Nonnull
	public String getId()
	{
		return "FanPodRunConfiguration";
	}

	public static FanPodRunConfigurationType getInstance()
	{
		return ConfigurationTypeUtil.findConfigurationType(FanPodRunConfigurationType.class);
	}
}
