package org.fandev.runner;

import org.jdom.Element;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizer;
import com.intellij.openapi.util.WriteExternalException;
import consulo.java.execution.configurations.OwnJavaParameters;

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
