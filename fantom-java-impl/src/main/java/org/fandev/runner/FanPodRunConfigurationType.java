package org.fandev.runner;

import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import consulo.fantom.FantomIcons;
import consulo.ui.image.Image;

/**
 * Date: Sep 4, 2009
 * Time: 11:57:21 PM
 *
 * @author Dror Bereznitsky
 */
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

	@NotNull
	public String getId()
	{
		return "FanPodRunConfiguration";
	}

	public static FanPodRunConfigurationType getInstance()
	{
		return ConfigurationTypeUtil.findConfigurationType(FanPodRunConfigurationType.class);
	}
}
