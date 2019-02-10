package org.fandev.runner;

import javax.annotation.Nonnull;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.openapi.project.Project;

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
