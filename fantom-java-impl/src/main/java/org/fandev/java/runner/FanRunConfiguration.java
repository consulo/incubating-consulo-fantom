package org.fandev.java.runner;

import com.intellij.java.execution.configurations.JavaCommandLineState;
import com.intellij.java.language.projectRoots.JavaSdk;
import consulo.compiler.ModuleCompilerPathsManager;
import consulo.content.bundle.Sdk;
import consulo.execution.configuration.ConfigurationFactory;
import consulo.execution.configuration.ModuleBasedConfiguration;
import consulo.execution.configuration.RunConfigurationModule;
import consulo.execution.configuration.RunProfileState;
import consulo.execution.executor.Executor;
import consulo.execution.runner.ExecutionEnvironment;
import consulo.execution.ui.console.TextConsoleBuilder;
import consulo.execution.ui.console.TextConsoleBuilderFactory;
import consulo.fantom.module.extension.FanModuleExtension;
import consulo.java.execution.configurations.OwnJavaParameters;
import consulo.language.content.ProductionContentFolderTypeProvider;
import consulo.language.util.ModuleUtilCore;
import consulo.module.Module;
import consulo.module.ModuleManager;
import consulo.process.ExecutionException;
import consulo.util.xml.serializer.InvalidDataException;
import consulo.util.xml.serializer.JDOMExternalizer;
import consulo.util.xml.serializer.WriteExternalException;
import org.fandev.index.FanIndex;
import org.fandev.runner.FanTypeFilter;
import org.fandev.sdk.FanSdkType;
import org.fandev.utils.FanUtil;
import org.fandev.utils.TextUtil;
import org.jdom.Element;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Date: Sep 5, 2009
 * Time: 11:16:49 PM
 *
 * @author Dror Bereznitsky
 */
public abstract class FanRunConfiguration extends ModuleBasedConfiguration
{
	protected final ConfigurationFactory factory;
	protected String myModuleName;
	protected String executionParameters;
	protected final FanIndex index;

	public FanRunConfiguration(final String name, final RunConfigurationModule runConfigurationModule, final ConfigurationFactory factory)
	{
		super(name, runConfigurationModule, factory);
		this.factory = factory;
		this.index = getProject().getComponent(FanIndex.class);
	}

	public RunProfileState getState(@Nonnull final Executor executor, @Nonnull final ExecutionEnvironment env) throws ExecutionException
	{
		final JavaCommandLineState state = new JavaCommandLineState(env)
		{
			protected OwnJavaParameters createJavaParameters() throws ExecutionException
			{
				final String outDir = getModuleOutDir();

				final OwnJavaParameters params = new OwnJavaParameters();

				params.setJdk(createFanJdk(getSdk()));
				params.getVMParametersList().add("-Dfan.home=" + getSdk().getHomePath());
				params.getVMParametersList().add("-Djava.library.path=" + FanSdkType.getExtDir(getSdk()));
				params.getVMParametersList().add("-Dfan.debug=true");
				params.setWorkingDirectory(outDir);
				params.getClassPath().add(outDir + "/classes");
				params.setMainClass(getMainClass());
				setExecutable(params);
				params.getProgramParametersList().addParametersString(getExecutionParameters());
				params.configureByModule(getModule(), OwnJavaParameters.CLASSES_ONLY);

				return params;
			}
		};
		final TextConsoleBuilder textConsoleBuilder = TextConsoleBuilderFactory.getInstance().createBuilder(getProject());
		textConsoleBuilder.addFilter(new FanTypeFilter(getProject()));
		state.setConsoleBuilder(textConsoleBuilder);
		return state;
	}

	public static java.io.File getJdkHome(final Sdk moduleSdk)
	{
		return new java.io.File(System.getProperty("java.home"));
	}

	public static Sdk createFanJdk(final Sdk moduleSdk)
	{
		return JavaSdk.getInstance().createJdk("Fantom JDK", getJdkHome(moduleSdk).getAbsolutePath());
	}

	protected String getMainClass()
	{
		return "fanx.tools.Fan";
	}

	@Override
	public void readExternal(final Element element) throws InvalidDataException
	{
		super.readExternal(element);
		readModule(element);
		executionParameters = JDOMExternalizer.readString(element, "parameters");
	}

	@Override
	public void writeExternal(final Element element) throws WriteExternalException
	{
		super.writeExternal(element);
		writeModule(element);
		JDOMExternalizer.write(element, "parameters", executionParameters);
	}

	public Collection<Module> getValidModules()
	{
		final Module[] modules = ModuleManager.getInstance(getProject()).getModules();
		final ArrayList<Module> res = new ArrayList<Module>();
		for(final Module module : modules)
		{
			if(ModuleUtilCore.getExtension(module, FanModuleExtension.class) != null)
			{
				res.add(module);
			}
		}
		return res;
	}

	public Sdk getSdk()
	{
		final Module module = getModule();
		if(module != null)
		{
			if(FanUtil.isFanModuleType(module))
			{
				return ModuleUtilCore.getSdk(module, FanModuleExtension.class);
			}
		}
		return null;
	}

	@Nullable
	protected Module findModuleByName(@Nullable final String name)
	{
		if(name == null)
		{
			return null;
		}

		final Module module = ModuleManager.getInstance(getProject()).findModuleByName(name);
		return module != null && !module.isDisposed() ? module : null;
	}

	protected String getModuleOutDir()
	{
		return ModuleCompilerPathsManager.getInstance(getModule()).getCompilerOutputUrl(ProductionContentFolderTypeProvider.getInstance());
	}

	@Nullable
	public Module getModule()
	{
		return findModuleByName(myModuleName);
	}

	@Nullable
	public String getModuleName()
	{
		return myModuleName;
	}

	public void setModuleName(@Nullable final String moduleName)
	{
		myModuleName = TextUtil.getAsNotNull(moduleName);
	}

	public void setModule(@Nullable final Module module)
	{
		setModuleName(module != null ? module.getName() : null);
	}

	public String getExecutionParameters()
	{
		return executionParameters;
	}

	public void setExecutionParameters(final String executionParameters)
	{
		this.executionParameters = executionParameters;
	}

	protected abstract void setExecutable(final OwnJavaParameters params);
}
