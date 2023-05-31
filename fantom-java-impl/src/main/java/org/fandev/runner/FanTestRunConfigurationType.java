package org.fandev.runner;

import consulo.annotation.component.ExtensionImpl;
import consulo.execution.configuration.ConfigurationTypeUtil;
import consulo.fantom.FantomIcons;
import consulo.ui.image.Image;

import javax.annotation.Nonnull;

/**
 * Date: Sep 16, 2009
 * Time: 11:30:20 PM
 *
 * @author Dror Bereznitsky
 */
@ExtensionImpl
public class FanTestRunConfigurationType extends FanRunConfigurationType
{
	public FanTestRunConfigurationType()
	{
		super();
		configurationFactory = new FanTestConfigurationFactory(this);
	}

	public String getDisplayName()
	{
		return "Fantom Test";
	}

	public String getConfigurationTypeDescription()
	{
		return "Fantom Test";
	}

	public Image getIcon()
	{
		return FantomIcons.Fantom;
	}

	@Nonnull
	public String getId()
	{
		return "FanTestRunConfiguration";
	}

	public static FanTestRunConfigurationType getInstance()
	{
		return ConfigurationTypeUtil.findConfigurationType(FanTestRunConfigurationType.class);
	}
}

