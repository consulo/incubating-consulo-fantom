package org.fandev.runner;

import consulo.execution.configuration.ConfigurationFactory;
import consulo.execution.configuration.ConfigurationType;
import consulo.execution.configuration.RunConfiguration;
import consulo.execution.configuration.RunConfigurationModule;
import consulo.project.Project;

import javax.annotation.Nonnull;

/**
 * Date: Sep 16, 2009
 * Time: 11:31:26 PM
 *
 * @author Dror Bereznitsky
 */
public class FanTestConfigurationFactory extends ConfigurationFactory
{
	protected FanTestConfigurationFactory(@Nonnull final ConfigurationType type)
	{
		super(type);
	}

	public RunConfiguration createTemplateConfiguration(final Project project)
	{
		return new FanTestRunConfiguration("Fantom Test", new RunConfigurationModule(project), this);
	}
}
