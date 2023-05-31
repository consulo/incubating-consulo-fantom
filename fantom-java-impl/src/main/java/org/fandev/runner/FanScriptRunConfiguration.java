package org.fandev.runner;

import consulo.execution.configuration.ConfigurationFactory;
import consulo.execution.configuration.ModuleBasedConfiguration;
import consulo.execution.configuration.RunConfiguration;
import consulo.execution.configuration.RunConfigurationModule;
import consulo.execution.configuration.ui.SettingsEditor;
import consulo.java.execution.configurations.OwnJavaParameters;
import consulo.util.xml.serializer.InvalidDataException;
import consulo.util.xml.serializer.JDOMExternalizer;
import consulo.util.xml.serializer.WriteExternalException;
import org.jdom.Element;

/**
 * @author Dror Bereznitsky
 * @date Jan 20, 2009 8:53:55 PM
 */
public class FanScriptRunConfiguration extends FanRunConfiguration
{
	private String scriptPath;

	public FanScriptRunConfiguration(final String name, final RunConfigurationModule runConfigurationModule, final ConfigurationFactory factory)
	{
		super(name, runConfigurationModule, factory);
	}

	protected ModuleBasedConfiguration createInstance()
	{
		return new FanScriptRunConfiguration(getName(), new RunConfigurationModule(getConfigurationModule().getProject()), factory);
	}

	public SettingsEditor<? extends RunConfiguration> getConfigurationEditor()
	{
		return new FanScriptRunConfigurationEditor();
	}

	protected void setExecutable(final OwnJavaParameters params)
	{
		params.getProgramParametersList().add(getScriptPath());
	}

	@Override
	public void readExternal(final Element element) throws InvalidDataException
	{
		super.readExternal(element);
		scriptPath = JDOMExternalizer.readString(element, "path");
	}

	@Override
	public void writeExternal(final Element element) throws WriteExternalException
	{
		super.writeExternal(element);
		JDOMExternalizer.write(element, "path", scriptPath);
	}

	public String getScriptPath()
	{
		return scriptPath;
	}

	public void setScriptPath(final String scriptPath)
	{
		this.scriptPath = scriptPath;
	}
}
