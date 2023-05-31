package org.fandev.java.runner;

import consulo.execution.configuration.RunConfiguration;
import consulo.execution.configuration.RunConfigurationModule;
import consulo.execution.configuration.ui.SettingsEditor;
import consulo.execution.configuration.ConfigurationFactory;
import consulo.execution.configuration.ModuleBasedConfiguration;
import consulo.java.execution.configurations.OwnJavaParameters;

/**
 * Date: Sep 16, 2009
 * Time: 11:24:15 PM
 *
 * @author Dror Bereznitsky
 */
public class FanTestRunConfiguration extends FanPodRunConfiguration
{
	public FanTestRunConfiguration(final String name, final RunConfigurationModule runConfigurationModule, final ConfigurationFactory factory)
	{
		super(name, runConfigurationModule, factory);
	}

	@Override
	protected ModuleBasedConfiguration createInstance()
	{
		return new FanTestRunConfiguration(getName(), new RunConfigurationModule(getConfigurationModule().getProject()), factory);
	}

	@Override
	public SettingsEditor<? extends RunConfiguration> getConfigurationEditor()
	{
		return new FanTestRunConfigurationEditor();
	}

	@Override
	protected void setExecutable(final OwnJavaParameters params)
	{
		final String typeToExecute = getModuleName() + (executableType == null || "".equals(executableType) ? "" : "::" + executableType);
		params.getProgramParametersList().add(typeToExecute);
	}

	@Override
	protected String getMainClass()
	{
		return "fanx.tools.Fant";
	}
}
