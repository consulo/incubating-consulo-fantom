package org.fandev.runner;

import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import consulo.fantom.FantomIcons;
import consulo.ui.image.Image;

/**
 * @author Dror Bereznitsky
 * @date Jan 20, 2009 8:45:09 PM
 */
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

	@NotNull
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
