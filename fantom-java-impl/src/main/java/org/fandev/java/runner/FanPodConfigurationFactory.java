package org.fandev.java.runner;

import consulo.execution.configuration.ConfigurationFactory;
import consulo.execution.configuration.ConfigurationType;
import consulo.execution.configuration.RunConfiguration;
import consulo.execution.configuration.RunConfigurationModule;
import consulo.fantom.module.extension.FanModuleExtension;
import consulo.module.extension.ModuleExtensionHelper;
import consulo.project.Project;

import javax.annotation.Nonnull;

/**
 * Date: Sep 5, 2009
 * Time: 11:28:52 PM
 *
 * @author Dror Bereznitsky
 */
public class FanPodConfigurationFactory extends ConfigurationFactory
{
	protected FanPodConfigurationFactory(@Nonnull final ConfigurationType type)
	{
		super(type);
	}

	public RunConfiguration createTemplateConfiguration(final Project project)
	{
		return new FanPodRunConfiguration("Fantom Pod", new RunConfigurationModule(project), this);
	}

	@Override
	public boolean isApplicable(@Nonnull Project project)
	{
		return ModuleExtensionHelper.getInstance(project).hasModuleExtension(FanModuleExtension.class);
	}
}
