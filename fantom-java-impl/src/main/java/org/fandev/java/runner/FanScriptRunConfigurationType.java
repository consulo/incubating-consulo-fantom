package org.fandev.java.runner;

import consulo.annotation.component.ExtensionImpl;
import consulo.execution.configuration.ConfigurationFactory;
import consulo.execution.configuration.ConfigurationTypeUtil;
import consulo.fantom.FantomIcons;
import consulo.ui.image.Image;

import javax.annotation.Nonnull;

/**
 * @author Dror Bereznitsky
 * @date Jan 20, 2009 8:45:09 PM
 */
@ExtensionImpl
public class FanScriptRunConfigurationType extends FanRunConfigurationType
{
	public FanScriptRunConfigurationType()
	{
		super();
		configurationFactory = new FanScriptConfigurationFactory(this);
	}

	public String getDisplayName()
	{
		return "Fantom Script";
	}

	public String getConfigurationTypeDescription()
	{
		return "Fantom Script";
	}

	public Image getIcon()
	{
		return FantomIcons.Fantom;
	}

	@Nonnull
	public String getId()
	{
		return "FanScriptRunConfiguration";
	}

	public ConfigurationFactory[] getConfigurationFactories()
	{
		return new ConfigurationFactory[]{configurationFactory};
	}

	public static FanScriptRunConfigurationType getInstance()
	{
		return ConfigurationTypeUtil.findConfigurationType(FanScriptRunConfigurationType.class);
	}
}
