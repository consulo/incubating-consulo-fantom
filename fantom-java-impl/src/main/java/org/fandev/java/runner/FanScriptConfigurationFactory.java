package org.fandev.java.runner;

import consulo.execution.configuration.ConfigurationFactory;
import consulo.execution.configuration.ConfigurationType;
import consulo.execution.configuration.RunConfiguration;
import consulo.execution.configuration.RunConfigurationModule;
import consulo.project.Project;

import javax.annotation.Nonnull;

/**
 * @author Dror Bereznitsky
 * @date Jan 20, 2009 8:52:38 PM
 */
public class FanScriptConfigurationFactory extends ConfigurationFactory
{
	protected FanScriptConfigurationFactory(@Nonnull final ConfigurationType type)
	{
		super(type);
	}

	public RunConfiguration createTemplateConfiguration(final Project project)
	{
		return new FanScriptRunConfiguration("Fantom Script", new RunConfigurationModule(project), this);
	}
}
