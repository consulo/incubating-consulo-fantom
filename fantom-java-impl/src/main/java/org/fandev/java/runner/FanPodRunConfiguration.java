package org.fandev.java.runner;

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
 * You can use any of the following formats to execute a method in an installed pod
 * <p/>
 * fan <pod> [args]
 * fan <pod>::<type> [args]
 * fan <pod>::<type>.<method> [args]
 * <p/>
 * The following steps are take to execute the method:
 * <p/>
 * 1. if only a pod name is specified then assume <pod>::Main.main
 * 2. if only a type name is specified then assume <pod>::<type>.main
 * 3. resolve the qualified name of the method
 * 4. if the method is not static then call on an instance created via Type.make
 * 5. if the method has a Str[] parameter then invoke it with Sys.args, otherwise invoke with no arguments.
 * 6. if main returns an Int, return that as exit code
 * <p/>
 * Date: Sep 5, 2009
 * Time: 11:24:44 PM
 *
 * @author Dror Bereznitsky
 */
public class FanPodRunConfiguration extends FanRunConfiguration
{
	protected String executableType;

	public FanPodRunConfiguration(final String name, final RunConfigurationModule runConfigurationModule, final ConfigurationFactory factory)
	{
		super(name, runConfigurationModule, factory);
	}

	protected void setExecutable(final OwnJavaParameters params)
	{
		final String typeToExecute = executableType == null || "".equals(executableType) ? "Main" : executableType;
		params.getProgramParametersList().add(getModuleName() + "::" + typeToExecute);
	}

	protected ModuleBasedConfiguration createInstance()
	{
		return new FanPodRunConfiguration(getName(), new RunConfigurationModule(getConfigurationModule().getProject()), factory);
	}

	public SettingsEditor<? extends RunConfiguration> getConfigurationEditor()
	{
		return new FanPodRunConfigurationEditor();
	}

	@Override
	public void readExternal(final Element element) throws InvalidDataException
	{
		super.readExternal(element);
		executableType = JDOMExternalizer.readString(element, "type");
	}

	@Override
	public void writeExternal(final Element element) throws WriteExternalException
	{
		super.writeExternal(element);
		JDOMExternalizer.write(element, "type", executableType);
	}

	public String getExecutableType()
	{
		return executableType;
	}

	public void setExecutableType(final String executableType)
	{
		this.executableType = executableType;
	}
}
