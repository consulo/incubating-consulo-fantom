package org.fandev.runner;

import javax.annotation.Nonnull;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.openapi.project.Project;

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
}
