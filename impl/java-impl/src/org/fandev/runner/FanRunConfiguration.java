package org.fandev.runner;

import java.util.ArrayList;
import java.util.Collection;

import org.fandev.index.FanIndex;
import org.fandev.sdk.FanSdkType;
import org.fandev.utils.FanUtil;
import org.fandev.utils.TextUtil;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import consulo.fantom.module.extension.FanModuleExtension;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.JavaCommandLineState;
import com.intellij.execution.configurations.JavaParameters;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizer;
import com.intellij.openapi.util.WriteExternalException;
import consulo.compiler.ModuleCompilerPathsManager;
import consulo.roots.impl.ProductionContentFolderTypeProvider;
import fan.sys.Env;
import fan.sys.Map;

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

	public RunProfileState getState(@NotNull final Executor executor, @NotNull final ExecutionEnvironment env) throws ExecutionException
	{
		final JavaCommandLineState state = new JavaCommandLineState(env)
		{
			protected JavaParameters createJavaParameters() throws ExecutionException
			{
				final String outDir = getModuleOutDir();

				final JavaParameters params = new JavaParameters();

				params.setJdk(createFanJdk(getSdk()));
				params.getVMParametersList().add("-Dfan.home=" + getSdk().getHomePath());
				params.getVMParametersList().add("-Djava.library.path=" + FanSdkType.getExtDir(getSdk()));
				params.getVMParametersList().add("-Dfan.debug=true");
				params.setWorkingDirectory(outDir);
				params.getClassPath().add(outDir + "/classes");
				params.setMainClass(getMainClass());
				setExecutable(params);
				params.getProgramParametersList().addParametersString(getExecutionParameters());
				params.configureByModule(getModule(), JavaParameters.CLASSES_ONLY);

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
		FanUtil.setFanHome(moduleSdk);
		final Map env = Env.cur().vars();
		String fanJavaHome = (String) env.get("java.home");
		if(fanJavaHome != null)
		{
			// Fallback to current Java version
			fanJavaHome = System.getProperty("java.home");
		}
		return new java.io.File(fanJavaHome);
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

	protected abstract void setExecutable(final JavaParameters params);
}
